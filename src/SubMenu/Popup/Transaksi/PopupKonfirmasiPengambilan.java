/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup.Transaksi;

import java.awt.Color;
import javax.swing.JDesktopPane;

/**
 *
 * @author rayrayaray
 */
public class PopupKonfirmasiPengambilan extends javax.swing.JFrame {

    JDesktopPane pane;

    /**
     * Creates new form PopupKonfirmasiPengambilan
     */
    public PopupKonfirmasiPengambilan() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
    }

    public void setPane(JDesktopPane pane) {
        this.pane = pane;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_exit = new utility.custom_panel.PanelRound();
        btn_scan = new javax.swing.JPanel();
        btn_ketik = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_exit.setBackground(new Color(0,0,0,0));
        btn_exit.setRoundBottomLeft(50);
        btn_exit.setRoundBottomRight(50);
        btn_exit.setRoundTopLeft(50);
        btn_exit.setRoundTopRight(50);
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_exitLayout = new javax.swing.GroupLayout(btn_exit);
        btn_exit.setLayout(btn_exitLayout);
        btn_exitLayout.setHorizontalGroup(
            btn_exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        btn_exitLayout.setVerticalGroup(
            btn_exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        getContentPane().add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(851, 281, 25, 25));

        btn_scan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scan.setOpaque(false);
        btn_scan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_scanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_scanLayout = new javax.swing.GroupLayout(btn_scan);
        btn_scan.setLayout(btn_scanLayout);
        btn_scanLayout.setHorizontalGroup(
            btn_scanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        btn_scanLayout.setVerticalGroup(
            btn_scanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 423, 169, 38));

        btn_ketik.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ketik.setOpaque(false);
        btn_ketik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ketikMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_ketikLayout = new javax.swing.GroupLayout(btn_ketik);
        btn_ketik.setLayout(btn_ketikLayout);
        btn_ketikLayout.setHorizontalGroup(
            btn_ketikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        btn_ketikLayout.setVerticalGroup(
            btn_ketikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_ketik, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 423, 169, 38));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Konfirmasi Pengambilan.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_ketikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ketikMouseClicked
        PopupPengambilanKetik ppk = new PopupPengambilanKetik();
        ppk.setPane(this.pane);
        ppk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_ketikMouseClicked

    private void btn_scanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanMouseClicked
        PopupPengambilanScan pps = new PopupPengambilanScan();
        pps.setPane(this.pane);
        pps.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_scanMouseClicked

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
            java.util.logging.Logger.getLogger(PopupKonfirmasiPengambilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiPengambilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiPengambilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiPengambilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupKonfirmasiPengambilan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private utility.custom_panel.PanelRound btn_exit;
    private javax.swing.JPanel btn_ketik;
    private javax.swing.JPanel btn_scan;
    // End of variables declaration//GEN-END:variables
}
