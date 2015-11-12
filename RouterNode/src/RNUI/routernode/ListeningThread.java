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

public class ListeningThread extends Thread{
    private Thread th;
    private String threadName;
    private ServerSocket listenSocket;
    private int listenPort;
    
    public ListeningThread(String name, int port)
    {
        threadName = name;
        listenPort = port;
    }
    public void listenStart()
    {
        if(th == null)
        {
            th = new Thread(this, threadName);
            th.start();
        }
    }
    public void run()
    {
        try
        {
            listenSocket = new ServerSocket(listenPort);
            listenSocket.accept();
        }
        catch(IOException ioe)
        {
            
        }
    }
}
