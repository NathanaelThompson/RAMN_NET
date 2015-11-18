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
    String username;
    String ipAddress;
    Socket connection;
    
    public RAMNConnection()
    {
        username="";
        ipAddress="";
        connection=null;
    }
    public RAMNConnection(String user, String address, Socket s)
    {
        username = user;
        ipAddress = address;
        connection = s;
    }
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String user)
    {
        username = user;
    }
    
    public String getIPAddress()
    {
        return ipAddress;
    }
    public void setIPAddress(String address)
    {
        ipAddress = address;
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
