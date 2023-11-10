package view;

public class MainView extends javax.swing.JFrame {

    public MainView() {
        initComponents();
        loadGraphicResources();
        setLocationRelativeTo(null);
    }

    private void loadGraphicResources() {
        playBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/videojuego.png")));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info.png")));
        btnScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/trophy.png")));
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settings.png")));
        btnExitGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close_game.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        playBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnExitGame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();
        btnScore = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(49, 96, 71));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playBtn.setBackground(new java.awt.Color(255, 255, 255));
        playBtn.setFont(new java.awt.Font("Informal Roman", 0, 48)); // NOI18N
        playBtn.setBorder(null);
        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });
        jPanel1.add(playBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 370, 120));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TIC TAC TOE GAME");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 630, -1));

        btnExitGame.setBorder(null);
        btnExitGame.setBorderPainted(false);
        btnExitGame.setContentAreaFilled(false);
        jPanel1.add(btnExitGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 100, 80));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("V 1.0");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 520, 50, 50));

        playerName.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        playerName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(playerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 290, 40));

        btnInfo.setBorder(null);
        btnInfo.setBorderPainted(false);
        btnInfo.setContentAreaFilled(false);
        jPanel1.add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 100, 80));

        btnScore.setBorder(null);
        btnScore.setBorderPainted(false);
        btnScore.setContentAreaFilled(false);
        jPanel1.add(btnScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 100, 80));

        btnSettings.setBorder(null);
        btnSettings.setBorderPainted(false);
        btnSettings.setContentAreaFilled(false);
        jPanel1.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 100, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playBtnActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnExitGame;
    public javax.swing.JButton btnInfo;
    public javax.swing.JButton btnScore;
    public javax.swing.JButton btnSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton playBtn;
    public javax.swing.JLabel playerName;
    // End of variables declaration//GEN-END:variables
}
