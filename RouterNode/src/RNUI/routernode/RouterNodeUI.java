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

import java.io.*;
import java.net.*;

public class RouterNodeUI extends javax.swing.JFrame {
    
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
    public static final String RAMN_REQUEST_IP = "RQIP";
    
    RoutingTableManager rtManager = new RoutingTableManager();
    /**
     * Creates new form RouterNodeUI
     */
    
    public RouterNodeUI() {
        
        initComponents();
        invalidPortErr.setVisible(false);
        invalidConnPortErr.setVisible(false);
        invalidIPErr.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startListenButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        listenPortTF = new javax.swing.JTextField();
        invalidPortErr = new javax.swing.JLabel();
        connectPanel = new javax.swing.JPanel();
        startConnectButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        connPortTF = new javax.swing.JTextField();
        invalidConnPortErr = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ipTF = new javax.swing.JTextField();
        invalidIPErr = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        activeUsersTA = new java.awt.TextArea();
        listenClientButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RAMN_Router");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 155, 0)), "Listen for Router"));

        startListenButton.setText("Start");
        startListenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startListenButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Listening Port: ");

        invalidPortErr.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        invalidPortErr.setForeground(new java.awt.Color(255, 0, 0));
        invalidPortErr.setText("*invalid port, assigned to port 5555");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(startListenButton))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listenPortTF)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(invalidPortErr, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(listenPortTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidPortErr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(startListenButton))
        );

        connectPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 155, 0)), "Connect To Router"));

        startConnectButton.setText("Start");
        startConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startConnectButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("IP Address:");

        invalidConnPortErr.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        invalidConnPortErr.setForeground(new java.awt.Color(255, 0, 0));
        invalidConnPortErr.setText("*invalid port, assigned to port 5555");

        jLabel3.setText("Port #:");

        invalidIPErr.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        invalidIPErr.setForeground(new java.awt.Color(255, 0, 0));
        invalidIPErr.setText("*IP address not found. Please try again.");

        javax.swing.GroupLayout connectPanelLayout = new javax.swing.GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(connectPanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(connectPanelLayout.createSequentialGroup()
                                        .addComponent(startConnectButton)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(connectPanelLayout.createSequentialGroup()
                                        .addComponent(invalidConnPortErr, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(connectPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connPortTF))))
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invalidIPErr)
                            .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(142, Short.MAX_VALUE))))
        );
        connectPanelLayout.setVerticalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidIPErr)
                .addGap(18, 18, 18)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(connPortTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidConnPortErr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startConnectButton))
        );

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 155, 0)), "Active Connections"));

        listenClientButton.setText("Listen for Clients");
        listenClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenClientButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listenClientButton, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(activeUsersTA, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listenClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(activeUsersTA, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exitButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void startListenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startListenButtonActionPerformed
        int listenPort;
        try
        {
            listenPort = Integer.parseInt(listenPortTF.getText());
        }
        catch(NumberFormatException nfe)
        {    
            invalidPortErr.setVisible(true);
            listenPort = 5555;
        }
        
        if(listenPort <1024 || listenPort > 65534)
        {
            invalidPortErr.setVisible(true);
            listenPort = 5555;
        }
        
        RouterListenerThread rlt = new RouterListenerThread("ListenForRouter", listenPort, rtManager.routingTable);
        rlt.listenStart();
    }//GEN-LAST:event_startListenButtonActionPerformed

    public static Socket routerConnSocket = null;
    private void startConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startConnectButtonActionPerformed
        int connPort;
        String ipAddress = null;
        
        try
        {
            connPort = Integer.parseInt(connPortTF.getText());
            ipAddress = ipTF.getText();
        }
        catch(NumberFormatException nfe)
        {    
            invalidConnPortErr.setVisible(true);
            connPort = 5555;
        }
        
        if(connPort <1024 || connPort > 65534)
        {
            invalidConnPortErr.setVisible(true);
            connPort = 5555;
        }
        
        try
        {
            routerConnSocket = new Socket(ipAddress, connPort);
        }
        catch(IOException ioe)
        {
            invalidIPErr.setVisible(true);
        }
        catch(IllegalArgumentException iae)
        {
            invalidConnPortErr.setVisible(true);
        }
    }//GEN-LAST:event_startConnectButtonActionPerformed

    ClientListenerThread clt = null;
    private void listenClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listenClientButtonActionPerformed
        //start a new thread to listen for clients
        /*if(clt != null)
        {
            clt.setName("Client-Listening-Thread");
            clt.setListenPort(5555);
            clt.setRoutingTable(rtManager.routingTable);
            
        }
        else
        {
            clt = new ClientListenerThread("Client-Listening-Thread", 5555, rtManager.routingTable);
            
            if(routerConnSocket != null)
            {
                clt.setNeighborSocket(routerConnSocket);
            }
        }*/
        clt = new ClientListenerThread("Client-Listening-Thread", 5555, rtManager.routingTable, routerConnSocket);
        clt.listenStart();
    }//GEN-LAST:event_listenClientButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        activeUsersTA.setText("");
        for(int i = 0; i < rtManager.routingTable.size(); i++)
        {
            activeUsersTA.append(rtManager.routingTable.get(i).getUsername() + '\n');
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(RouterNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RouterNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RouterNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RouterNodeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RouterNodeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextArea activeUsersTA;
    private javax.swing.JTextField connPortTF;
    private javax.swing.JPanel connectPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel invalidConnPortErr;
    private javax.swing.JLabel invalidIPErr;
    private javax.swing.JLabel invalidPortErr;
    private javax.swing.JTextField ipTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton listenClientButton;
    private javax.swing.JTextField listenPortTF;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton startConnectButton;
    private javax.swing.JButton startListenButton;
    // End of variables declaration//GEN-END:variables
}
