/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu;

import SubMenu.Popup.Transaksi.PopupKonfirmasiPengambilan;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import koneksi.Connect;
import java.sql.*;
import javax.swing.JDesktopPane;
import javax.swing.table.TableColumnModel;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author perlengkapan
 */
public class DashboardFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel model;

    /**
     * Creates new form DashboardFrame
     */
    public DashboardFrame() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_pesanan.getTableHeader().setBackground(new Color(0, 168, 209));
        dataTable();
        totalProfit(lbl_profit);
        totalPesanan(lbl_totalPesanan);
        statusLaundryBelum(lbl_belumDiambil);
        statusLaundrySudah(lbl_sudahDiambil);

        //ilangin border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

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

    public void dataTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tbl_pesanan.setModel(dtm);

        dtm.addColumn("No.");
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Nama Karyawan");
        dtm.addColumn("Total");
        dtm.addColumn("Status Pembayaran");
        dtm.addColumn("Tanggal Transaksi");
        dtm.addColumn("Status Pesanan");

        preferedWidthTable();

        try {

            ResultSet hasilCariData = readDb("SELECT transaksi.id_transaksi, nama_pelanggan, nama, total, status_pembayaran, "
                    + "status_pesanan, tgl_transaksi FROM transaksi "
                    + "JOIN akun ON transaksi.id_akun = akun.id_akun "
                    + "JOIN pelanggan ON pelanggan.id_pelanggan = transaksi.id_pelanggan where status_pesanan = 'Belum Diambil' order by tgl_transaksi desc");

            int no = 1;
            while (hasilCariData.next()) {
                dtm.addRow(new Object[]{
                    no++,
                    hasilCariData.getString("id_transaksi"),
                    hasilCariData.getString("nama_pelanggan"),
                    hasilCariData.getString("nama"),
                    formatDec(Integer.parseInt(hasilCariData.getString("total"))),
                    hasilCariData.getString("status_pembayaran"),
                    hasilCariData.getString("tgl_transaksi"),
                    hasilCariData.getString("status_pesanan")
                }
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void preferedWidthTable() {
        TableColumnModel columnModel = tbl_pesanan.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(42);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(153);
        columnModel.getColumn(3).setPreferredWidth(144);
        columnModel.getColumn(4).setPreferredWidth(86);
        columnModel.getColumn(5).setPreferredWidth(167);
        columnModel.getColumn(6).setPreferredWidth(155);
        columnModel.getColumn(7).setPreferredWidth(136);
    }

    private String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    private String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    private void totalProfit(JLabel jlabel) {
        try {
            ResultSet rs = readDb("SELECT SUM(total) as total_hari_ini FROM transaksi WHERE DATE(tgl_transaksi) = CURDATE()");
            if (rs.next()) {
                if (rs.getString("total_hari_ini") == null) {
                    jlabel.setText("0");
                } else {
                    jlabel.setText(formatDec(Integer.parseInt(rs.getString("total_hari_ini"))));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void totalPesanan(JLabel jlabel) {
        try {
            ResultSet rs = readDb("SELECT COUNT(id_transaksi) AS pesanan_hari_ini FROM transaksi WHERE DATE(tgl_transaksi) = CURDATE()");
            if (rs.next()) {
                jlabel.setText(rs.getString("pesanan_hari_ini"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void statusLaundrySudah(JLabel jlabel) {
        try {
            ResultSet rs = readDb("SELECT COUNT(id_transaksi) AS pesanan_sudah FROM transaksi WHERE DATE(tgl_transaksi) = CURDATE() AND status_pesanan = 'Sudah Diambil'");
            if (rs.next()) {
                jlabel.setText(rs.getString("pesanan_sudah"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void statusLaundryBelum(JLabel jlabel) {
        try {
            ResultSet rs = readDb("SELECT COUNT(id_transaksi) AS pesanan_belum FROM transaksi WHERE DATE(tgl_transaksi) = CURDATE() AND status_pesanan = 'Belum Diambil'");
            if (rs.next()) {
                jlabel.setText(rs.getString("pesanan_belum"));
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pesanan = new javax.swing.JTable();
        konfirmasiPengambilan = new javax.swing.JLabel();
        lbl_totalPesanan = new javax.swing.JLabel();
        lbl_profit = new javax.swing.JLabel();
        Rp = new javax.swing.JLabel();
        lbl_sudahDiambil = new javax.swing.JLabel();
        lbl_belumDiambil = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_pesanan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        tbl_pesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_pesanan.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tbl_pesanan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 980, 220));

        konfirmasiPengambilan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        konfirmasiPengambilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                konfirmasiPengambilanMouseClicked(evt);
            }
        });
        getContentPane().add(konfirmasiPengambilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(777, 577, 260, 40));

        lbl_totalPesanan.setFont(new java.awt.Font("Poppins", 1, 60)); // NOI18N
        lbl_totalPesanan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_totalPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_totalPesanan.setText("50");
        getContentPane().add(lbl_totalPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 130, 80));

        lbl_profit.setBackground(new java.awt.Color(255, 255, 255));
        lbl_profit.setFont(new java.awt.Font("Poppins", 1, 30)); // NOI18N
        lbl_profit.setForeground(new java.awt.Color(255, 255, 255));
        lbl_profit.setText("100.000");
        lbl_profit.setMaximumSize(new java.awt.Dimension(100, 16));
        lbl_profit.setPreferredSize(new java.awt.Dimension(100, 16));
        getContentPane().add(lbl_profit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 140, 30));

        Rp.setBackground(new java.awt.Color(255, 255, 255));
        Rp.setFont(new java.awt.Font("Poppins", 1, 30)); // NOI18N
        Rp.setForeground(new java.awt.Color(255, 255, 255));
        Rp.setText("Rp.");
        Rp.setMaximumSize(new java.awt.Dimension(100, 16));
        Rp.setPreferredSize(new java.awt.Dimension(100, 16));
        getContentPane().add(Rp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 30));

        lbl_sudahDiambil.setFont(new java.awt.Font("Poppins", 1, 60)); // NOI18N
        lbl_sudahDiambil.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sudahDiambil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_sudahDiambil.setText("3");
        getContentPane().add(lbl_sudahDiambil, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 70, 100, -1));

        lbl_belumDiambil.setFont(new java.awt.Font("Poppins", 1, 60)); // NOI18N
        lbl_belumDiambil.setForeground(new java.awt.Color(255, 255, 255));
        lbl_belumDiambil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_belumDiambil.setText("5");
        getContentPane().add(lbl_belumDiambil, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 100, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Dashboard Frame.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void konfirmasiPengambilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_konfirmasiPengambilanMouseClicked
        PopupKonfirmasiPengambilan pkp = new PopupKonfirmasiPengambilan();
        JDesktopPane desktopPane = getDesktopPane();
        pkp.setPane(desktopPane);
        pkp.setVisible(true);
    }//GEN-LAST:event_konfirmasiPengambilanMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Rp;
    private javax.swing.JLabel background;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel konfirmasiPengambilan;
    private javax.swing.JLabel lbl_belumDiambil;
    private javax.swing.JLabel lbl_profit;
    private javax.swing.JLabel lbl_sudahDiambil;
    private javax.swing.JLabel lbl_totalPesanan;
    private javax.swing.JTable tbl_pesanan;
    // End of variables declaration//GEN-END:variables

}
