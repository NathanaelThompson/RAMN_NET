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
    private int listenPort;
    ArrayList<RAMNConnection> routingTable;
    BufferedReader fromClient, fromRouter;
    PrintWriter toClient, toRouter;
    Socket neighborSocket = null;
    
    public ClientListenerThread()
    {
        threadName = "uninitialized";
        listenPort = -1;
        routingTable = null;
    }
    public ClientListenerThread(String t_name, int port, ArrayList<RAMNConnection> rt)
    {
        threadName = t_name;
        listenPort = port;
        routingTable = rt;   
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
        ClientListenerThread clt;
        try 
        {
            //get incoming connection
            listenSocket = new ServerSocket(listenPort);
            incConnection = listenSocket.accept();

            //in the event multiple clients are simultaneously connecting, we try to service them as soon as possible
            //also, the port must be adjusted for unique connections
            listenPort += 1;
            clt = new ClientListenerThread("CLT (stacked)", listenPort, routingTable);
            clt.listenStart();

            toClient = new PrintWriter(incConnection.getOutputStream(), true);
            fromClient = new BufferedReader(new InputStreamReader(incConnection.getInputStream()));

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

                //print connections to client
                if (neighborSocket != null) 
                {
                    //get connections from neighbor router
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
            }
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
}
