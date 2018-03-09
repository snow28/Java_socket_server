
package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Main extends javax.swing.JFrame {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
    static int posY = 4;
    static int posX = 4;
    static int[] indexHelp = {0,10,20,30,40,50,60,70};  // we will use our array to indicate numbe of char we have to change during changing state of game
    int minY = 2;
    int minX = 2;
    
    String startPoint = ""
                + "XXXXXXXXX\n"
                + "XXXXXXXXX\n"
                + "XXoooooXX\n"
                + "XXmooooXX\n"
                + "XXoopooXX\n"
                + "XXoooooXX\n"
                + "XXoooooXX\n"
                + "XXXXXXXXX\n";
    
    
    
    public Main() {
        initComponents();
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        consoleWindow = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        consoleWindow.setColumns(20);
        consoleWindow.setRows(5);
        jScrollPane1.setViewportView(consoleWindow);

        jButton1.setText("Test button");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try{
            dos.writeUTF(startPoint);
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        // we will use it to start our game
        StringBuilder patternStart = new StringBuilder(""
                + "XXXXXXXXX\n"
                + "XXXXXXXXX\n"
                + "XXnooooXX\n"
                + "XXoooooXX\n"
                + "XXoopooXX\n"
                + "XXoooooXX\n"
                + "XXoooooXX\n"
                + "XXXXXXXXX\n");
        StringBuilder patternGame = new StringBuilder(""
                + "XXXXXXXXX\n"
                + "XXXXXXXXX\n"
                + "XXnooooXX\n"
                + "XXoooooXX\n"
                + "XXoopooXX\n"
                + "XXoooooXX\n"
                + "XXoooooXX\n"
                + "XXXXXXXXX\n");
        
        char[][] gameObject = {
            {'X','X','X','X','X','X','X','X','X'},
            {'X','X','X','X','X','X','X','X','X'},
            {'X','X','o','o','o','o','o','X','X'},
            {'X','X','n','o','o','o','o','X','X'},
            {'X','X','o','o','p','o','o','X','X'},
            {'X','X','o','o','o','o','o','X','X'},
            {'X','X','o','o','o','o','o','X','X'},
            {'X','X','X','X','X','X','X','X','X'}
        };
        
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
        
        try{
            ss = new ServerSocket(1201);
            s = ss.accept();
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            String msgin = "";
            while(!msgin.equals("exit")){
                msgin = "";
                msgin = dis.readUTF();
                if(msgin.equals("start")){
                    dos.writeUTF(patternStart.toString());
                    patternGame = patternStart;
                }else if(msgin.equals("top")){
                    for(int x=0; x<5; x++){
                        if(indexHelp[posY+2]+(posX-2)+x >= indexHelp[posY+2] && indexHelp[posY+2]+(posX-2)+x <= indexHelp[posY+3]-2){
                            patternGame.setCharAt(indexHelp[posY+2]+(posX-2)+x, 'X');
                        }
                    }
                    
                    patternGame.setCharAt(indexHelp[posY]+posX, 'o');
                    if(posY >= 1){
                        posY--;
                    }
                    System.out.println("last Top. " + " X : " + posX + ". Y + :" + posY);
                }else if(msgin.equals("left")){
                    if(posX >=1){
                        posX--;
                    }
                    System.out.println("last left. " + " X : " + posX + ". Y + :" + posY);
                }else if(msgin.equals("right")){
                    if(posX <=7){
                        posX++;
                    }
                    System.out.println("last right. " + " X : " + posX + ". Y + :" + posY);
                }else if(msgin.equals("bottom")){
                    if(posY <=6){
                        posY++;
                    }
                    System.out.println("last bottom. " + " X : " + posX + ". Y + :" + posY);
                }
                    
                if(posX == 0 || posY == 0 || posX == 8 || posY == 7){
                    dos.writeUTF("alert");
                }else{
                    dos.writeUTF("aflert-delete");
                }
                

                // Here we will draw and send our game
                patternGame.setCharAt(indexHelp[posY]+posX, 'p');
                
                
                
                
                
                dos.writeUTF(patternGame.toString());
                consoleWindow.setText(consoleWindow.getText().trim() + "\n" + msgin + "\n");
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea consoleWindow;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
