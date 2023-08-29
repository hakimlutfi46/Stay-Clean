/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MainMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author ldzorph
 */
public class SplashScreen extends javax.swing.JFrame {

    Timer timer;
    ActionListener action;

    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        initComponents();
        aksi();
        timer = new Timer(62, action);
        timer.start();
        panelRound2.setVisible(false);
    }

    private void aksi() {
        action = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                progres.setValue(progres.getValue() + 1);
                progres.setStringPainted(true);
                if (progres.getPercentComplete() == 1.0) {
                    timer.stop();
                    panelRound2.setVisible(true);
                }
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new utility.custom_panel.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        progres = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new Color(255,255,255)
        );
        panelRound2.setRoundBottomLeft(90);
        panelRound2.setRoundBottomRight(90);
        panelRound2.setRoundTopLeft(90);
        panelRound2.setRoundTopRight(90);
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelRound2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelRound2MouseExited(evt);
            }
        });
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new Color(0,0,0,0)
        );
        jLabel3.setFont(new java.awt.Font("Poppins ExtraBold", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 144, 186));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mulai");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        panelRound2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, 250, 60));

        getContentPane().add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 563, 260, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Splash Screen SC Netbeans GIF.gif"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -30, 1366, 768));
        getContentPane().add(progres, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 705, 940, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseEntered
        panelRound2.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_panelRound2MouseEntered

    private void panelRound2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseExited
        panelRound2.setBackground(Color.white);
    }//GEN-LAST:event_panelRound2MouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new LoginPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        panelRound2.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        panelRound2.setBackground(Color.white);
    }//GEN-LAST:event_jLabel3MouseExited

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
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private utility.custom_panel.PanelRound panelRound2;
    private javax.swing.JProgressBar progres;
    // End of variables declaration//GEN-END:variables
}
