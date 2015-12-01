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

public class RAMNConnection {
    //for use with client to router connections
    String username;
    Socket connection;
    
    
    //default constructor
    public RAMNConnection()
    {
        username="";
        connection=null;
    }
    //for use with client to router connections
    public RAMNConnection(String user, Socket s)
    {
        username = user;
        connection = s;
    }
    
    //for use with client to client connections
    String user1, user2;
    String ip1, ip2;
    
    //for use with client to client connections
    public RAMNConnection(String u1, String u2)
    {
        user1 = u1;
        user2 = u2;
        connection = null;
    }
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String user)
    {
        username = user;
    }
    
    public Socket getSocket()
    {
        return connection;
    }
    public void setSocket(Socket s)
    {
        connection = s;
    }
}
