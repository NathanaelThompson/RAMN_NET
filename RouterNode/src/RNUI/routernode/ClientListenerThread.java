/*
 * Copyright (C) 2015 Nathanael Thompson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package RNUI.routernode;

/**
 *
 * @author Nate
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientListenerThread extends Thread{
    private Thread th;
    private String threadName;
    private ServerSocket listenSocket;
    public int listenPort;
    ArrayList<RAMNConnection> routingTable;
    BufferedReader fromClient, fromRouter;
    PrintWriter toClient, toRouter;
    Socket neighborSocket = null;
    ClientListenerThread clt = null;
    
    public ClientListenerThread()
    {
        threadName = "uninitialized";
        listenPort = -1;
        routingTable = null;
    }
    public ClientListenerThread(String t_name, int port, ArrayList<RAMNConnection> rt, Socket routerToRouterSocket)
    {
        threadName = t_name;
        listenPort = port;
        routingTable = rt;   
        neighborSocket =routerToRouterSocket;
        try
        {
            if(neighborSocket != null){
            toRouter = new PrintWriter(neighborSocket.getOutputStream(), true);
            fromRouter = new BufferedReader(new InputStreamReader(neighborSocket.getInputStream()));
            }
        }
        catch(IOException ioe)
        {
            
        }
    }
    
    public void setThreadName(String name)
    {
        threadName = name;
    }
    public String getThreadName()
    {
        return threadName;
    }
    public void setListenPort(int port)
    {
        listenPort = port;
    }
    public void setRoutingTable(ArrayList<RAMNConnection> rt)
    {
        routingTable = rt;
    }
    public void setNeighborSocket(Socket s)
    {
        neighborSocket = s;
        try
        {
            toRouter = new PrintWriter(neighborSocket.getOutputStream(), true);
            fromRouter = new BufferedReader(new InputStreamReader(neighborSocket.getInputStream()));
        }
        catch(IOException ioe)
        {
                
        }
    }
        
    public void listenStart()
    {
        if(th == null)
        {
            th = new Thread(this, threadName);
            th.start();
        }
    }
    
    @Override
    public void run()
    {
        Socket incConnection;
        RAMNConnection metaData;
        
        try 
        {
            //get incoming connection
            listenSocket = new ServerSocket(listenPort);
            incConnection = listenSocket.accept();

            //in the event multiple clients are simultaneously connecting, we try to service them as soon as possible
            //also, the port must be adjusted for unique connections
            listenPort += 1;
            clt = new ClientListenerThread("CLT (stacked)", listenPort, routingTable, neighborSocket);
            clt.listenStart();

            toClient = new PrintWriter(incConnection.getOutputStream(), true);
            fromClient = new BufferedReader(new InputStreamReader(incConnection.getInputStream()));

            //for registration
            String potentialUser = fromClient.readLine();
            boolean failFlag = false;
            for (int i = 0; i < routingTable.size(); i++) 
            {
                if (routingTable.get(i).getUsername().equals(potentialUser)) 
                {
                    toClient.println(RouterNodeUI.RAMN_RESPONSE_DENIED);
                    failFlag = true;
                    break;
                }
            }
            if (!failFlag) 
            {
                //create a new RAMNConnection and add it to the routing table
                metaData = new RAMNConnection(potentialUser, incConnection);
                routingTable.add(metaData);
                toClient.println(RouterNodeUI.RAMN_RESPONSE_OK);
            }
            else
                return;
            
            while(!incConnection.isClosed())
            {
                String input;
                input = fromClient.readLine();
                switch(input)
                {
                    case RouterNodeUI.RAMN_REQUEST_PEERLIST:
                        //print connections to client
                        if (neighborSocket != null) 
                        {
                            //get users from neighbor router
                            toRouter.println(RouterNodeUI.RAMN_REQUEST_PEERLIST);
                            String user;
                            while (!((user = fromRouter.readLine()).equals(RouterNodeUI.RAMN_TRANSFER_COMPLETE))) 
                            {
                                toClient.println(user);
                            }

                            //get connections for this router's routing table
                            for (int i = 0; i < routingTable.size(); i++) 
                            {
                                user = routingTable.get(i).getUsername();
                                toClient.println(user);
                            }
                        }        
                        else 
                        {
                            //get connections for this router's routing table
                            String user;
                            for (int i = 0; i < routingTable.size(); i++)
                            {
                                user = routingTable.get(i).getUsername();
                                toClient.println(user);
                            }
                        }
                        toClient.println(RouterNodeUI.RAMN_TRANSFER_COMPLETE);
                        break;
                    case RouterNodeUI.RAMN_REQUEST_IP:
                        for(int i = 0; i < routingTable.size(); i++)
                        {
                             if(routingTable.get(i).getUsername().equals(fromRouter.readLine()))
                             {
                                 toRouter.println(routingTable.get(i).getSocket().getRemoteSocketAddress().toString());
                                 return;
                             }
                        }
                        break;
                    case RouterNodeUI.RAMN_REQUEST_CONNECTION:
                        //get the user requested
                        String userRequested = fromClient.readLine();
                        String ipRequested = "x.x.x.x";
                    
                        //try to find user requested in local routing table
                        for(int i = 0; i < routingTable.size();i++)
                        {
                            //if user is found
                            if(routingTable.get(i).getUsername().equals((userRequested)))
                            {
                                //tell the requestor, and return
                                ipRequested = routingTable.get(i).getSocket().getRemoteSocketAddress().toString();
                                toClient.println(RouterNodeUI.RAMN_RESPONSE_OK);
                                toClient.println(ipRequested);
                                return;
                            }
                        }
                    
                        //if it wasn't found...
                        if(ipRequested.equals("x.x.x.x"))
                        {
                            //...ask neighboring router to search
                            toRouter.println(RouterNodeUI.RAMN_REQUEST_IP);
                            toRouter.println(userRequested);
                            ipRequested = fromRouter.readLine();
                        }
                    
                        //if the neighboring router couldn't find it either...
                        if(ipRequested.equals(RouterNodeUI.RAMN_RESPONSE_ERROR))
                        {
                            //...signal to client the connection wasn't found
                            toClient.println(RouterNodeUI.RAMN_RESPONSE_ERROR);
                        }
                        else
                        {
                            //otherwise, tell the client it's all good, and give them the requested IP address
                            toClient.println(RouterNodeUI.RAMN_RESPONSE_OK);
                            toClient.println(ipRequested);
                        }
                        break;
                    case RouterNodeUI.RAMN_REQUEST_DISCONNECT:
                        break;
                    case RouterNodeUI.RAMN_REQUEST_ROUTER_DISCONNECT:
                        incConnection.close();
                        fromClient.close();
                        toClient.close();
                        break;
                }    
            }
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
}
