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

package CNUI.clientnode;

import java.io.*;
import java.net.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ClientNodeUI extends javax.swing.JFrame {

    /**
     * Creates new form ClientNodeUI
     */
    
    //The RAMN_NET protocol definition
    public static final String RAMN_RESPONSE_OK = "OK";//confirmation response
    public static final String RAMN_RESPONSE_ERROR = "ERROR";//error in the request
    public static final String RAMN_RESPONSE_DENIED = "DENIED";//request is denied: could add specific codes here and @RAMN_RESPONSE_ERROR
    public static final String RAMN_TRANSFER_COMPLETE = "COMPLETE";
    public static final String RAMN_REQUEST_CONNECTION = "CON";//for connecting to a peer
    public static final String RAMN_REQUEST_DISCONNECT = "DCON";//for disconnection from a peer
    public static final String RAMN_REQUEST_ROUTER_DISCONNECT = "RTDCON";//for disconnecting from a router
    public static final String RAMN_REQUEST_REGISTER = "REGISTER";//to attempt to register a new user
    public static final String RAMN_REQUEST_PEERLIST = "PEERS";//to request all active connections
    public static final String RAMN_REQUEST_IP = "RQIP";//request an IP from the router
    
    public ClientNodeUI() {
        initComponents();
        routerErrLabel.setVisible(false);
        uniqueErrLabel.setVisible(false);
        styleErrLabel.setVisible(false);
        connErrLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        connectRouterButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        routerIPTF = new javax.swing.JTextField();
        routerErrLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        uniqueErrLabel = new javax.swing.JLabel();
        styleErrLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        connectClientButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userJList = new javax.swing.JList();
        connErrLabel = new javax.swing.JLabel();
        refreshClientButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        chatTA = new java.awt.TextArea();
        sendButton = new javax.swing.JButton();
        textToSend = new javax.swing.JTextField();
        disconnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RAMN_NET");
        setIconImages(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "Router Connection"));

        connectRouterButton.setText("Connect");
        connectRouterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectRouterButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Router IP:");

        routerErrLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        routerErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        routerErrLabel.setText("*router not found, please try again");
        routerErrLabel.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(connectRouterButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(routerErrLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(routerIPTF))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(routerIPTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(routerErrLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(connectRouterButton)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true), "Register for p2p communication"));

        jLabel1.setText("Username:");

        usernameTF.setEnabled(false);

        uniqueErrLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        uniqueErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        uniqueErrLabel.setText("*username already in use");
        uniqueErrLabel.setToolTipText("");
        uniqueErrLabel.setEnabled(false);

        styleErrLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        styleErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        styleErrLabel.setText("*username cannot contain special characters\n(@,$, \", \', etc...)");
        styleErrLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        styleErrLabel.setEnabled(false);

        registerButton.setText("Register");
        registerButton.setEnabled(false);
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTF)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(uniqueErrLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(styleErrLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registerButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uniqueErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(styleErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true), "Available connections")));

        connectClientButton.setText("Connect");
        connectClientButton.setEnabled(false);
        connectClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectClientButtonActionPerformed(evt);
            }
        });

        userJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Usernames here!" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        userJList.setEnabled(false);
        jScrollPane1.setViewportView(userJList);

        connErrLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        connErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        connErrLabel.setText("*connection failed, please try again");
        connErrLabel.setEnabled(false);

        refreshClientButton.setText("Refresh");
        refreshClientButton.setEnabled(false);
        refreshClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshClientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(connectClientButton)
                        .addGap(18, 18, 18)
                        .addComponent(connErrLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshClientButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(connErrLabel)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshClientButton)
                        .addComponent(connectClientButton))))
        );

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true), "Chat Window"));

        chatTA.setEditable(false);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        textToSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textToSendActionPerformed(evt);
            }
        });

        disconnButton.setText("Disconnect");
        disconnButton.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chatTA, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToSend))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(disconnButton)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatTA, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(textToSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnButton)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exitButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //Tell the router to remove this client from the routing table,
        //and close whatever streams are open,
        try
        {            
            toRouter.println(RAMN_REQUEST_ROUTER_DISCONNECT);
            sockToRouter.close();
            toRouter.close();
            fromRouter.close();
            
            sockToPeer.close();
            toPeer.close();
            fromPeer.close();
        }
        catch(Exception ex){}
        
        //and exit.
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    Socket sockToRouter = null, sockToPeer = null;
    PrintWriter toRouter = null, toPeer = null;
    BufferedReader fromRouter = null, fromPeer = null;
    
    private void connectRouterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectRouterButtonActionPerformed
        
        String routerIP = routerIPTF.getText();
        int portNum = 5555;
        boolean failFlag = true;
        for(int i = 0; i < 100; i++)
        {
            try 
            {
                sockToRouter = new Socket();
                sockToRouter.connect(new InetSocketAddress(routerIP, (portNum+i)), 1000);
                toRouter = new PrintWriter(sockToRouter.getOutputStream(), true);
                fromRouter = new BufferedReader(new InputStreamReader(sockToRouter.getInputStream()));
                usernameTF.setEnabled(true);
                registerButton.setEnabled(true);
                failFlag = false;
                break;
            } 
            catch (UnknownHostException e) 
            {
                routerErrLabel.setVisible(true);
                routerErrLabel.setEnabled(true);
            } 
            catch (IOException ioe) 
            {
                routerErrLabel.setVisible(true);
                routerErrLabel.setEnabled(true);
            }
        }
        if(failFlag)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Failed to connect to the router. Please try again.", 
                    "Connection failure",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_connectRouterButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String username = usernameTF.getText();
        
        //^ finds the start of a string
        //[a-zA-Z0-9_-] finds sequence of lower/upper case, numbers, underscore, and hyphen
        //{4,30} establishes entry must be min 4 length, max 32 length
        //$ asserts end of the string
        String pattern = "^[a-zA-Z0-9_-]{4,32}$";

        if(username.length() < 4 && username.length() > 32)
        {
            styleErrLabel.setEnabled(true);
            styleErrLabel.setVisible(true);
            return;
        }
        
        /*If name fails, we print to the error label the error message
          else, add username and update routing table*/
        if(!username.matches(pattern))
        {
            styleErrLabel.setEnabled(true);
            styleErrLabel.setVisible(true);
            styleErrLabel.setText("Invalid username: " + username);
            usernameTF.grabFocus();
        }
        else
        {
            //toRouter.println(RAMN_REQUEST_REGISTER);
            toRouter.println(username);
            
            try
            {
                String ramnResponse = null;
                ramnResponse = fromRouter.readLine();
                if(ramnResponse.equals(RAMN_RESPONSE_OK))//if registration went ok
                {
                    /*Get users connected to the network,
                      and allow users to proceed with connection.*/
                    userJList.setEnabled(true);
                    connectClientButton.setEnabled(true);
                    refreshClientButton.setEnabled(true);
                    long startTime, endTime, totalTime;
                    startTime = System.currentTimeMillis();
                    
                    toRouter.println(RAMN_REQUEST_PEERLIST);
                    DefaultListModel list = new DefaultListModel();
                    while(!((ramnResponse = fromRouter.readLine()).equals(RAMN_TRANSFER_COMPLETE))) //While the transfer isn't complete
                    {
                        list.addElement(ramnResponse);
                    }
                    
                    userJList.setModel(list);
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime;
                    JOptionPane.showMessageDialog(null, "Transfer time: " + totalTime, "Transfer time", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(ramnResponse.equals(RAMN_RESPONSE_DENIED))//if denied
                {
                    JOptionPane.showMessageDialog(
                    null,
                    "Username already taken. Please try again.", 
                    "Denied operation",
                    JOptionPane.INFORMATION_MESSAGE);
                }
                else //otherwise, error out
                {
                    JOptionPane.showMessageDialog(
                    null,
                    "Failed to register your username. Please try again.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(
                    null,
                    "Could not get active connections.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        /*Launch a thread for client to receive incoming connections,
        and receive incoming communication from those connections.*/
        
        ClientReceiveThread crt = new ClientReceiveThread("Client-Side LT", 5555, chatTA);
        crt.CRTStart();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void connectClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectClientButtonActionPerformed
        
        try
        {
            //get the index of the selected item and return if nothing is selected
            int selectedUser = userJList.getSelectedIndex();
            String userToConnect;
            if(selectedUser==-1)
            {
                JOptionPane.showMessageDialog(
                    null,
                    "No user selected, please try again.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                userToConnect = userJList.getSelectedValue().toString();
            }
            
            //tell the router to connect to a client
            toRouter.println(RAMN_REQUEST_CONNECTION);
            toRouter.println(userToConnect);
            
            //if the user's IP was found
            if(fromRouter.readLine().equals(RAMN_RESPONSE_OK))
            {
                String ipToConnect = fromRouter.readLine();
                sockToPeer = new Socket(ipToConnect, 8888);
                toPeer = new PrintWriter(sockToPeer.getOutputStream(), true);
            }
            else
            {
                JOptionPane.showMessageDialog(
                    null,
                    "Error establishing connection\n. User may have disconnected, "
                        + "please click \"Refresh\" to ensure user is still in RAMN_NET.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(IOException ioe)
        {
            
        }
    }//GEN-LAST:event_connectClientButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        //send message to peer and clear the text field
        String inputData = textToSend.getText() + '\n';
        chatTA.append(inputData);
        toPeer.println(inputData);
        textToSend.setText("");
        
    }//GEN-LAST:event_sendButtonActionPerformed

    private void textToSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textToSendActionPerformed
        // TODO add your handling code here:
        sendButtonActionPerformed(evt);
    }//GEN-LAST:event_textToSendActionPerformed

    private void refreshClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshClientButtonActionPerformed
        long startTime, endTime, totalTime;
        startTime = System.currentTimeMillis();
        toRouter.println(RAMN_REQUEST_PEERLIST);
        DefaultListModel list = new DefaultListModel();
        String ramnResponse;
        try
        {
            while (!((ramnResponse = fromRouter.readLine()).equals(RAMN_TRANSFER_COMPLETE))) //While the transfer isn't complete
            {
                list.addElement(ramnResponse);
            }
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            JOptionPane.showMessageDialog(null, "Total transfer time: " + totalTime,
                    "Transfer time", JOptionPane.INFORMATION_MESSAGE);
            userJList.setModel(list);
        }
        catch(IOException ioe)
        {
            
        }
        
        
    }//GEN-LAST:event_refreshClientButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[])throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientNodeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextArea chatTA;
    private javax.swing.JLabel connErrLabel;
    private javax.swing.JButton connectClientButton;
    private javax.swing.JButton connectRouterButton;
    private javax.swing.JButton disconnButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshClientButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel routerErrLabel;
    private javax.swing.JTextField routerIPTF;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel styleErrLabel;
    private java.awt.TextArea textArea1;
    private javax.swing.JTextField textToSend;
    private javax.swing.JLabel uniqueErrLabel;
    private javax.swing.JList userJList;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
