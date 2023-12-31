/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup.Karyawan;

import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JDesktopPane;
import koneksi.Connect;

/**
 *
 * @author rayrayaray
 */
public class PopupKonfirmasi extends javax.swing.JFrame {

    String idAkun;
    JDesktopPane pane;

    /**
     * Creates new form PopupKonfirmasi
     */
    public PopupKonfirmasi() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
    }

    public PopupKonfirmasi(String pesan, String id, JDesktopPane desktopPane) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        lbl_pesan.setText("<html><center>" + pesan + "</center></html>");
        this.idAkun = id;
        this.pane = desktopPane;
    }

    private void deleteDb(String sql) {
        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_batal = new javax.swing.JPanel();
        btn_oke = new javax.swing.JPanel();
        lbl_pesan = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batal.setOpaque(false);
        btn_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_batalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_batalLayout = new javax.swing.GroupLayout(btn_batal);
        btn_batal.setLayout(btn_batalLayout);
        btn_batalLayout.setHorizontalGroup(
            btn_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        btn_batalLayout.setVerticalGroup(
            btn_batalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 482, 186, 38));

        btn_oke.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_oke.setOpaque(false);
        btn_oke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_okeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_okeLayout = new javax.swing.GroupLayout(btn_oke);
        btn_oke.setLayout(btn_okeLayout);
        btn_okeLayout.setHorizontalGroup(
            btn_okeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        btn_okeLayout.setVerticalGroup(
            btn_okeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_oke, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 482, 186, 38));

        lbl_pesan.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        lbl_pesan.setForeground(new java.awt.Color(0, 0, 0));
        lbl_pesan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pesan.setText("Title Here");
        getContentPane().add(lbl_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 360, 500, 100));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Template Pop up konfirmasi.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseClicked
        try {
            deleteDb("delete from akun where id_akun = '" + this.idAkun + "'");
            this.dispose();
            new PopupSukses("Data berhasil dihapus", "karyawan", this.pane).setVisible(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            new PopupGagal("Data gagal dihapus").setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_okeMouseClicked

    private void btn_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_batalMouseClicked

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
            java.util.logging.Logger.getLogger(PopupKonfirmasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupKonfirmasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel btn_batal;
    private javax.swing.JPanel btn_oke;
    private javax.swing.JLabel lbl_pesan;
    // End of variables declaration//GEN-END:variables
}
