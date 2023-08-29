/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu.Popup.Transaksi;

import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import koneksi.Connect;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author rayrayaray
 */
public class PopupDetailTransaksi extends javax.swing.JFrame {

    String idTransaksi;
    JasperDesign JasDes;
    JasperPrint JasPri;
    JasperReport JasRep;
    Map Param = new HashMap();

    /**
     * Creates new form PopUpDetailTransaksi
     */
    public PopupDetailTransaksi() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_detailTransaksi.getTableHeader().setBackground(new Color(0, 168, 209));
        data();
    }

    public PopupDetailTransaksi(String id) {
        initComponents();
        this.idTransaksi = id;
        setBackground(new Color(0, 0, 0, 0));
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_detailTransaksi.getTableHeader().setBackground(new Color(0, 168, 209));
        data();
    }

    public void data() {
        DefaultTableModel dtm = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tbl_detailTransaksi.setModel(dtm);

        dtm.addColumn("No.");
        dtm.addColumn("Paket");
        dtm.addColumn("Jenis Paket");
        dtm.addColumn("Rak");
        dtm.addColumn("Subtotal");

        preferedWidthTable();

        try {
            //select data
            ResultSet hasilCariData = readDb("SELECT transaksi.id_transaksi, tipe_paket, nama_paket, nama_rak, sub_total, "
                    + "nama_pelanggan, nope_pelanggan, alamat_pelanggan, total, tunai, kembalian "
                    + "FROM detail_transaksi "
                    + "JOIN transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi "
                    + "JOIN detail_paket ON detail_paket.id_detailPaket = detail_transaksi.id_detailPaket "
                    + "JOIN paket ON paket.id_paket = detail_paket.id_paket "
                    + "JOIN rak ON rak.id_rak = detail_transaksi.id_rak "
                    + "JOIN pelanggan ON pelanggan.id_pelanggan = transaksi.id_pelanggan "
                    + "WHERE transaksi.id_transaksi = '" + this.idTransaksi + "'");

            int no = 1;
            while (hasilCariData.next()) {

                //set data pelanggan
                lbl_nama.setText(hasilCariData.getString("nama_pelanggan"));
                lbl_nope.setText(hasilCariData.getString("nope_pelanggan"));
                lbl_alamat.setText(hasilCariData.getString("alamat_pelanggan"));

                //set data transaksi
                lbl_idTransaksi.setText(this.idTransaksi);
                lbl_total.setText(hasilCariData.getString("total"));
                lbl_bayar.setText(hasilCariData.getString("tunai"));
                lbl_kembalian.setText(hasilCariData.getString("kembalian"));

                //set detail transaksi
                dtm.addRow(new Object[]{
                    no++,
                    hasilCariData.getString("tipe_paket"),
                    hasilCariData.getString("nama_paket"),
                    hasilCariData.getString("nama_rak"),
                    formatDec(Integer.parseInt(hasilCariData.getString("sub_total")))
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void preferedWidthTable() {
        TableColumnModel columnModel = tbl_detailTransaksi.getColumnModel();

//        columnModel.getColumn(0).setPreferredWidth(42);
//        columnModel.getColumn(1).setPreferredWidth(110);
//        columnModel.getColumn(2).setPreferredWidth(153);
//        columnModel.getColumn(3).setPreferredWidth(144);
//        columnModel.getColumn(4).setPreferredWidth(86);
//        columnModel.getColumn(5).setPreferredWidth(167);
//        columnModel.getColumn(6).setPreferredWidth(155);
//        columnModel.getColumn(7).setPreferredWidth(136);
    }

    private String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    private String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    private void insertDb(String sql) {
        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_detailTransaksi = new javax.swing.JTable();
        lbl_nama = new javax.swing.JLabel();
        lbl_nope = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        lbl_idTransaksi = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        lbl_bayar = new javax.swing.JLabel();
        lbl_kembalian = new javax.swing.JLabel();
        btn_exit = new utility.custom_panel.PanelRound();
        btn_nota = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_detailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_detailTransaksi.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tbl_detailTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 530, 95));

        lbl_nama.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_nama.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 206, 340, 17));

        lbl_nope.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_nope.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_nope, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 236, 340, 17));

        lbl_alamat.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_alamat.setForeground(new java.awt.Color(0, 0, 0));
        lbl_alamat.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 266, 340, 40));

        lbl_idTransaksi.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_idTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 380, 340, 17));

        lbl_total.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_total.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 410, 340, 20));

        lbl_bayar.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_bayar.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 445, 340, 17));

        lbl_kembalian.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_kembalian.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 475, 340, 20));

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
            .addGap(0, 36, Short.MAX_VALUE)
        );
        btn_exitLayout.setVerticalGroup(
            btn_exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        getContentPane().add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 95, 36, 36));

        btn_nota.setBackground(new Color(0,0,0,0));
        btn_nota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_notaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_notaLayout = new javax.swing.GroupLayout(btn_nota);
        btn_nota.setLayout(btn_notaLayout);
        btn_notaLayout.setHorizontalGroup(
            btn_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        btn_notaLayout.setVerticalGroup(
            btn_notaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        getContentPane().add(btn_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 659, 158, 34));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Pop Up Detail Transaksi.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_notaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_notaMouseClicked
        try {
            Connection conn = Connect.GetConnection();

            Linear barcode = new Linear();
            barcode.setType(Linear.CODE39);
            barcode.setData(this.idTransaksi);
            String fName = this.idTransaksi;
            barcode.renderBarcode("src/assets/img/barcode/" + fName + ".png");

            String report = ("E:\\Project Kelompok 3 Semester 2\\Stay Clean_FIX\\StayClean\\src\\SubMenu\\Nota\\NotaTransaksi.jrxml");
            HashMap hash = new HashMap();
            hash.put("id_transaksi", this.idTransaksi);
            hash.put("path", "src/assets/img/barcode/" + fName + ".png");
            JasperReport jasper = JasperCompileManager.compileReport(report);
            JasperPrint jasperP = JasperFillManager.fillReport(jasper, hash, conn);
//        JasperViewer.viewReport(jasperP, false); // untuk liat preview
            JasperPrintManager.printReport(jasperP, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_notaMouseClicked

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
            java.util.logging.Logger.getLogger(PopupDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupDetailTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private utility.custom_panel.PanelRound btn_exit;
    private javax.swing.JPanel btn_nota;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_bayar;
    private javax.swing.JLabel lbl_idTransaksi;
    private javax.swing.JLabel lbl_kembalian;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_nope;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTable tbl_detailTransaksi;
    // End of variables declaration//GEN-END:variables
}
