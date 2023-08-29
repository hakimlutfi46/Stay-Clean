/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu;

//import SubMenu.Popup.popup_konfirmHapus;
import SubMenu.Popup.Pelanggan.popup_konfirmHapus;
import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import java.sql.*;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import koneksi.Connect;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author perlengkapan
 */
public class PelangganFrame extends javax.swing.JInternalFrame {

    String idPelanggan = "";
    /**
     * Creates new form PelangganFrame
     */
    public boolean hapus;
    public boolean edit;

    public PelangganFrame() {
        initComponents();
        datatable();
        getData();

        hapus = false;
        edit = false;

        //ilangin border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        tabelPelanggan.getTableHeader().setBackground(new Color(0, 168, 209));
    }

    private void caseSensitive(JTextField textfield) {
        textfield.setText(textfield.getText().toLowerCase());
        textfield.setText(textfield.getText().replaceAll("\\s+", ""));
    }

    private void numberOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^0-9]", ""));
    }

    private void alphabetOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^a-zA-Z\\s+]", ""));
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

    public void clear() {
        tf_namaCustomer.setText(null);
        tf_noTelp.setText(null);
        tf_alamat.setText(null);
        tabelPelanggan.clearSelection();
        tf_idPelanggan.setText(null);
        tf_cari.setText(null);
    }

    public void datatable() {

        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbl.addColumn("ID Pelanggan");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("No Telepon");
        tbl.addColumn("Alamat");

        tabelPelanggan.setModel(tbl);
        try {
            ResultSet res = readDb("select * from pelanggan order by id_pelanggan DESC");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_pelanggan"),
                    res.getString("nama_pelanggan"),
                    res.getString("nope_pelanggan"),
                    res.getString("alamat_pelanggan"),});
                tabelPelanggan.setModel(tbl);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getData() {
        try {
            ResultSet result = readDb("select * from pelanggan order by id_pelanggan DESC");

            DefaultTableModel model = (DefaultTableModel) tabelPelanggan.getModel();

            model.setRowCount(0);
            while (result.next()) {
                String id = result.getString("id_pelanggan");
                String nama = result.getString("nama_pelanggan");
                String nope = result.getString("nope_pelanggan");
                String alamat = result.getString("alamat_pelanggan");

                model.addRow(new Object[]{
                    id,
                    nama,
                    nope,
                    alamat
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cariData() {

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pelanggan");
        tbl.addColumn("Nama Pelanggan");
        tbl.addColumn("No Telepon");
        tbl.addColumn("Alamat");

        tabelPelanggan.setModel(tbl);

        String cari = tf_cari.getText();

        try {
            ResultSet res = readDb("select * from pelanggan where nama_pelanggan like'%" + cari + "%' or alamat_pelanggan like '%" + cari + "%'");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_pelanggan"),
                    res.getString("nama_pelanggan"),
                    res.getString("nope_pelanggan"),
                    res.getString("alamat_pelanggan"),});
                tabelPelanggan.setModel(tbl);
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

        tf_namaCustomer = new javax.swing.JTextField();
        tf_cari = new javax.swing.JTextField();
        tf_noTelp = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JLabel();
        btn_clear = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        JScrollPane1 = new javax.swing.JScrollPane();
        tf_alamat = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();
        tf_idPelanggan = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tf_namaCustomer.setBackground(new Color(0,0,0,0));
        tf_namaCustomer.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        tf_namaCustomer.setBorder(null);
        tf_namaCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_namaCustomerKeyReleased(evt);
            }
        });
        getContentPane().add(tf_namaCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 107, 290, 30));

        tf_cari.setBackground(new Color(0,0,0,0));
        tf_cari.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tf_cari.setBorder(null);
        tf_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_cariKeyReleased(evt);
            }
        });
        getContentPane().add(tf_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(848, 303, 190, 33));

        tf_noTelp.setBackground(new Color(0,0,0,0));
        tf_noTelp.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        tf_noTelp.setBorder(null);
        tf_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_noTelpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(tf_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 290, 30));

        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 147, 39));

        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editMouseClicked(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 147, 39));

        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 147, 39));

        btn_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clearMouseClicked(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 147, 39));

        tabelPelanggan.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPelanggan.getTableHeader().setResizingAllowed(false);
        tabelPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelangganMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPelanggan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 1010, 240));

        JScrollPane1.setBackground(new Color(0,0,0,0));
        JScrollPane1.setBorder(null);

        tf_alamat.setBackground(new Color(0,0,0,0));
        tf_alamat.setColumns(13);
        tf_alamat.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        tf_alamat.setLineWrap(true);
        tf_alamat.setRows(4);
        tf_alamat.setWrapStyleWord(true);
        tf_alamat.setOpaque(false);
        JScrollPane1.setViewportView(tf_alamat);

        getContentPane().add(JScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, 120));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Data Pelanggan Frame.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tf_idPelanggan.setText("ppp");
        getContentPane().add(tf_idPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 90, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked
        if (edit == true) {
            new PopupGagal("Data sudah ada dan tidak bisa ditambah<br>Silahkan klik tombol edit untuk update data").setVisible(true);
        } else {
            if (tf_namaCustomer.getText().equals("") || tf_noTelp.getText().equals("") || tf_alamat.getText().equals("")) {
                new PopupGagal("Silahkan isi datanya terlebih dahulu..").setVisible(true);
            } else if (tf_noTelp.getText().length() < 12) {
                new PopupGagal("Silahkan cek kembali nomor handphone yang dimasukkan").setVisible(true);
            } else {
                try {
                    ResultSet res = readDb("select * from pelanggan where nope_pelanggan = '" + tf_noTelp + "'");
                    if (res.next()) {
                        new PopupGagal("Data sudah ada pada database").setVisible(true);
                    } else {
                        insertDb("INSERT INTO pelanggan VALUES ('', '" + tf_namaCustomer.getText() + "','" + tf_noTelp.getText() + "','" + tf_alamat.getText() + "')");

                        clear();
                        getData();

                        JDesktopPane desktopPane = getDesktopPane();
                        new PopupSukses("Data berhasil ditambahkan", "pelanggan", desktopPane).setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_clearMouseClicked

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        if (edit == false) {
            new PopupGagal("Silahkan pilih datanya terlebih dahulu").setVisible(true);
        } else {
            if (tf_namaCustomer.getText().equals("") || tf_noTelp.getText().equals("") || tf_alamat.getText().equals("")) {
                new PopupGagal("Silahkan isi datanya terlebih dahulu..").setVisible(true);
            } else if (tf_noTelp.getText().length() < 12) {
                new PopupGagal("Silahkan cek kembali nomor handphone yang dimasukkan").setVisible(true);
            } else {
                try {
                    ResultSet res = readDb("select * from pelanggan where nope_pelanggan = '" + tf_noTelp + "'");
                    if (res.next()) {
                        new PopupGagal("Data sudah ada pada database").setVisible(true);
                    } else {
                        Connection con = (Connection) Connect.GetConnection();
                        PreparedStatement pst = con.prepareStatement("update pelanggan SET nama_pelanggan = '" + tf_namaCustomer.getText() + "', "
                                + "nope_pelanggan = '" + tf_noTelp.getText() + "', alamat_pelanggan = '" + tf_alamat.getText() + "' "
                                + "where id_pelanggan = '" + tf_idPelanggan.getText() + "'");

                        pst.executeUpdate();
                        clear();
                        getData();
                        edit = false;

                        JDesktopPane desktopPane = getDesktopPane();
                        new PopupSukses("Data berhasil diedit", "pelanggan", desktopPane).setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_editMouseClicked

    private void tabelPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelangganMouseClicked
        hapus = true;
        edit = true;

        int row = tabelPelanggan.rowAtPoint(evt.getPoint());

        String id_pelanggan = tabelPelanggan.getValueAt(row, 0).toString();
        tf_idPelanggan.setText(id_pelanggan);

        //lempar id
        this.idPelanggan = id_pelanggan;

        String nama = tabelPelanggan.getValueAt(row, 1).toString();
        tf_namaCustomer.setText(nama);

        String telp = tabelPelanggan.getValueAt(row, 2).toString();
        tf_noTelp.setText(telp);

        String alamat = tabelPelanggan.getValueAt(row, 3).toString();
        tf_alamat.setText(alamat);
    }//GEN-LAST:event_tabelPelangganMouseClicked

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        // TODO add your handling code here:
        if (hapus == false) {
            new PopupGagal("Silahkan pilih datanya terlebih dahulu").setVisible(true);
        } else {
            JDesktopPane desktopPane = getDesktopPane();
            new popup_konfirmHapus("Apakah anda yakin<br>ingin menghapus data ini?", idPelanggan, desktopPane).setVisible(true);
        }
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void tf_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_cariKeyReleased
        cariData();
    }//GEN-LAST:event_tf_cariKeyReleased

    private void tf_namaCustomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_namaCustomerKeyReleased
        alphabetOnly(tf_namaCustomer);
    }//GEN-LAST:event_tf_namaCustomerKeyReleased

    private void tf_noTelpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_noTelpKeyReleased
        numberOnly(tf_noTelp);
    }//GEN-LAST:event_tf_noTelpKeyReleased

    private void tf_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_noTelpKeyTyped
        if (tf_noTelp.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_tf_noTelpKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_clear;
    private javax.swing.JLabel btn_edit;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JLabel btn_tambah;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tabelPelanggan;
    private javax.swing.JTextArea tf_alamat;
    private javax.swing.JTextField tf_cari;
    public javax.swing.JLabel tf_idPelanggan;
    private javax.swing.JTextField tf_namaCustomer;
    private javax.swing.JTextField tf_noTelp;
    // End of variables declaration//GEN-END:variables
}
