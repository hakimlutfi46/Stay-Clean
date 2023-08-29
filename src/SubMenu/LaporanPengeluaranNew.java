package SubMenu;

import SubMenu.Popup.Laporan.YakinHapus;
import SubMenu.Popup.Laporan.popup_tambahEditPengeluaran;
import utility.laporan.DateUtil;
import koneksi.Connect;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import utility.custom_table.table.TableCustom;

public class LaporanPengeluaranNew extends javax.swing.JInternalFrame {

    public boolean klikTable;
    private popup_tambahEditPengeluaran popupTambahEdit;
    String tgl_input;

    public LaporanPengeluaranNew() {
        initComponents();
        datatable();
        autosumTotal();

        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        tabel_pengeluaran.getTableHeader().setBackground(new Color(0, 168, 209));

        //menghilangkan border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        initializeBulanComboBox();
        initializeTahunComboBox();

        lbl_tambah = new JLabel("Tambah");
        popupTambahEdit = new popup_tambahEditPengeluaran();

        lbl_tambah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupTambahEdit.addTittle();
                popupTambahEdit.setVisible(true);

            }
        });

        // Menggunakan MouseListener untuk lbl_edit di Laporan
        lbl_edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabel_pengeluaran.getSelectedRow();

                // Mendapatkan nilai dari tabel_pengeluaran
                String namaPengeluaran = (String) tabel_pengeluaran.getValueAt(row, 1);
                String kategori = (String) tabel_pengeluaran.getValueAt(row, 2);
                String tagihan = (String) tabel_pengeluaran.getValueAt(row, 3);
                String tanggalPengeluaran = (String) tabel_pengeluaran.getValueAt(row, 4);
                String deskripsi = (String) tabel_pengeluaran.getValueAt(row, 5);

                // Menetapkan nilai ke komponen-komponen di popupTambahEdit
                popupTambahEdit.setNamaPengeluaranLabel(namaPengeluaran);
                popupTambahEdit.setKategoriComboBox(kategori);
                popupTambahEdit.setTagihanLabel(tagihan);
                popupTambahEdit.setTanggalPengeluaranChooser(DateUtil.parseDate(tanggalPengeluaran)); // Menggunakan metode parseDate untuk mengubah format tanggal
                popupTambahEdit.setDeskripsiTextArea(deskripsi);
                popupTambahEdit.editTitle();

                int i = tabel_pengeluaran.getSelectedRow();
                TableModel tbl = tabel_pengeluaran.getModel();

                String field6 = tbl.getValueAt(i, 6).toString();

                popupTambahEdit.setTanggalInput(field6);

                JDesktopPane desktopPane = getDesktopPane();
                popupTambahEdit.setBackPane(desktopPane);

                // Menampilkan popupTambahEdit
                popupTambahEdit.setVisible(true);
            }
        });

    }

    public void autosumTotal() {
        int total = 0;
        NumberFormat format = NumberFormat.getInstance();

        for (int i = 0; i < tabel_pengeluaran.getRowCount(); i++) {
            try {

                String amountString = tabel_pengeluaran.getValueAt(i, 3).toString();
                int amount = format.parse(amountString).intValue();
                total += amount;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        lbl_total_pengeluaran.setText("Total pengeluaran = Rp. " + formatDec(total));
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

    private void prefredWidthTable() {
        //set width column table
        TableColumnModel kolomModel = tabel_pengeluaran.getColumnModel();

        kolomModel.getColumn(0).setMaxWidth(39);
        kolomModel.getColumn(0).setMinWidth(39);
        kolomModel.getColumn(0).setPreferredWidth(39);

        kolomModel.getColumn(1).setPreferredWidth(161);

        kolomModel.getColumn(2).setPreferredWidth(120);

        kolomModel.getColumn(3).setPreferredWidth(104);

        kolomModel.getColumn(4).setPreferredWidth(172);

        kolomModel.getColumn(5).setPreferredWidth(181);

        kolomModel.getColumn(6).setPreferredWidth(171);

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

        tabel_pengeluaran.setModel(tbl);

        tbl.addColumn("No");
        tbl.addColumn("Nama Pengeluaran");
        tbl.addColumn("Kategori");
        tbl.addColumn("Tagihan");
        tbl.addColumn("Tanggal Pengeluaran");
        tbl.addColumn("Deskripsi");
        tbl.addColumn("Waktu Input");

        prefredWidthTable();

        try {
            Calendar calendar = Calendar.getInstance();
            int tahun = calendar.get(Calendar.YEAR);
            int bulan = calendar.get(Calendar.MONTH) + 1;

            String sql = "SELECT nama_pengeluaran, kategori, tagihan, tanggal_pengeluaran, deskripsi, tanggal_input "
                    + "FROM pengeluaran WHERE YEAR(tanggal_pengeluaran) = ? AND MONTH(tanggal_pengeluaran) = ? "
                    + "ORDER BY id_pengeluaran DESC";

            Connection conn = (Connection) Connect.GetConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tahun);
            pstmt.setInt(2, bulan);
            ResultSet res = pstmt.executeQuery();

            int no = 1;

            while (res.next()) {
                // Format rupiah
                int angka = Integer.parseInt(res.getString("pengeluaran.tagihan"));
                String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
                StringTokenizer token = new StringTokenizer(ganti, ".");
                ganti = token.nextToken();
                ganti = ganti.replace(',', '.');

                // Format tanggal
                String tanggalPengeluaran = res.getString("tanggal_pengeluaran");
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

                try {
                    Date parsedDate = inputDateFormat.parse(tanggalPengeluaran);
                    tanggalPengeluaran = outputDateFormat.format(parsedDate);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                tbl.addRow(new Object[]{
                    no++,
                    res.getString(1),
                    res.getString(2),
                    res.getString(3).format(ganti),
                    tanggalPengeluaran,
                    res.getString(5),
                    res.getString(6)
                });
            }
            tabel_pengeluaran.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

        tabel_pengeluaran.setModel(tbl);

        tbl.addColumn("No");
        tbl.addColumn("Nama Pengeluaran");
        tbl.addColumn("Kategori");
        tbl.addColumn("Tagihan");
        tbl.addColumn("Tanggal Pengeluaran");
        tbl.addColumn("Deskripsi");
        tbl.addColumn("Waktu Input");

        prefredWidthTable();

        try {
            String tahun = cmb_tahun.getSelectedItem().toString();
            int bulanIndex = cmb_bulan.getSelectedIndex();
            int bulan = bulanIndex + 1;

            String sql = "SELECT nama_pengeluaran, kategori, tagihan, tanggal_pengeluaran, deskripsi, tanggal_input "
                    + "FROM pengeluaran WHERE YEAR(tanggal_pengeluaran) = ? AND MONTH(tanggal_pengeluaran) = ? "
                    + "ORDER BY id_pengeluaran DESC";

            Connection conn = (Connection) Connect.GetConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tahun);
            pstmt.setInt(2, bulan);
            ResultSet res = pstmt.executeQuery();

            int no = 1;

            while (res.next()) {
                // Format rupiah
                int angka = Integer.parseInt(res.getString("pengeluaran.tagihan"));
                String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
                StringTokenizer token = new StringTokenizer(ganti, ".");
                ganti = token.nextToken();
                ganti = ganti.replace(',', '.');

                // Format tanggal "dd MMMM yyyy"
                String tanggalPengeluaran = res.getString("tanggal_pengeluaran");
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

                try {
                    Date parsedDate = inputDateFormat.parse(tanggalPengeluaran);
                    tanggalPengeluaran = outputDateFormat.format(parsedDate);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                tbl.addRow(new Object[]{
                    no++,
                    res.getString(1),
                    res.getString(2),
                    res.getString(3).format(ganti),
                    tanggalPengeluaran,
                    res.getString(5),
                    res.getString(6)
                });
            }
            tabel_pengeluaran.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_pengeluaran = new javax.swing.JTable();
        lbl_pemasukan = new javax.swing.JLabel();
        lbl_evaluasi = new javax.swing.JLabel();
        lbl_tambah = new javax.swing.JLabel();
        lbl_edit = new javax.swing.JLabel();
        lbl_hapus = new javax.swing.JLabel();
        cmb_tahun = new javax.swing.JComboBox<>();
        cmb_bulan = new javax.swing.JComboBox<>();
        lbl_total_pengeluaran = new javax.swing.JLabel();
        lbl_filter = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_pengeluaran.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        tabel_pengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_pengeluaran.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tabel_pengeluaran.getTableHeader().setResizingAllowed(false);
        tabel_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pengeluaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_pengeluaran);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 1030, 400));

        lbl_pemasukan.setBackground(new Color(0,0,0,0)
        );
        lbl_pemasukan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_pemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pemasukanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 16, 100, 29));

        lbl_evaluasi.setBackground(new Color(0,0,0,0)
        );
        lbl_evaluasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_evaluasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_evaluasiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_evaluasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 16, 82, 29));

        lbl_tambah.setBackground(new Color(0,0,0,0)
        );
        lbl_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 77, 147, 39));

        lbl_edit.setBackground(new Color(0,0,0,0)
        );
        lbl_edit.setForeground(new Color(0, 168, 209)
        );
        lbl_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_editMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 77, 147, 39));

        lbl_hapus.setBackground(new Color(0,0,0,0)
        );
        lbl_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 77, 147, 39));

        cmb_tahun.setBackground(new Color(0,0,0,0)
        );
        cmb_tahun.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_tahun.setBorder(null);
        cmb_tahun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(cmb_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 77, 140, 39));

        cmb_bulan.setBackground(new Color(0,0,0,0)
        );
        cmb_bulan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cmb_bulan.setBorder(null);
        cmb_bulan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(cmb_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(861, 77, 140, 39));

        lbl_total_pengeluaran.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        lbl_total_pengeluaran.setForeground(new Color(0, 168, 209)
        );
        lbl_total_pengeluaran.setText("Total pengeluaran = Rp. ");
        getContentPane().add(lbl_total_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, -1));

        lbl_filter.setBackground(new Color(0,0,0,0)
        );
        lbl_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1023, 77, 39, 39));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Laporan Pengeluaran.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pengeluaranMouseClicked

        klikTable = true;
        int i = tabel_pengeluaran.getSelectedRow();
        TableModel tbl = tabel_pengeluaran.getModel();
        String field6 = tbl.getValueAt(i, 6).toString();

        tgl_input = field6;

    }//GEN-LAST:event_tabel_pengeluaranMouseClicked

    private void lbl_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tambahMouseClicked
        popupTambahEdit.addTittle();
        popupTambahEdit.setVisible(true);

        JDesktopPane desktopPane = getDesktopPane();
        popupTambahEdit.setBackPane(desktopPane);
    }//GEN-LAST:event_lbl_tambahMouseClicked

    private void lbl_pemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pemasukanMouseClicked
        LaporanFrame laporanFrame = new LaporanFrame();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(laporanFrame);
        laporanFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_lbl_pemasukanMouseClicked

    private void lbl_evaluasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_evaluasiMouseClicked
        LaporanEvaluasi laporanEvaluasi = new LaporanEvaluasi();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(laporanEvaluasi);
        laporanEvaluasi.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_lbl_evaluasiMouseClicked

    private void lbl_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_editMouseClicked

        if (klikTable == true) {

            try {
                JDesktopPane desktopPane = getDesktopPane();
                popupTambahEdit.setBackPane(desktopPane);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Pilih opsi yang akan diedit");
        }

    }//GEN-LAST:event_lbl_editMouseClicked

    private void lbl_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hapusMouseClicked
        if (klikTable == true) {

            try {
                String tampilan = "yyyy-MM-dd";
                SimpleDateFormat fm = new SimpleDateFormat(tampilan);

                int i = tabel_pengeluaran.getSelectedRow();
                TableModel tbl = tabel_pengeluaran.getModel();

                String field4 = tbl.getValueAt(i, 4).toString();

                JDesktopPane desktopPane = getDesktopPane();
                new YakinHapus("Apakah yakin ingin menghapus data ini?", field4, this.tgl_input, desktopPane).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diedit");
        }
    }//GEN-LAST:event_lbl_hapusMouseClicked

    private void lbl_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterMouseClicked
        dataFilter();
        autosumTotal();
    }//GEN-LAST:event_lbl_filterMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JComboBox<String> cmb_bulan;
    private javax.swing.JComboBox<String> cmb_tahun;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_edit;
    private javax.swing.JLabel lbl_evaluasi;
    private javax.swing.JLabel lbl_filter;
    private javax.swing.JLabel lbl_hapus;
    private javax.swing.JLabel lbl_pemasukan;
    private javax.swing.JLabel lbl_tambah;
    private javax.swing.JLabel lbl_total_pengeluaran;
    private javax.swing.JTable tabel_pengeluaran;
    // End of variables declaration//GEN-END:variables
}
