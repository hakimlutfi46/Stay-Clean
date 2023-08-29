package SubMenu;

import SubMenu.Popup.Laporan.popup_detailLaporanPemasukan;
import koneksi.Connect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.swing.JDesktopPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utility.custom_table.table.TableCustom;

public class LaporanFrame extends javax.swing.JInternalFrame {

    public LaporanFrame() {
        initComponents();
        datatable();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tabel_utama.getTableHeader().setBackground(new Color(0, 168, 209));

        //menghilangkan border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        initializeBulanComboBox();
        initializeTahunComboBox();
    }

    private void prefredWidthTable() {
        //set width column table
        TableColumnModel kolomModel = tabel_utama.getColumnModel();

        kolomModel.getColumn(0).setMaxWidth(50);
        kolomModel.getColumn(0).setMinWidth(50);
        kolomModel.getColumn(0).setPreferredWidth(50);

        kolomModel.getColumn(1).setMaxWidth(162);
        kolomModel.getColumn(1).setMinWidth(162);
        kolomModel.getColumn(1).setPreferredWidth(162);

        kolomModel.getColumn(2).setMaxWidth(94);
        kolomModel.getColumn(2).setMinWidth(94);
        kolomModel.getColumn(2).setPreferredWidth(94);

        kolomModel.getColumn(3).setMaxWidth(154);
        kolomModel.getColumn(3).setMinWidth(154);
        kolomModel.getColumn(3).setPreferredWidth(154);

        kolomModel.getColumn(4).setMaxWidth(175);
        kolomModel.getColumn(4).setMinWidth(175);
        kolomModel.getColumn(4).setPreferredWidth(175);

        kolomModel.getColumn(5).setMaxWidth(163);
        kolomModel.getColumn(5).setMinWidth(163);
        kolomModel.getColumn(5).setPreferredWidth(163);

        kolomModel.getColumn(6).setMaxWidth(99);
        kolomModel.getColumn(6).setMinWidth(99);
        kolomModel.getColumn(6).setPreferredWidth(99);

        kolomModel.getColumn(7).setMaxWidth(99);
        kolomModel.getColumn(7).setMinWidth(99);
        kolomModel.getColumn(7).setPreferredWidth(99);

    }

