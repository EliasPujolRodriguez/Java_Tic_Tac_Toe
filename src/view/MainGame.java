package view;

import com.formdev.flatlaf.FlatClientProperties;

public class MainGame extends javax.swing.JFrame {

    public MainGame() {
        initComponents();
        loadGraphicResources();
        setLocationRelativeTo(null);
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        player1.putClientProperty(FlatClientProperties.STYLE, "arc: 8");
        player2.putClientProperty(FlatClientProperties.STYLE, "arc: 8");

    }

    private void loadGraphicResources() {
        /*a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
        a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
        a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
        a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
        a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
        a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
        a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
        a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
        a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
         */
        vs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/player_versus_player.png")));
        btnReturnMainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/return.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        a1 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        a3 = new javax.swing.JLabel();
        a4 = new javax.swing.JLabel();
        a5 = new javax.swing.JLabel();
        a6 = new javax.swing.JLabel();
        a7 = new javax.swing.JLabel();
        a8 = new javax.swing.JLabel();
        a9 = new javax.swing.JLabel();
        player1 = new javax.swing.JPanel();
        iconPlayerType1 = new javax.swing.JLabel();
        playerScore = new javax.swing.JLabel();
        player2 = new javax.swing.JPanel();
        iconPlayerType2 = new javax.swing.JLabel();
        pcScore = new javax.swing.JLabel();
        vs = new javax.swing.JLabel();
        btnReturnMainMenu = new javax.swing.JButton();
        playerName2 = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        shift2 = new javax.swing.JLabel();
        shift1 = new javax.swing.JLabel();
        levelNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(49, 96, 71));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(232, 160, 98));
        jPanel3.setLayout(new java.awt.GridLayout(3, 3));

        a1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a1.setIconTextGap(250);
        jPanel3.add(a1);

        a2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a2.setIconTextGap(250);
        jPanel3.add(a2);

        a3.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a3.setIconTextGap(250);
        jPanel3.add(a3);

        a4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a4.setIconTextGap(250);
        jPanel3.add(a4);

        a5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a5.setIconTextGap(250);
        jPanel3.add(a5);

        a6.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a6.setIconTextGap(250);
        jPanel3.add(a6);

        a7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a7.setIconTextGap(250);
        jPanel3.add(a7);

        a8.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a8.setIconTextGap(250);
        jPanel3.add(a8);

        a9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(155, 106, 54)));
        a9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        a9.setIconTextGap(250);
        jPanel3.add(a9);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 68, 536, 385));

        playerScore.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        playerScore.setText("0");

        javax.swing.GroupLayout player1Layout = new javax.swing.GroupLayout(player1);
        player1.setLayout(player1Layout);
        player1Layout.setHorizontalGroup(
            player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconPlayerType1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player1Layout.createSequentialGroup()
                    .addContainerGap(93, Short.MAX_VALUE)
                    .addComponent(playerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17)))
        );
        player1Layout.setVerticalGroup(
            player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconPlayerType1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(playerScore, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
        );

        jPanel1.add(player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 540, -1, -1));

        pcScore.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        pcScore.setText("0");

        javax.swing.GroupLayout player2Layout = new javax.swing.GroupLayout(player2);
        player2.setLayout(player2Layout);
        player2Layout.setHorizontalGroup(
            player2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconPlayerType2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(pcScore, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        player2Layout.setVerticalGroup(
            player2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconPlayerType2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pcScore, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        jPanel1.add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 540, -1, -1));
        jPanel1.add(vs, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 84, 121));

        btnReturnMainMenu.setBorder(null);
        btnReturnMainMenu.setBorderPainted(false);
        btnReturnMainMenu.setContentAreaFilled(false);
        jPanel1.add(btnReturnMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 700, 152, 70));

        playerName2.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        playerName2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(playerName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 190, 30));

        playerName.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        playerName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(playerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 190, 30));

        shift2.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        shift2.setForeground(new java.awt.Color(51, 153, 255));
        jPanel1.add(shift2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 680, 190, 30));

        shift1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        shift1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(shift1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 680, 190, 30));

        levelNumber.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        levelNumber.setForeground(new java.awt.Color(255, 255, 255));
        levelNumber.setText("LEVEL");
        jPanel1.add(levelNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 150, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel a1;
    public javax.swing.JLabel a2;
    public javax.swing.JLabel a3;
    public javax.swing.JLabel a4;
    public javax.swing.JLabel a5;
    public javax.swing.JLabel a6;
    public javax.swing.JLabel a7;
    public javax.swing.JLabel a8;
    public javax.swing.JLabel a9;
    public javax.swing.JButton btnReturnMainMenu;
    private javax.swing.JLabel iconPlayerType1;
    private javax.swing.JLabel iconPlayerType2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JLabel levelNumber;
    public javax.swing.JLabel pcScore;
    public javax.swing.JPanel player1;
    public javax.swing.JPanel player2;
    public javax.swing.JLabel playerName;
    public javax.swing.JLabel playerName2;
    public javax.swing.JLabel playerScore;
    public javax.swing.JLabel shift1;
    public javax.swing.JLabel shift2;
    public javax.swing.JLabel vs;
    // End of variables declaration//GEN-END:variables
}
