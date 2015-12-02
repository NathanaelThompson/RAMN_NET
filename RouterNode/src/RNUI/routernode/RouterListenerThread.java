/*
 * Copyright (C) 2015 Nate
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
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RouterListenerThread extends Thread{
    private Thread th;
    private final String threadName;
    private ServerSocket listenSocket;
    private final int listenPort;
    ArrayList<RAMNConnection> routingTable;
    BufferedReader fromConnection=null;
    PrintWriter toConnection=null;
    Socket incConnection=null;
    
    public RouterListenerThread(String t_name, int port, ArrayList<RAMNConnection> rt)
    {
        threadName = t_name;
        listenPort = port;
        routingTable = rt;
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
        RAMNConnection metaData;
        try
        {
            //grab the incoming connection from the router
            listenSocket = new ServerSocket(listenPort);
            incConnection = listenSocket.accept();
            toConnection= new PrintWriter(incConnection.getOutputStream(),true);
            fromConnection= new BufferedReader(new InputStreamReader(incConnection.getInputStream()));
                
            RouterNodeUI.routerConnSocket = incConnection;
            //create a new RAMNConnection and add it to the routing table
            metaData = new RAMNConnection("RAMN_ROUTER_CONNECTOR", incConnection);
            //add "RAMN_ROUTER_LISTENER"
            routingTable.add(metaData);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not establish an incoming connection to a router. Please try again.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //while the connection is open
        String message;
        while(incConnection.isConnected())
        {
            try
            {
                message = fromConnection.readLine();
                makeResponse(message);
            }
            catch(IOException ioe)
            {}
        }
    }
    public void makeResponse(String input)
    {   
        if(input == null || input.isEmpty())
            return;
        switch(input)
        {
            case RouterNodeUI.RAMN_REQUEST_CONNECTION://WIP
                try
                {
                    //get the user requested
                    String userRequested = fromConnection.readLine();
                    String ipRequested = "x.x.x.x";
                    
                    //try to find user requested in local routing table
                    for(int i = 0; i < routingTable.size();i++)
                    {
                        //if user is found
                        if(routingTable.get(i).getUsername().equals((userRequested)))
                        {
                            //tell the requestor, and return
                            ipRequested = routingTable.get(i).getSocket().getRemoteSocketAddress().toString();
                            toConnection.println(RouterNodeUI.RAMN_RESPONSE_OK);
                            toConnection.println(ipRequested);
                            return;
                        }
                    }
                    
                    //if it wasn't found...
                    if(ipRequested.equals("x.x.x.x"))
                    {
                        //...ask neighboring router to search
                        toConnection.println(RouterNodeUI.RAMN_REQUEST_IP);
                        toConnection.println(userRequested);
                        ipRequested = fromConnection.readLine();
                    }
                    
                    //if the neighboring router couldn't find it either...
                    if(ipRequested.equals(RouterNodeUI.RAMN_RESPONSE_ERROR))
                    {
                        //...signal to client the connection wasn't found
                        toConnection.println(RouterNodeUI.RAMN_RESPONSE_ERROR);
                    }
                    else
                    {
                        //otherwise, tell the client it's all good, and give them the requested IP address
                        toConnection.println(RouterNodeUI.RAMN_RESPONSE_OK);
                        toConnection.println(ipRequested);
                    }
                }
                catch(IOException ioe)
                {
                    toConnection.println(RouterNodeUI.RAMN_RESPONSE_ERROR);
                }
                break;
            case RouterNodeUI.RAMN_REQUEST_DISCONNECT://when a peer wishes to disconnect from another peer
                String userToDisconnect;
                
                try
                {
                    userToDisconnect = fromConnection.readLine();
                    for(int i = 0; i < routingTable.size(); i++)
                    {
                        if(routingTable.get(i).getUsername().equals(userToDisconnect))
                        {
                            routingTable.get(i).getSocket().close();
                            routingTable.remove(i);
                            return;//user found, return;
                        }
                    }
                    
                    //since the user wasn't found, have to poll neighbor router
                    toRouter = new PrintWriter(routerSocket.getOutputStream(),true);
                    toRouter.println(RouterNodeUI.RAMN_REQUEST_DISCONNECT);
                    toRouter.println(userToDisconnect);
                    
                }
                catch(IOException ioe){}
                
                break;
            case RouterNodeUI.RAMN_REQUEST_PEERLIST://When clients need to know who else is in the network
                for(int i = 0; i < routingTable.size(); i++)
                {
                    toConnection.println(routingTable.get(i).getUsername());
                }
                toConnection.println(RouterNodeUI.RAMN_TRANSFER_COMPLETE);
                break;
            case RouterNodeUI.RAMN_REQUEST_ROUTER_DISCONNECT://when a client wishes to disconnect from the router
                try
                {
                    incConnection.close();
                    toConnection.close();
                    fromConnection.close();
                }
                catch(IOException ioe)
                {}
                break;
            case RouterNodeUI.RAMN_REQUEST_IP://for whenever a client requests a connection located outside of its router
                try
                {
                    for(int i = 0; i < routingTable.size(); i++)
                    {
                        if(routingTable.get(i).getUsername().equals(fromConnection.readLine()))
                        {
                            toConnection.println(routingTable.get(i).getSocket().getRemoteSocketAddress().toString());
                            return;
                        }
                    }
                }
                catch(IOException ioe)
                {
                    
                }
               
                break;
            default:
                break;
        }
    }
}