    public void datatable() {

        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tabel_utama.setModel(tbl);

        tbl.addColumn("No");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("Paket");
        tbl.addColumn("Jumlah Satuan");
        tbl.addColumn("Tanggal Transaksi");
        tbl.addColumn("Tanggal Kembali");
        tbl.addColumn("Total");
        tbl.addColumn("Bayar");

        prefredWidthTable();

        try {
            Calendar calendar = Calendar.getInstance();
            int tahun = calendar.get(Calendar.YEAR);
            int bulan = calendar.get(Calendar.MONTH) + 1;

            String sql = "select nama_pelanggan, tipe_paket, jumlah_satuan, tgl_transaksi, estimasi_pengambilan, "
                    + "total, tunai "
                    + "from transaksi "
                    + "join pelanggan on transaksi.id_pelanggan = pelanggan.id_pelanggan "
                    + "join detail_transaksi on detail_transaksi.id_transaksi = transaksi.id_transaksi "
                    + "join detail_paket on detail_transaksi.id_detailPaket = detail_paket.id_detailPaket "
                    + "join paket on paket.id_paket = detail_paket.id_paket "
                    + "WHERE YEAR(transaksi.tgl_transaksi) = ? AND MONTH(transaksi.tgl_transaksi) = ? "
                    + "ORDER BY transaksi.id_transaksi DESC;";

            Connection conn = (Connection) Connect.GetConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tahun);
            pstmt.setInt(2, bulan);
            ResultSet res = pstmt.executeQuery();

            int no = 1;

            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

            while (res.next()) {

                String tglPesan = outputDateFormat.format(inputDateFormat.parse(res.getString("tgl_transaksi")));
                String tglKembali = outputDateFormat.format(inputDateFormat.parse(res.getString("estimasi_pengambilan")));

//              format rupiah
                int angka = Integer.parseInt(res.getString("transaksi.total"));
                String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
                StringTokenizer token = new StringTokenizer(ganti, ".");
                ganti = token.nextToken();
                ganti = ganti.replace(',', '.');

                int angka1 = Integer.parseInt(res.getString("transaksi.tunai"));
                String ganti1 = NumberFormat.getNumberInstance(Locale.US).format(angka1);
                StringTokenizer token1 = new StringTokenizer(ganti1, ".");
                ganti1 = token1.nextToken();
                ganti1 = ganti1.replace(',', '.');

                String satuan = res.getString("paket.tipe_paket").equals("Satuan") ? " Pcs" : " Kg";

                tbl.addRow(new Object[]{
                    no++,
                    res.getString("pelanggan.nama_pelanggan"),
                    res.getString("paket.tipe_paket"),
                    res.getString("jumlah_satuan") + satuan,
                    tglPesan,
                    tglKembali,
                    ganti,
                    ganti1
                });

            }
            tabel_utama.setModel(tbl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void dataFilter() {

        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tabel_utama.setModel(tbl);

        tbl.addColumn("No");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("Paket");
        tbl.addColumn("Jumlah Satuan");
        tbl.addColumn("Tanggal Transaksi");
        tbl.addColumn("Tanggal Kembali");
        tbl.addColumn("Total");
        tbl.addColumn("Bayar");

        prefredWidthTable();

        try {
            String tahun = cmb_tahun.getSelectedItem().toString();
            int bulanIndex = cmb_bulan.getSelectedIndex();
            int bulan = bulanIndex + 1;

            String sql = "select nama_pelanggan, tipe_paket, jumlah_satuan, tgl_transaksi, estimasi_pengambilan, "
                    + "total, tunai "
                    + "from transaksi "
                    + "join pelanggan on transaksi.id_pelanggan = pelanggan.id_pelanggan "
                    + "join detail_transaksi on detail_transaksi.id_transaksi = transaksi.id_transaksi "
                    + "join detail_paket on detail_transaksi.id_detailPaket = detail_paket.id_detailPaket "
                    + "join paket on paket.id_paket = detail_paket.id_paket "
                    + "WHERE YEAR(transaksi.tgl_transaksi) = ? AND MONTH(transaksi.tgl_transaksi) = ? "
                    + "ORDER BY transaksi.id_transaksi DESC;";

            Connection conn = (Connection) Connect.GetConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tahun);
            pstmt.setInt(2, bulan);
            ResultSet res = pstmt.executeQuery();

            int no = 1;

            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

            while (res.next()) {

                String tglPesan = outputDateFormat.format(inputDateFormat.parse(res.getString("tgl_transaksi")));
                String tglKembali = outputDateFormat.format(inputDateFormat.parse(res.getString("estimasi_pengambilan")));

//              format rupiah
                int angka = Integer.parseInt(res.getString("transaksi.total"));
                String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
                StringTokenizer token = new StringTokenizer(ganti, ".");
                ganti = token.nextToken();
                ganti = ganti.replace(',', '.');

                int angka1 = Integer.parseInt(res.getString("transaksi.tunai"));
                String ganti1 = NumberFormat.getNumberInstance(Locale.US).format(angka1);
                StringTokenizer token1 = new StringTokenizer(ganti1, ".");
                ganti1 = token1.nextToken();
                ganti1 = ganti1.replace(',', '.');

                String satuan = res.getString("paket.tipe_paket").equals("Satuan") ? " Pcs" : " Kg";

                tbl.addRow(new Object[]{
                    no++,
                    res.getString("pelanggan.nama_pelanggan"),
                    res.getString("paket.tipe_paket"),
                    res.getString("jumlah_satuan") + satuan,
                    tglPesan,
                    tglKembali,
                    ganti,
                    ganti1
                });

            }
            tabel_utama.setModel(tbl);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_utama = new javax.swing.JTable();
        lbl_pengeluaran = new javax.swing.JLabel();
        lbl_evaluasi = new javax.swing.JLabel();
        lbl_filter = new javax.swing.JLabel();
        cmb_tahun = new javax.swing.JComboBox<>();
        cmb_bulan = new javax.swing.JComboBox<>();
        background = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_utama.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        tabel_utama.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_utama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_utama.setRowHeight(40);
        tabel_utama.getTableHeader().setResizingAllowed(false);
        tabel_utama.getTableHeader().setReorderingAllowed(false);
        tabel_utama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_utamaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_utama);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 990, 530));

        lbl_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pengeluaranMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 16, 107, 30));

        lbl_evaluasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_evaluasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_evaluasiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_evaluasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 16, 80, 30));

        lbl_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(982, 12, 39, 39));

        cmb_tahun.setBackground(new Color(0,0,0,0)
        );
        cmb_tahun.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        getContentPane().add(cmb_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 12, 140, 39));

        cmb_bulan.setBackground(new Color(0,0,0,0)
        );
        cmb_bulan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        getContentPane().add(cmb_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 12, 140, 39));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Lapopran Pemasukan Newest.png"))); // NOI18N
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

    private void tabel_utamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_utamaMouseClicked

        try {
            int selectedRow = tabel_utama.getSelectedRow();
            if (selectedRow != -1) {

                // Mendapatkan nilai kolom nama_paket dari baris yang diklik
                String namaPaket = tabel_utama.getValueAt(selectedRow, 2).toString();

                // Mendapatkan nilai kolom tanggal_transaksi dari baris yang diklik
                String tanggalTransaksi = tabel_utama.getValueAt(selectedRow, 4).toString();

                // Reformat tanggal sesuai dengan format di database
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                tanggalTransaksi = outputDateFormat.format(inputDateFormat.parse(tanggalTransaksi));

                popup_detailLaporanPemasukan detailTransaksiFrame = new popup_detailLaporanPemasukan(namaPaket, tanggalTransaksi);
                detailTransaksiFrame.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_tabel_utamaMouseClicked

    private void lbl_evaluasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_evaluasiMouseClicked
        LaporanEvaluasi laporanEvaluasi = new LaporanEvaluasi();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(laporanEvaluasi);
        laporanEvaluasi.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_lbl_evaluasiMouseClicked

    private void lbl_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterMouseClicked
        dataFilter();
    }//GEN-LAST:event_lbl_filterMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JComboBox<String> cmb_bulan;
    private javax.swing.JComboBox<String> cmb_tahun;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_evaluasi;
    private javax.swing.JLabel lbl_filter;
    private javax.swing.JLabel lbl_pengeluaran;
    private javax.swing.JTable tabel_utama;
    // End of variables declaration//GEN-END:variables
}
