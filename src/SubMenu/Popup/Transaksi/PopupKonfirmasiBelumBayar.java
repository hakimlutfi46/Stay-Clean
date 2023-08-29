/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup.Transaksi;

import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import com.barcodelib.barcode.Linear;
import java.sql.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import koneksi.Connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author perlengkapan
 */
public class PopupKonfirmasiBelumBayar extends javax.swing.JFrame {

    String idTransaksi;
    JDesktopPane pane;

    /**
     * Creates new form PopupKonfirmasiBelumBayar
     */
    public PopupKonfirmasiBelumBayar() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_detailTransaksi.getTableHeader().setBackground(new Color(0, 168, 209));
    }

    public PopupKonfirmasiBelumBayar(String id, JDesktopPane pane) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        this.idTransaksi = id;
        this.pane = pane;
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_detailTransaksi.getTableHeader().setBackground(new Color(0, 168, 209));
        dataTable();
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

    private String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    private String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    private void preferedWidthTable() {
        TableColumnModel columnModel = tbl_detailTransaksi.getColumnModel();

        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(2).setMaxWidth(120);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setMaxWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
    }

    public void dataTable() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        DefaultTableModel dtm = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        dtm.addColumn("No.");
        dtm.addColumn("Jenis Paket");
        dtm.addColumn("Jumlah Satuan");
        dtm.addColumn("Harga");
        dtm.addColumn("Sub Total");

        int no = 1;
        String satuan = null;
        try {
            ResultSet res = readDb("SELECT transaksi.id_transaksi, tgl_transaksi, nama, nama_pelanggan, nama_paket, jumlah_satuan, tipe_paket, harga_paket, sub_total, total, tunai FROM transaksi \n"
                    + "JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi\n"
                    + "JOIN pelanggan ON transaksi.id_pelanggan = pelanggan.id_pelanggan\n"
                    + "JOIN detail_paket ON detail_transaksi.id_detailPaket = detail_paket.id_detailPaket\n"
                    + "JOIN paket ON paket.id_paket = detail_paket.id_paket\n"
                    + "JOIN akun ON transaksi.id_akun = akun.id_akun WHERE transaksi.id_transaksi = '" + this.idTransaksi + "'");

            while (res.next()) {

                if (res.getString("tipe_paket").equals("Satuan")) {
                    satuan = "Pcs";
                } else {
                    satuan = "Kg";
                }

                lbl_kasir.setText(res.getString("nama"));
                lbl_tanggalTransaksi.setText(res.getString("tgl_transaksi"));
                lbl_namaPelanggan.setText(res.getString("nama_pelanggan"));
                lbl_total.setText(formatDec(Integer.parseInt(res.getString("total"))));
                lbl_sudahDibayar.setText(formatDec(Integer.parseInt(res.getString("tunai"))));

                dtm.addRow(new Object[]{
                    no++,
                    res.getString("nama_paket"),
                    res.getString("jumlah_satuan") + " " + satuan,
                    formatDec(Integer.parseInt(res.getString("harga_paket"))),
                    formatDec(Integer.parseInt(res.getString("sub_total")))
                });

                int sisa = Integer.parseInt(res.getString("total")) - Integer.parseInt(res.getString("tunai"));
                if (sisa < 1) {
                    lbl_sisa.setText("1000");
                } else {
                    lbl_sisa.setText(formatDec(sisa));
                }
            }

        } catch (Exception e) {
            new PopupGagal("Gagal mengambil data table").setVisible(true);
            System.out.println(e.getMessage());
        }

        tbl_detailTransaksi.setModel(dtm);
        preferedWidthTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new utility.custom_panel.PanelRound();
        lbl_total = new javax.swing.JLabel();
        lbl_sudahDibayar = new javax.swing.JLabel();
        lbl_sisa = new javax.swing.JLabel();
        lbl_kembalian = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        lbl_namaPelanggan = new javax.swing.JLabel();
        kasir = new javax.swing.JLabel();
        lbl_kasir = new javax.swing.JLabel();
        lbl_tanggalTransaksi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_detailTransaksi = new javax.swing.JTable();
        btn_konfirmasi = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new Color(0,0,0,0));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 33, 30));

        lbl_total.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        lbl_total.setForeground(new java.awt.Color(0, 0, 0));
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("123456");
        getContentPane().add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 450, 160, 30));

        lbl_sudahDibayar.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        lbl_sudahDibayar.setForeground(new java.awt.Color(0, 0, 0));
        lbl_sudahDibayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_sudahDibayar.setText("123456");
        getContentPane().add(lbl_sudahDibayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 490, 160, 20));

        lbl_sisa.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        lbl_sisa.setForeground(new java.awt.Color(0, 0, 0));
        lbl_sisa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_sisa.setText("123456");
        getContentPane().add(lbl_sisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 530, 160, 20));

        lbl_kembalian.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        lbl_kembalian.setForeground(new java.awt.Color(255, 0, 0));
        lbl_kembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_kembalian.setText("Uang Kurang");
        getContentPane().add(lbl_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 600, 160, 20));

        txt_bayar.setBackground(new Color(0,0,0,0));
        txt_bayar.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        txt_bayar.setForeground(new java.awt.Color(0, 0, 0));
        txt_bayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_bayar.setBorder(null);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 562, 160, 27));

        lbl_namaPelanggan.setFont(new java.awt.Font("Poppins Medium", 1, 20)); // NOI18N
        lbl_namaPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        lbl_namaPelanggan.setText("Muhammad Rayasya Dziqi Cahyana");
        getContentPane().add(lbl_namaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 510, 40));

        kasir.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        kasir.setForeground(new java.awt.Color(0, 0, 0));
        kasir.setText("Kasir :");
        getContentPane().add(kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 195, 50, -1));

        lbl_kasir.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_kasir.setForeground(new java.awt.Color(0, 0, 0));
        lbl_kasir.setText("Admin");
        getContentPane().add(lbl_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 195, -1, -1));

        lbl_tanggalTransaksi.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_tanggalTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        lbl_tanggalTransaksi.setText("12-12-2012 23:59:59");
        getContentPane().add(lbl_tanggalTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, -1));

        tbl_detailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_detailTransaksi.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tbl_detailTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 558, 120));

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
            .addGap(0, 120, Short.MAX_VALUE)
        );
        btn_konfirmasiLayout.setVerticalGroup(
            btn_konfirmasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(btn_konfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 650, 120, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Belum bayar.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        int inputanBayar = Integer.parseInt(reFormat(txt_bayar.getText()));
        txt_bayar.setText(formatDec(inputanBayar));

        int total = Integer.parseInt(reFormat(lbl_sisa.getText()));
        int bayar = Integer.parseInt(reFormat(txt_bayar.getText()));
        if (bayar < total) {
            lbl_kembalian.setText("Uang Kurang");
            lbl_kembalian.setForeground(Color.red);
        } else if (bayar >= total) {
            lbl_kembalian.setForeground(Color.black);
            int kembalian = bayar - total;
            String kembalianfix = String.valueOf(kembalian);
            lbl_kembalian.setText(formatDec(Integer.parseInt(kembalianfix)));
        } else if (txt_bayar.equals("")) {
            lbl_kembalian.setText("Uang Kurang");
            lbl_kembalian.setForeground(Color.red);
        } else {
            lbl_kembalian.setText("0");
            lbl_kembalian.setForeground(Color.black);
        }
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void panelRound1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound1MouseClicked
        this.dispose();
    }//GEN-LAST:event_panelRound1MouseClicked

    private void btn_konfirmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_konfirmasiMouseClicked
        if (lbl_kembalian.getText().equals("Uang Kurang")) {
            new PopupGagal("Silahkan cek kembali uang yang dibayarkan").setVisible(true);
        } else {
            try {
                //ambil nama rak
                ResultSet res = readDb("SELECT transaksi.id_transaksi, nama_rak, status_pembayaran FROM transaksi\n"
                        + "JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi\n"
                        + "JOIN rak ON detail_transaksi.id_rak = rak.id_rak WHERE transaksi.id_transaksi = '" + this.idTransaksi + "';");
                ResultSet result = readDb("SELECT transaksi.id_transaksi, nama_rak, status_pembayaran FROM transaksi\n"
                        + "JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi\n"
                        + "JOIN rak ON detail_transaksi.id_rak = rak.id_rak WHERE transaksi.id_transaksi = '" + this.idTransaksi + "';");

                StringBuilder stringBuilder = new StringBuilder();
                while (res.next()) {
                    updateDb("update rak status = 'Kosong' where nama_rak = '" + res.getString("nama_rak") + "'");
                    stringBuilder.append(res.getString("nama_rak")).append(", ");
                }
                String rak = stringBuilder.toString();
                if (rak.endsWith(",")) {
                    rak = rak.substring(0, rak.length() - 1);
                }

                //update db
                updateDb("update transaksi set tunai = '" + reFormat(lbl_total.getText()) + "', kembalian = '0', status_pembayaran = 'Lunas', status_pesanan = 'Sudah Diambil', tgl_pengambilan = now() where id_transaksi = '" + this.idTransaksi + "'");

                this.dispose();

                if (result.next()) {
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
                }

                new PopupSukses("Data berhasil diperbarui<br>Silahkan ambil laundry pada " + rak, "dataTransaksi", this.pane).setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new PopupGagal("Data gagal diperbarui").setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_konfirmasiMouseClicked

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
            java.util.logging.Logger.getLogger(PopupKonfirmasiBelumBayar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiBelumBayar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiBelumBayar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupKonfirmasiBelumBayar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupKonfirmasiBelumBayar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel btn_konfirmasi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kasir;
    private javax.swing.JLabel lbl_kasir;
    private javax.swing.JLabel lbl_kembalian;
    private javax.swing.JLabel lbl_namaPelanggan;
    private javax.swing.JLabel lbl_sisa;
    private javax.swing.JLabel lbl_sudahDibayar;
    private javax.swing.JLabel lbl_tanggalTransaksi;
    private javax.swing.JLabel lbl_total;
    private utility.custom_panel.PanelRound panelRound1;
    private javax.swing.JTable tbl_detailTransaksi;
    private javax.swing.JTextField txt_bayar;
    // End of variables declaration//GEN-END:variables
}
