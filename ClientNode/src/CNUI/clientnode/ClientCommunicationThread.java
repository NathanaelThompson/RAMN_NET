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

public class ClientCommunicationThread extends Thread{
    private Thread th;
    String threadName;
    Socket commSocket, routerSocket;
    PrintWriter toPeer;
    BufferedReader fromPeer;
    
    public ClientCommunicationThread(String t_name, Socket cSocket, Socket rSocket)
    {
        threadName = t_name;
        commSocket = cSocket;
        routerSocket = rSocket;
        
        try
        {
            toPeer = new PrintWriter(commSocket.getOutputStream(), true);
            fromPeer = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
        }
        catch(IOException e)
        {}
    }
    
    public void commStart()
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
        //communication loop!
    }
}
