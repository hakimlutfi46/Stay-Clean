package SubMenu;

import com.raven.chart.ModelChart;
import javax.swing.JDesktopPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Month;
import java.util.Calendar;
import koneksi.Connect;

public final class LaporanEvaluasi extends javax.swing.JInternalFrame {

    public boolean klikEdit;

    public LaporanEvaluasi() {
        initComponents();

        //menghilangkan border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        initializeTahunComboBox();
        initializeBulanComboBox();

        setChartBar();

        filterEval();

    }

    public void setChartBar() {
        chart1.addLegend("Pemasukan", new Color(50, 186, 124));
        chart1.addLegend("Pengeluaran", new Color(226, 76, 75));
        chart1.addLegend("Pendapatan Bersih", new Color(0, 168, 209));
        try {
            ResultSet res = readDb("SELECT pemasukan.bulan, COALESCE(total_pemasukan, 0) AS total_pemasukan, COALESCE(total_pengeluaran, 0) AS total_pengeluaran "
                    + "FROM ( "
                    + "    SELECT MONTH(tgl_transaksi) AS bulan, SUM(total) AS total_pemasukan "
                    + "    FROM transaksi "
                    + "    WHERE MONTH(tgl_transaksi) >= MONTH(CURRENT_DATE()) "
                    + "    GROUP BY MONTH(tgl_transaksi) "
                    + "    LIMIT 6 "
                    + ") AS pemasukan "
                    + "LEFT JOIN ( "
                    + "    SELECT MONTH(tanggal_pengeluaran) AS bulan, SUM(tagihan) AS total_pengeluaran "
                    + "    FROM pengeluaran "
                    + "    WHERE MONTH(tanggal_pengeluaran) >= MONTH(CURRENT_DATE()) "
                    + "    GROUP BY MONTH(tanggal_pengeluaran) "
                    + "    LIMIT 6 "
                    + ") AS pengeluaran "
                    + "ON pemasukan.bulan = pengeluaran.bulan "
                    + "ORDER BY pemasukan.bulan;");

            while (res.next()) {
                String bulan = String.valueOf(Month.of(Integer.parseInt(res.getString("bulan"))));
                int laba_bersih = Integer.parseInt(res.getString("total_pemasukan")) - Integer.parseInt(res.getString("total_pengeluaran"));
                chart1.addData(new ModelChart(bulan, new double[]{Integer.parseInt(res.getString("total_pemasukan")), Integer.parseInt(res.getString("total_pengeluaran")), laba_bersih}));
            }

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

    private void filterEval() {
        try {
            ResultSet resPaketLaris = readDb("SELECT month(tgl_transaksi) as bulan, year(tgl_transaksi) as tahun, "
                    + "paket.tipe_paket, COUNT(detail_transaksi.id_transaksi) AS jumlah_penjualan FROM detail_transaksi "
                    + "JOIN detail_paket ON detail_transaksi.id_detailPaket = detail_paket.id_detailPaket "
                    + "join transaksi on transaksi.id_transaksi = detail_transaksi.id_transaksi "
                    + "join paket on paket.id_paket = detail_paket.id_paket "
                    + "where month(tgl_transaksi) = '" + (cmb_bulan.getSelectedIndex() + 1) + "' and year(tgl_transaksi) = '" + cmb_tahun.getSelectedItem() + "' GROUP BY paket.tipe_paket "
                    + "ORDER BY `jumlah_penjualan` DESC limit 1;");

            ResultSet resPemasukan = readDb("SELECT SUM(total) AS total_pemasukan FROM transaksi WHERE MONTH(tgl_transaksi) = '" + (cmb_bulan.getSelectedIndex() + 1) + "' AND YEAR(tgl_transaksi) = '" + cmb_tahun.getSelectedItem() + "'");

            ResultSet resPengeluaran = readDb("SELECT SUM(tagihan) AS total_pengeluaran FROM pengeluaran WHERE MONTH(tanggal_pengeluaran) = '" + (cmb_bulan.getSelectedIndex() + 1) + "' AND YEAR(tanggal_pengeluaran) = '" + cmb_tahun.getSelectedItem() + "'");

            if (resPemasukan.next() && resPaketLaris.next() && resPengeluaran.next()) {
                int laba_bersih = Integer.parseInt(resPemasukan.getString("total_pemasukan")) - Integer.parseInt(resPengeluaran.getString("total_pengeluaran"));

                if (resPaketLaris.getString("tipe_paket") == null) {
                    lbl_paket_terlaris.setText("<html><center>Data Kosong</center></html>");
                } else {
                    lbl_paket_terlaris.setText(resPaketLaris.getString("tipe_paket"));
                }

                if (resPemasukan.getString("total_pemasukan") == null) {
                    lbl_total_pendapatan.setText("<html><center>Data Kosong</center></html>");
                } else {
                    lbl_total_pendapatan.setText("Rp " + formatDec(Integer.parseInt(resPemasukan.getString("total_pemasukan"))));
                }

                if (resPengeluaran.getString("total_pengeluaran") == null) {
                    lbl_total_pengeluaran.setText("<html><center>Data Kosong</center></html>");
                } else {
                    lbl_total_pengeluaran.setText("Rp " + formatDec(Integer.parseInt(resPengeluaran.getString("total_pengeluaran"))));
                }

                if (laba_bersih < 1) {
                    lbl_pendapatan_bersih.setText("<html><center>Data Kosong</center></html>");
                } else {
                    lbl_pendapatan_bersih.setText(String.valueOf("Rp. " + formatDec(laba_bersih)));
                }

            } else {
                lbl_paket_terlaris.setText("<html><center>Data Kosong</center></html>");
                lbl_total_pendapatan.setText("<html><center>Data Kosong</center></html>");
                lbl_total_pengeluaran.setText("<html><center>Data Kosong</center></html>");
                lbl_pendapatan_bersih.setText("<html><center>Data Kosong</center></html>");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initializeTahunComboBox() {
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int tahunAwal = tahunSekarang - 1;
        int tahunAkhir = tahunSekarang + 1;

        for (int tahun = tahunAwal; tahun <= tahunAkhir; tahun++) {
            cmb_tahun.addItem(String.valueOf(tahun));
        }

        // Set tahunComboBox ke tahun saat ini jika ada dalam rentang tahunAwal dan tahunAkhir
        if (tahunSekarang >= tahunAwal && tahunSekarang <= tahunAkhir) {
            cmb_tahun.setSelectedItem(String.valueOf(tahunSekarang));
        } else {
            cmb_tahun.setSelectedItem(null);
        }
    }

    private void initializeBulanComboBox() {
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        cmb_bulan.setSelectedIndex(bulanSekarang);
    }

    //format decimal
    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    //untuk menghilangkan titik
    public static String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_pengeluaran = new javax.swing.JLabel();
        lbl_pemasukan = new javax.swing.JLabel();
        lbl_paket_terlaris = new javax.swing.JLabel();
        lbl_total_pendapatan = new javax.swing.JLabel();
        lbl_total_pengeluaran = new javax.swing.JLabel();
        lbl_pendapatan_bersih = new javax.swing.JLabel();
        cmb_tahun = new javax.swing.JComboBox<>();
        cmb_bulan = new javax.swing.JComboBox<>();
        lbl_filter = new javax.swing.JLabel();
        chart1 = new com.raven.chart.Chart();
        background = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pengeluaranMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 16, 100, 29));

        lbl_pemasukan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_pemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pemasukanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 16, 100, 29));

        lbl_paket_terlaris.setFont(new java.awt.Font("Poppins SemiBold", 3, 24)); // NOI18N
        lbl_paket_terlaris.setForeground(new java.awt.Color(255, 255, 255));
        lbl_paket_terlaris.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_paket_terlaris.setText(".");
        lbl_paket_terlaris.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_paket_terlaris.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_paket_terlaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 200, 210, 70));

        lbl_total_pendapatan.setFont(new java.awt.Font("Poppins SemiBold", 3, 22)); // NOI18N
        lbl_total_pendapatan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_total_pendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_total_pendapatan.setText(".");
        lbl_total_pendapatan.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_total_pendapatan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_total_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 200, 210, 70));

        lbl_total_pengeluaran.setFont(new java.awt.Font("Poppins SemiBold", 3, 22)); // NOI18N
        lbl_total_pengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        lbl_total_pengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_total_pengeluaran.setText(".");
        lbl_total_pengeluaran.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_total_pengeluaran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_total_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 200, 210, 70));

        lbl_pendapatan_bersih.setFont(new java.awt.Font("Poppins SemiBold", 3, 22)); // NOI18N
        lbl_pendapatan_bersih.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pendapatan_bersih.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pendapatan_bersih.setText(".");
        lbl_pendapatan_bersih.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_pendapatan_bersih.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_pendapatan_bersih, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 200, 210, 70));

        cmb_tahun.setBackground(new Color(0,0,0,0));
        cmb_tahun.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_tahun.setBorder(null);
        getContentPane().add(cmb_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 82, 140, 29));

        cmb_bulan.setBackground(new Color(0,0,0,0));
        cmb_bulan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cmb_bulan.setBorder(null);
        cmb_bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_bulanActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 82, 140, 29));

        lbl_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 77, 92, 39));
        getContentPane().add(chart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 960, 290));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Laporan Evaluasi.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pengeluaranMouseClicked
        LaporanPengeluaranNew laporanNew = new LaporanPengeluaranNew();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(laporanNew);
        laporanNew.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_lbl_pengeluaranMouseClicked

    private void lbl_pemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pemasukanMouseClicked
        LaporanFrame laporanFrame = new LaporanFrame();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(laporanFrame);
        laporanFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_lbl_pemasukanMouseClicked

    private void lbl_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterMouseClicked
        filterEval();
    }//GEN-LAST:event_lbl_filterMouseClicked

    private void cmb_bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_bulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_bulanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private com.raven.chart.Chart chart1;
    private javax.swing.JComboBox<String> cmb_bulan;
    private javax.swing.JComboBox<String> cmb_tahun;
    private javax.swing.JLabel lbl_filter;
    private javax.swing.JLabel lbl_paket_terlaris;
    private javax.swing.JLabel lbl_pemasukan;
    private javax.swing.JLabel lbl_pendapatan_bersih;
    private javax.swing.JLabel lbl_pengeluaran;
    private javax.swing.JLabel lbl_total_pendapatan;
    private javax.swing.JLabel lbl_total_pengeluaran;
    // End of variables declaration//GEN-END:variables
}
