package SubMenu.Popup.Laporan;

import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import koneksi.Connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class popup_detailLaporanPemasukan extends javax.swing.JFrame {

    private String namaPaket;
    private String tanggalTransaksi;

    public popup_detailLaporanPemasukan(String namaPaket, String tanggalTransaksi) {
        initComponents();
        this.namaPaket = namaPaket;
        this.tanggalTransaksi = tanggalTransaksi;

        initComponents();
        loadData();
        setBackground(new Color(0, 0, 0, 0));
    }

    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    private void loadData() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = (Connection) Connect.GetConnection();

            String sql = "select nama_pelanggan, nope_pelanggan, alamat_pelanggan, total_qty, tipe_paket, "
                    + "jumlah_satuan, tgl_transaksi, estimasi_pengambilan, total, tunai, kembalian, nama, "
                    + "transaksi.id_transaksi from transaksi "
                    + "join pelanggan on transaksi.id_pelanggan = pelanggan.id_pelanggan "
                    + "join detail_transaksi on detail_transaksi.id_transaksi = transaksi.id_transaksi "
                    + "join detail_paket on detail_transaksi.id_detailPaket = detail_paket.id_detailPaket "
                    + "join paket on paket.id_paket = detail_paket.id_paket "
                    + "join akun on transaksi.id_akun = akun.id_akun "
                    + "WHERE paket.tipe_paket = ? AND date(transaksi.tgl_transaksi) = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, namaPaket);
            statement.setString(2, tanggalTransaksi);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                kasir.setText(resultSet.getString("nama"));
                id_transaksi.setText(resultSet.getString("id_transaksi"));
                nama_customer.setText(resultSet.getString("nama_pelanggan"));
                notelp.setText(resultSet.getString("nope_pelanggan"));
                alamat.setText(resultSet.getString("alamat_pelanggan"));
                paket.setText(resultSet.getString("tipe_paket"));
                jumlah_satuan.setText(resultSet.getString("jumlah_satuan")
                        + (resultSet.getString("tipe_paket").equals("Satuan") ? " Pcs" : " Kg"));

                // Reformat tanggal pesan sesuai dengan format "dd MMMM yyyy"
                String tglPesan = resultSet.getString("tgl_transaksi");
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                try {
                    Date parsedDate = inputDateFormat.parse(tglPesan);
                    tglPesan = outputDateFormat.format(parsedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tanggal_pesan.setText(tglPesan);

                total_item.setText(resultSet.getString("total_qty") + " Pcs");
                total_harga.setText("Rp. " + formatDec(resultSet.getInt("total")));
                bayar.setText("Rp. " + formatDec(resultSet.getInt("tunai")));
                kembalian.setText("Rp. " + formatDec(resultSet.getInt("kembalian")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kasir = new javax.swing.JLabel();
        id_transaksi = new javax.swing.JLabel();
        btn_tampil_nota = new javax.swing.JLabel();
        btn_close = new javax.swing.JLabel();
        nama_customer = new javax.swing.JLabel();
        notelp = new javax.swing.JLabel();
        alamat = new javax.swing.JLabel();
        paket = new javax.swing.JLabel();
        jumlah_satuan = new javax.swing.JLabel();
        tanggal_pesan = new javax.swing.JLabel();
        total_item = new javax.swing.JLabel();
        total_harga = new javax.swing.JLabel();
        bayar = new javax.swing.JLabel();
        kembalian = new javax.swing.JLabel();
        popUp_detail_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kasir.setFont(new java.awt.Font("Poppins Medium", 1, 18)); // NOI18N
        kasir.setText("Admin");
        getContentPane().add(kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 127, 130, 20));

        id_transaksi.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        id_transaksi.setText("TR002");
        getContentPane().add(id_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 152, 130, 20));

        btn_tampil_nota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tampil_nota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tampil_notaMouseClicked(evt);
            }
        });
        getContentPane().add(btn_tampil_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(734, 658, 159, 35));

        btn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
        });
        getContentPane().add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 123, 35, 35));

        nama_customer.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        nama_customer.setText("jLabel1");
        getContentPane().add(nama_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 210, 20));

        notelp.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        notelp.setText("jLabel1");
        getContentPane().add(notelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 210, 20));

        alamat.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        alamat.setText("jLabel1");
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 210, 20));

        paket.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        paket.setText("jLabel1");
        getContentPane().add(paket, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 406, 210, 20));

        jumlah_satuan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jumlah_satuan.setText("jLabel1");
        getContentPane().add(jumlah_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 439, 210, 20));

        tanggal_pesan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        tanggal_pesan.setText("jLabel1");
        getContentPane().add(tanggal_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 472, 210, 20));

        total_item.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        total_item.setText("jLabel1");
        getContentPane().add(total_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 505, 210, 20));

        total_harga.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        total_harga.setText("jLabel1");
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 538, 210, 20));

        bayar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        bayar.setText("jLabel1");
        getContentPane().add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 571, 210, 20));

        kembalian.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        kembalian.setText("jLabel1");
        getContentPane().add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 604, 210, 20));

        popUp_detail_bg.setBackground(new Color(0,0,0,0));
        popUp_detail_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/detail_transaksi.png"))); // NOI18N
        getContentPane().add(popUp_detail_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        setSize(new java.awt.Dimension(1365, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btn_closeMouseClicked

    private void btn_tampil_notaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tampil_notaMouseClicked
        try {
            Connection conn = Connect.GetConnection();

            Linear barcode = new Linear();
            barcode.setType(Linear.CODE39);
            barcode.setData(id_transaksi.getText());
            String fName = id_transaksi.getText();
            barcode.renderBarcode("src/assets/img/barcode/" + fName + ".png");

            String report = ("E:\\KULIAH\\Semester 2\\Project Tugas Akhir\\StayClean\\src\\SubMenu\\Nota\\NotaTransaksi.jrxml");
            HashMap hash = new HashMap();
            hash.put("id_transaksi", id_transaksi.getText());
            hash.put("path", "src/assets/img/barcode/" + fName + ".png");
            JasperReport jasper = JasperCompileManager.compileReport(report);
            JasperPrint jasperP = JasperFillManager.fillReport(jasper, hash, conn);
//        JasperViewer.viewReport(jasperP, false); // untuk liat preview
            JasperPrintManager.printReport(jasperP, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_tampil_notaMouseClicked

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
            java.util.logging.Logger.getLogger(popup_detailLaporanPemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popup_detailLaporanPemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popup_detailLaporanPemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popup_detailLaporanPemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new popup_detailLaporanPemasukan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alamat;
    private javax.swing.JLabel bayar;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel btn_tampil_nota;
    private javax.swing.JLabel id_transaksi;
    private javax.swing.JLabel jumlah_satuan;
    private javax.swing.JLabel kasir;
    private javax.swing.JLabel kembalian;
    private javax.swing.JLabel nama_customer;
    private javax.swing.JLabel notelp;
    private javax.swing.JLabel paket;
    private javax.swing.JLabel popUp_detail_bg;
    private javax.swing.JLabel tanggal_pesan;
    private javax.swing.JLabel total_harga;
    private javax.swing.JLabel total_item;
    // End of variables declaration//GEN-END:variables
}
