/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup.Transaksi;

import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import koneksi.Connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author rayrayaray
 */
public class PopupPengambilanKetik extends javax.swing.JFrame {

    JDesktopPane pane;

    /**
     * Creates new form PopupPengambilanKetik
     */
    public PopupPengambilanKetik() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        System.out.println(this.pane);
    }

    public void setPane(JDesktopPane pane) {
        this.pane = pane;
    }

    private ResultSet readDb(String sql) {
        ResultSet result = null;

        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            result = res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private void updateDb(String sql) {
        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void numberOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^0-9SC]", ""));
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
        btn_back = new utility.custom_panel.PanelRound();
        txt_idTransaksi = new javax.swing.JTextField();
        btn_konfirmasi = new javax.swing.JPanel();
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
            .addGap(0, 31, Short.MAX_VALUE)
        );
        btn_exitLayout.setVerticalGroup(
            btn_exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        getContentPane().add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 194, 31, 31));

        btn_back.setBackground(new Color(0,0,0,0)
        );
        btn_back.setRoundBottomLeft(20);
        btn_back.setRoundBottomRight(20);
        btn_back.setRoundTopLeft(20);
        btn_back.setRoundTopRight(20);
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_backLayout = new javax.swing.GroupLayout(btn_back);
        btn_back.setLayout(btn_backLayout);
        btn_backLayout.setHorizontalGroup(
            btn_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        btn_backLayout.setVerticalGroup(
            btn_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 194, 30, 30));

        txt_idTransaksi.setBackground(new Color(0,0,0,0)
        );
        txt_idTransaksi.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txt_idTransaksi.setBorder(null);
        txt_idTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idTransaksiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idTransaksiKeyTyped(evt);
            }
        });
        getContentPane().add(txt_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 469, 263, 26));

        btn_konfirmasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_konfirmasi.setOpaque(false);
        btn_konfirmasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_konfirmasiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_konfirmasiLayout = new javax.swing.GroupLayout(btn_konfirmasi);
        btn_konfirmasi.setLayout(btn_konfirmasiLayout);
        btn_konfirmasiLayout.setHorizontalGroup(
            btn_konfirmasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        btn_konfirmasiLayout.setVerticalGroup(
            btn_konfirmasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        getContentPane().add(btn_konfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 514, 286, 31));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Konfirmasi Pengambilan - Ketik Kode.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        PopupKonfirmasiPengambilan pkp = new PopupKonfirmasiPengambilan();
        pkp.setPane(this.pane);
        pkp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backMouseClicked

    private void btn_konfirmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_konfirmasiMouseClicked
        try {
            //ambil nama rak
            ResultSet res = readDb("SELECT transaksi.id_transaksi, nama_rak, status_pembayaran FROM transaksi\n"
                    + "JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi\n"
                    + "JOIN rak ON detail_transaksi.id_rak = rak.id_rak WHERE transaksi.id_transaksi = '" + txt_idTransaksi.getText() + "';");
            StringBuilder stringBuilder = new StringBuilder();
            while (res.next()) {
                updateDb("update rak status = 'Kosong' where nama_rak = '" + res.getString("nama_rak") + "'");
                stringBuilder.append(res.getString("nama_rak")).append(", ");
            }
            String rak = stringBuilder.toString();
            if (rak.endsWith(",")) {
                rak = rak.substring(0, rak.length() - 1);
            }
            System.out.println(rak);

            //cek pembayaran
            ResultSet result = readDb("SELECT transaksi.id_transaksi, nama_rak, status_pembayaran, status_pesanan FROM transaksi\n"
                    + "JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi\n"
                    + "JOIN rak ON detail_transaksi.id_rak = rak.id_rak WHERE transaksi.id_transaksi = '" + txt_idTransaksi.getText() + "';");
            if (result.next()) {
                if (result.getString("status_pembayaran").equals("Belum Lunas") && result.getString("status_pesanan").equals("Belum Diambil")) {
                    new PopupKonfirmasiBelumBayar(result.getString("id_transaksi"), this.pane).setVisible(true);
                    this.dispose();
                } else if (result.getString("status_pembayaran").equals("Lunas") && result.getString("status_pesanan").equals("Belum Diambil")) {
                    updateDb("update transaksi set status_pembayaran = 'Lunas', status_pesanan = 'Sudah Diambil', tgl_pengambilan = now() where id_transaksi = '" + result.getString("id_transaksi") + "'");

                    Connection conn = Connect.GetConnection();

                    Linear barcode = new Linear();
                    barcode.setType(Linear.CODE39);
                    barcode.setData(result.getString("id_transaksi"));
                    String fName = result.getString("id_transaksi");
                    barcode.renderBarcode("src/assets/img/barcode/" + fName + ".png");

                    String report = ("E:\\Project Kelompok 3 Semester 2\\Stay Clean_FIX\\StayClean\\src\\SubMenu\\Nota\\NotaTransaksi.jrxml");
                    HashMap hash = new HashMap();
                    hash.put("id_transaksi", result.getString("id_transaksi"));
                    hash.put("path", "src/assets/img/barcode/" + fName + ".png");
                    JasperReport jasper = JasperCompileManager.compileReport(report);
                    JasperPrint jasperP = JasperFillManager.fillReport(jasper, hash, conn);

//               JasperViewer.viewReport(jasperP, false); // untuk liat preview
                    JasperPrintManager.printReport(jasperP, false);

                    new PopupSukses("Data berhasil diperbarui<br>Silahkan ambil laundry pada " + rak, "dataTransaksi", this.pane).setVisible(true);
                    this.dispose();
                } else {
                    new PopupGagal("Data laundry tidak ditemukan").setVisible(true);
                    txt_idTransaksi.setText("");
                    txt_idTransaksi.requestFocus();
                }
            } else {
                txt_idTransaksi.setText("");
                txt_idTransaksi.requestFocus();
                new PopupGagal("Data tidak ditemukan").setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_konfirmasiMouseClicked

    private void txt_idTransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idTransaksiKeyTyped
        if (txt_idTransaksi.getText().length() >= 7) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_idTransaksiKeyTyped

    private void txt_idTransaksiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idTransaksiKeyReleased
        numberOnly(txt_idTransaksi);
        txt_idTransaksi.setText(txt_idTransaksi.getText().replaceAll("\\s+", ""));
    }//GEN-LAST:event_txt_idTransaksiKeyReleased

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
            java.util.logging.Logger.getLogger(PopupPengambilanKetik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupPengambilanKetik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupPengambilanKetik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupPengambilanKetik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupPengambilanKetik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private utility.custom_panel.PanelRound btn_back;
    private utility.custom_panel.PanelRound btn_exit;
    private javax.swing.JPanel btn_konfirmasi;
    private javax.swing.JTextField txt_idTransaksi;
    // End of variables declaration//GEN-END:variables
}
