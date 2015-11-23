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
package CNUI.clientnode;

/**
 *
 * @author Nate
 */

import java.io.*;
import java.net.*;
import java.awt.TextArea;
import javax.swing.JOptionPane;

public class ClientReceiveThread extends Thread{
    private Thread th;
    private final String threadName;
    ServerSocket listenSocket;
    Socket commSocket;
    private final int listenPort;
    BufferedReader fromPeer;
    TextArea chatArea;
    public ClientReceiveThread(String t_name, int port, TextArea ta)
    {
        threadName = t_name;
        listenPort = port;
        chatArea = ta;
    }
    public void CRTStart()
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
        try
        {
            //open the listening socket
            listenSocket = new ServerSocket(listenPort);
        }
        catch(IOException ioe)
        {
            
        }
        while(true)
        {
            try
            {
                commSocket = new Socket();
                
                //accept() inc connection
                commSocket = listenSocket.accept();
                
                //get confirmation from the user
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Incoming connection, would you like to accept?",
                        "Confirm Connection", JOptionPane.YES_NO_OPTION);

                //if confirmed...
                if(confirmation == JOptionPane.YES_OPTION)
                {
                    //...do I/O while connected
                    fromPeer = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
                    String msgReceived;
                    while(commSocket.isConnected())
                    {
                        msgReceived = fromPeer.readLine();
                        chatArea.append("Received > " + msgReceived);
                    }
                }
                else //otherwise...
                {
                    //close the comm socket
                    commSocket.close();
                    commSocket = null;
                }
            }
            catch(IOException ioe)
            {

            }
        }
    }
}
