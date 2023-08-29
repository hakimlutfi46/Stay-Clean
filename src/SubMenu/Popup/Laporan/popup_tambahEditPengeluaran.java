package SubMenu.Popup.Laporan;

import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import koneksi.Connect;

public class popup_tambahEditPengeluaran extends javax.swing.JFrame {

    JDesktopPane pane;
    String tgl_input;

    public static void restrictToCurrentMonth(JDateChooser dateChooser) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Mengatur tanggal menjadi 1 untuk mendapatkan awal bulan ini
        Date minDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); // Menambahkan satu bulan ke tanggal sekarang
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Mengurangi satu hari untuk mendapatkan akhir bulan ini
        Date maxDate = calendar.getTime();

        dateChooser.setMinSelectableDate(minDate);
        dateChooser.setMaxSelectableDate(maxDate);
    }

    public popup_tambahEditPengeluaran() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));

        unViewWarning();
        restrictToCurrentMonth(tgl_pengeluaran);

    }

    public void setTanggalInput(String date) {
        this.tgl_input = date;
    }

    // Metode getter untuk lbl_nama_pengeluaran
    public JTextField getNamaPengeluaranLabel() {
        return lbl_nama_pengeluaran;
    }

    // Metode setter untuk lbl_nama_pengeluaran
    public void setNamaPengeluaranLabel(String text) {
        lbl_nama_pengeluaran.setText(text);
    }

    // Metode getter untuk tgl_pengeluaran
    public JDateChooser getTanggalPengeluaranChooser() {
        return tgl_pengeluaran;
    }

    // Metode setter untuk tgl_pengeluaran
    public void setTanggalPengeluaranChooser(Date date) {
        tgl_pengeluaran.setDate(date);
    }

    // Metode getter untuk cmb_kategori
    public JComboBox<String> getKategoriComboBox() {
        return cmb_kategori;
    }

    // Metode setter untuk cmb_kategori
    public void setKategoriComboBox(String selectedItem) {
        cmb_kategori.setSelectedItem(selectedItem);
    }

    // Metode getter untuk lbl_tagihan
    public JTextField getTagihanLabel() {
        return lbl_tagihan;
    }

    // Metode setter untuk lbl_tagihan
    public void setTagihanLabel(String text) {
        lbl_tagihan.setText(text);
    }

    // Metode getter untuk deskripsi
    public JTextArea getDeskripsiTextArea() {
        return deskripsi;
    }

    // Metode setter untuk deskripsi
    public void setDeskripsiTextArea(String text) {
        deskripsi.setText(text);
    }

    //format decimal
    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    //untuk menghilangkan titik
    public static String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    public void addTittle() {
        lbl_judul.setText("Tambah Data");
        lbl_tambahOrEdit.setText("Tambah");
    }

    public void editTitle() {
        lbl_judul.setText("Edit Data");
        lbl_tambahOrEdit.setText("Edit");
    }

    private void unViewWarning() {
        warning_deskripsi.setVisible(false);
        warning_kategori.setVisible(false);
        warning_nama.setVisible(false);
        warning_tagihan.setVisible(false);
        warning_tgl_pengeluaran.setVisible(false);
    }

    // Method untuk refresh kembali ke halaman submenu
    public void setBackPane(JDesktopPane pane) {
        this.pane = pane;
    }

    private void saveData() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tgl_pengeluaran.getDate()));

        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst;
            String sql;

            if (lbl_judul.getText().equals("Edit Data")) {
                // Perintah untuk melakukan UPDATE data
                String searchId = "SELECT * FROM pengeluaran WHERE tanggal_input = ?";
                pst = conn.prepareStatement(searchId);
                pst.setString(1, this.tgl_input);
                ResultSet res = pst.executeQuery();

                if (res.next()) {
                    String idPengeluaran = res.getString("id_pengeluaran");
                    sql = "UPDATE pengeluaran SET "
                            + "nama_pengeluaran = ?, "
                            + "kategori = ?, "
                            + "tagihan = ?, "
                            + "tanggal_pengeluaran = ?, "
                            + "deskripsi = ?,"
                            + "tanggal_input = NOW() "
                            + "WHERE id_pengeluaran = ?";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, lbl_nama_pengeluaran.getText());
                    pst.setString(2, cmb_kategori.getSelectedItem().toString());
                    pst.setString(3, reFormat(lbl_tagihan.getText()));
                    pst.setString(4, tanggal);
                    pst.setString(5, deskripsi.getText());
                    pst.setString(6, idPengeluaran);

                    pst.executeUpdate();

                    new PopupSukses("Data berhasil diedit", "laporanPengeluaran", pane).setVisible(true);

                } else {
                    // jika data tidak ditemukan
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                    System.out.println(this.tgl_input);
                    return; // Keluar dari method saveData()
                }

            } else if (lbl_judul.getText().equals("Tambah Data")) {

                sql = "INSERT INTO pengeluaran (`id_pengeluaran`, `nama_pengeluaran`,`kategori`,`tagihan`,"
                        + "`tanggal_pengeluaran`,`deskripsi`, `tanggal_input`) VALUES (?, ?, ?, ?, ?, ?, NOW())";

                pst = conn.prepareStatement(sql);
                pst.setString(1, "");
                pst.setString(2, lbl_nama_pengeluaran.getText());
                pst.setString(3, cmb_kategori.getSelectedItem().toString());
                pst.setString(4, reFormat(lbl_tagihan.getText()));
                pst.setString(5, tanggal);
                pst.setString(6, deskripsi.getText());

                pst.executeUpdate();

                new PopupSukses("Data berhasil ditambahkan", "laporanPengeluaran", pane).setVisible(true);
            }

            this.dispose();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void clear() {
        lbl_nama_pengeluaran.setText(null);
        cmb_kategori.setSelectedIndex(0);
        tgl_pengeluaran.setDate(null);
        lbl_tagihan.setText(null);
        deskripsi.setText(null);
    }

    private void unView() {
        warning_nama.setVisible(false);
        warning_tagihan.setVisible(false);
        warning_kategori.setVisible(false);
        warning_deskripsi.setVisible(false);
        warning_tgl_pengeluaran.setVisible(false);
    }

    private void viewWarning() {
        if (lbl_nama_pengeluaran.getText().equals("")) {
            warning_nama.setVisible(true);
        } else {
            warning_nama.setVisible(false);
        }

        if (lbl_tagihan.getText().equals("")) {
            warning_tagihan.setVisible(true);
        } else {
            warning_tagihan.setVisible(false);
        }

        if (deskripsi.getText().equals("")) {
            warning_deskripsi.setVisible(true);
        } else {
            warning_deskripsi.setVisible(false);
        }

        if (cmb_kategori.getSelectedIndex() == 0) {
            warning_kategori.setVisible(true);
        } else {
            warning_kategori.setVisible(false);
        }

        if (tgl_pengeluaran.getDate() == null) {
            warning_tgl_pengeluaran.setVisible(true);
        } else {
            warning_tgl_pengeluaran.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_batal = new javax.swing.JLabel();
        lbl_tambahOrEdit = new javax.swing.JLabel();
        lbl_judul = new javax.swing.JLabel();
        lbl_nama_pengeluaran = new javax.swing.JTextField();
        cmb_kategori = new javax.swing.JComboBox<>();
        lbl_tagihan = new javax.swing.JTextField();
        tgl_pengeluaran = new com.toedter.calendar.JDateChooser();
        deskripsi = new javax.swing.JTextArea();
        warning_tgl_pengeluaran = new javax.swing.JLabel();
        warning_nama = new javax.swing.JLabel();
        warning_kategori = new javax.swing.JLabel();
        warning_tagihan = new javax.swing.JLabel();
        warning_deskripsi = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_batal.setBackground(new Color(0,0,0,0)
        );
        lbl_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_batalMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 520, 139, 40));

        lbl_tambahOrEdit.setBackground(new Color(0,0,0,0)
        );
        lbl_tambahOrEdit.setFont(new java.awt.Font("Poppins Medium", 1, 17)); // NOI18N
        lbl_tambahOrEdit.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tambahOrEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tambahOrEdit.setText(".");
        lbl_tambahOrEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_tambahOrEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tambahOrEditMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_tambahOrEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(864, 521, 139, 40));

        lbl_judul.setBackground(new Color(0,0,0,0));
        lbl_judul.setFont(new java.awt.Font("Poppins ExtraBold", 0, 36)); // NOI18N
        lbl_judul.setForeground(new java.awt.Color(255, 255, 255));
        lbl_judul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_judul.setText(".");
        getContentPane().add(lbl_judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 144, 390, -1));

        lbl_nama_pengeluaran.setBackground(new Color(0,0,0,0)
        );
        lbl_nama_pengeluaran.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_nama_pengeluaran.setBorder(null);
        lbl_nama_pengeluaran.setSelectionColor(new java.awt.Color(1, 176, 213));
        getContentPane().add(lbl_nama_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 309, 273, 28));

        cmb_kategori.setBackground(new Color(0,0,0,0)
        );
        cmb_kategori.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        cmb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Bulanan", "Tak terduga" }));
        getContentPane().add(cmb_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 391, 273, 28));

        lbl_tagihan.setBackground(new Color(0,0,0,0)
        );
        lbl_tagihan.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lbl_tagihan.setBorder(null);
        lbl_tagihan.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lbl_tagihan.setSelectionColor(new java.awt.Color(1, 176, 213));
        lbl_tagihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_tagihanKeyReleased(evt);
            }
        });
        getContentPane().add(lbl_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 390, 273, 28));

        tgl_pengeluaran.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        getContentPane().add(tgl_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 309, 290, 28));

        deskripsi.setBackground(new Color(0,0,0,0)
        );
        deskripsi.setColumns(20);
        deskripsi.setFont(new java.awt.Font("Poppins Medium", 0, 13)); // NOI18N
        deskripsi.setRows(5);
        deskripsi.setBorder(null);
        deskripsi.setSelectionColor(new java.awt.Color(1, 176, 213));
        getContentPane().add(deskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 474, 273, 80));

        warning_tgl_pengeluaran.setForeground(new java.awt.Color(255, 0, 0));
        warning_tgl_pengeluaran.setText("*nama pengeluaran harus diisi");
        getContentPane().add(warning_tgl_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, -1, -1));

        warning_nama.setForeground(new java.awt.Color(255, 0, 0));
        warning_nama.setText("*nama pengeluaran harus diisi");
        getContentPane().add(warning_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

        warning_kategori.setForeground(new java.awt.Color(255, 0, 0));
        warning_kategori.setText("*kategori harus diisi");
        getContentPane().add(warning_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, -1));

        warning_tagihan.setForeground(new java.awt.Color(255, 0, 0));
        warning_tagihan.setText("*tagihan harus diisi");
        getContentPane().add(warning_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, -1, -1));

        warning_deskripsi.setForeground(new java.awt.Color(255, 0, 0));
        warning_deskripsi.setText("*deskripsi harus diisi");
        getContentPane().add(warning_deskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 563, -1, -1));

        bg.setBackground(new Color(0,0,0,0));
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/PopUp/Tambah&Edit pengeluaran.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1365, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_batalMouseClicked
        clear();
        unViewWarning();
        this.dispose();
    }//GEN-LAST:event_lbl_batalMouseClicked

    private void lbl_tambahOrEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tambahOrEditMouseClicked
        saveData();

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tgl_pengeluaran.getDate()));

        if (lbl_judul.equals("Edit Data")) {
            if (lbl_nama_pengeluaran.getText().isEmpty() || cmb_kategori.getSelectedItem() == null
                    || lbl_tagihan.getText().isEmpty() || tanggal.isEmpty() || deskripsi.getText().isEmpty()) {
                viewWarning();
                new PopupGagal("Data gagal diedit").setVisible(true);
                return;
            }
        } else {
            if (lbl_nama_pengeluaran.getText().isEmpty() || cmb_kategori.getSelectedItem() == null
                    || lbl_tagihan.getText().isEmpty() || tanggal.isEmpty() || deskripsi.getText().isEmpty()) {
                viewWarning();
                new PopupGagal("Data gagal ditambahkan").setVisible(true);
                return;
            }
        }
    }//GEN-LAST:event_lbl_tambahOrEditMouseClicked

    private void lbl_tagihanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_tagihanKeyReleased
        if (lbl_tagihan.getText().length() > 0) {
            int tagihanVar = Integer.parseInt(reFormat(lbl_tagihan.getText()));
            lbl_tagihan.setText(formatDec(tagihanVar));
        }
    }//GEN-LAST:event_lbl_tagihanKeyReleased

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
            java.util.logging.Logger.getLogger(popup_tambahEditPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popup_tambahEditPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popup_tambahEditPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popup_tambahEditPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new popup_tambahEditPengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JComboBox<String> cmb_kategori;
    private javax.swing.JTextArea deskripsi;
    private javax.swing.JLabel lbl_batal;
    private javax.swing.JLabel lbl_judul;
    private javax.swing.JTextField lbl_nama_pengeluaran;
    private javax.swing.JTextField lbl_tagihan;
    private javax.swing.JLabel lbl_tambahOrEdit;
    private com.toedter.calendar.JDateChooser tgl_pengeluaran;
    private javax.swing.JLabel warning_deskripsi;
    private javax.swing.JLabel warning_kategori;
    private javax.swing.JLabel warning_nama;
    private javax.swing.JLabel warning_tagihan;
    private javax.swing.JLabel warning_tgl_pengeluaran;
    // End of variables declaration//GEN-END:variables
}
