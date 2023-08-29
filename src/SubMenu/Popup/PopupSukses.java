/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup;

import SubMenu.DashboardFrame;
import SubMenu.DataPaketFrame;
import SubMenu.DataTransaksiFrame;
import SubMenu.KaryawanFrame;
import SubMenu.LaporanFrame;
import SubMenu.LaporanPengeluaranNew;
import SubMenu.PelangganFrame;
import SubMenu.TransaksiFrame;
import java.awt.Color;
import javax.swing.JDesktopPane;

/**
 *
 * @author rayrayaray
 */
public class PopupSukses extends javax.swing.JFrame {

    String pilihanLayout = "";
    JDesktopPane pane;

    /**
     * Creates new form PopupSukses
     */
    public PopupSukses() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
    }

    public PopupSukses(String pesan, String pilihanLayout, JDesktopPane pane) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        lbl_pesan.setText("<html><center>" + pesan + "</center></html>");
        this.pilihanLayout = pilihanLayout;
        this.pane = pane;
    }

    public PopupSukses(String pesan, String pilihanLayout) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        lbl_pesan.setText("<html><center>" + pesan + "</center></html>");
        this.pilihanLayout = pilihanLayout;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_pesan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_pesan.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        lbl_pesan.setForeground(new java.awt.Color(0, 0, 0));
        lbl_pesan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pesan.setText("Title Here");
        getContentPane().add(lbl_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 360, 500, 100));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setOpaque(false);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 186, 38));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Template Pop up sukses.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if (this.pilihanLayout.equals("dashboard")) {
            DashboardFrame frame = new DashboardFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("transaksi")) {
            TransaksiFrame frame = new TransaksiFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("pelanggan")) {
            PelangganFrame frame = new PelangganFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("dataPaket")) {
            DataPaketFrame frame = new DataPaketFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("laporan")) {
            LaporanFrame frame = new LaporanFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("laporanPengeluaran")) {
            LaporanPengeluaranNew frame = new LaporanPengeluaranNew();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("dataTransaksi")) {
            DataTransaksiFrame frame = new DataTransaksiFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else if (this.pilihanLayout.equals("karyawan")) {
            KaryawanFrame frame = new KaryawanFrame();
            this.pane.removeAll();
            this.pane.add(frame);
            frame.setVisible(true);
            this.dispose();
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jPanel1MouseClicked

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
            java.util.logging.Logger.getLogger(PopupSukses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupSukses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupSukses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupSukses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupSukses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_pesan;
    // End of variables declaration//GEN-END:variables
}