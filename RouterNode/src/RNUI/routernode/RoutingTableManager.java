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
public class RoutingTableManager implements RoutingTableInterface{
    public static Object[][] routingTable;
    public RoutingTableManager()
    {
        routingTable = new Object[100][2];
    }
    @Override
    public Object[][] getRoutingTable()
    {
        return routingTable;
    }
    @Override
    public void setRoutingTable(Object[][] rt)
    {
        routingTable = rt;
    }
    @Override
    public void addToRoutingTable(String address, String username)
    {
        for(int i = 0; i < routingTable.length; i++)
        {
            if(routingTable[i][0] == null)
            {
                routingTable[i][0] = address;
                routingTable[i][1] = username;
                return;
            }
        }
    }
    @Override
    public void removeFromRoutingTable(String username)
    {
        for(int i = 0; i < routingTable.length; i++)
        {
            if(routingTable[i][1].equals(username))
            {
                routingTable[i][0] = null;
                routingTable[i][1] = null;
            }
        }
    }
    @Override
    public void removeFromRoutingTable(String address, String username)
    {
        for(int i = 0; i < routingTable.length; i++)
        {
            if(routingTable[i][0].equals(address) || routingTable[i][1].equals(username))
            {
                routingTable[i][0] = null;
                routingTable[i][1] = null;
            }
        }
    }
}
