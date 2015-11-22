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

public class ClientReceiveConnectionThread extends Thread{
    private Thread th;
   // private final String threadName; //Robert commented out in order to run
    ServerSocket listenSocket;
    int listenPort;
}
