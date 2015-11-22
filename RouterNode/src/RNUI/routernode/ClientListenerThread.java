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

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientListenerThread extends Thread{
    private Thread th;
    private final String threadName;
    private ServerSocket listenSocket;
    private final int listenPort;
    ArrayList<RAMNConnection> routingTable;
    
    public ClientListenerThread(String t_name, int port, ArrayList<RAMNConnection> rt)
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
        while(true)
        {
            Socket incConnection;
            RAMNConnection metaData;
            
            try
            {
                listenSocket = new ServerSocket(listenPort);
                incConnection = listenSocket.accept();
                
                //get the remote socket address...
                InetSocketAddress sockAddr = (InetSocketAddress)incConnection.getRemoteSocketAddress();
                InetAddress iAddress = sockAddr.getAddress();
                Inet4Address i4Addr = (Inet4Address)iAddress;
                
                //...and convert it to a string
                byte[] ip4AddrBytes = i4Addr.getAddress();
                String address = new String(ip4AddrBytes);
                
                //create a new RAMNConnection and add it to the routing table
                metaData = new RAMNConnection("RAMN_ROUTER", address, incConnection);
                routingTable.add(metaData);
            }
            catch(IOException ioe){}
        }
    }
}
