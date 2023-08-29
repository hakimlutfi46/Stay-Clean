/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu;

import koneksi.Connect;
import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import SubMenu.Popup.Karyawan.*;
import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author perlengkapan
 */
public class KaryawanFrame extends javax.swing.JInternalFrame {

    public String id_akun = "";
    boolean editHapus;

    /**
     * Creates new form KaryawanFrame
     */
    public KaryawanFrame() {
        initComponents();
        load_tabel("");
        clear();

        //ilangin border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tabel.getTableHeader().setBackground(new Color(0, 168, 209));

        editHapus = false;
    }

    private void numberOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^0-9]", ""));
    }

    private void alphabetOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^a-zA-Z\\s+]", ""));
    }

    //merubah ukuran kolom pada tabel, setting column
    private void prefredWidthTable() {
        //set width column table
        TableColumnModel kolomModel = tabel.getColumnModel();

        kolomModel.getColumn(0).setMaxWidth(60);
        kolomModel.getColumn(0).setPreferredWidth(60);
        kolomModel.getColumn(1).setMaxWidth(185);
        kolomModel.getColumn(1).setPreferredWidth(185);
        kolomModel.getColumn(2).setPreferredWidth(220);
        kolomModel.getColumn(3).setPreferredWidth(103);
        kolomModel.getColumn(4).setPreferredWidth(97);
        kolomModel.getColumn(5).setPreferredWidth(161);
        kolomModel.getColumn(6).setPreferredWidth(210);

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

    private void updateDb(String sql) {
        try {
            Connection conn = Connect.GetConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void load_tabel(String param) {

        DefaultTableModel model = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        model.addColumn("NO");
        model.addColumn("Nama");
        model.addColumn("Email");
        model.addColumn("Scan KTP");
        model.addColumn("Hak Akses");
        model.addColumn("Nama Pengguna");
        model.addColumn("Kata Sandi");

        try {
            int no = 1;
            ResultSet res = readDb("SELECT * FROM akun " + param);

            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("email"),
                    res.getString("scan_ktp"),
                    res.getString("hak_akses"),
                    res.getString("username"),
                    res.getString("password")
                });
            }

            tabel.setModel(model);
            prefredWidthTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // method untuk memngkosongkan textfield
    public void clear() {
        txt_nama1.setText(null);
        txt_email.setText(null);
        txt_namaUser.setText(null);
        txt_kataSandi.setText(null);
        txt_cari.setText(null);
        txt_rfid.setText(null);
        cb_hakakses.setSelectedIndex(0);
        tabel.clearSelection();
    }

    public void setID_akun(String id_akun) {
        this.id_akun = id_akun;
    }

    public void addplaceholderstyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }

    public void removeplaceholderstyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nama1 = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_namaUser = new javax.swing.JTextField();
        txt_kataSandi = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        txt_rfid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        cb_hakakses = new javax.swing.JComboBox<>();
        btn_clear = new javax.swing.JPanel();
        btn_hapus = new javax.swing.JPanel();
        btn_edit = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setBackground(new Color(0,0,0,0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama1.setBackground(new Color(0,0,0,0));
        txt_nama1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_nama1.setForeground(new java.awt.Color(0, 0, 0));
        txt_nama1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_nama1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nama1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nama1KeyTyped(evt);
            }
        });
        getContentPane().add(txt_nama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 300, 30));

        txt_email.setBackground(new Color(0,0,0,0));
        txt_email.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_email.setForeground(new java.awt.Color(0, 0, 0));
        txt_email.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_emailKeyReleased(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 91, 300, 30));

        txt_namaUser.setBackground(new Color(0,0,0,0));
        txt_namaUser.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_namaUser.setForeground(new java.awt.Color(0, 0, 0));
        txt_namaUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_namaUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_namaUserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namaUserKeyTyped(evt);
            }
        });
        getContentPane().add(txt_namaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 178, 300, 30));

        txt_kataSandi.setBackground(new Color(0,0,0,0));
        txt_kataSandi.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_kataSandi.setForeground(new java.awt.Color(0, 0, 0));
        txt_kataSandi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(txt_kataSandi, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 178, 300, 30));

        txt_cari.setBackground(new Color(0,0,0,0));
        txt_cari.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_cari.setForeground(new java.awt.Color(153, 153, 153));
        txt_cari.setBorder(null);
        txt_cari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariFocusLost(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 362, 223, 30));

        txt_rfid.setBackground(new Color(0,0,0,0));
        txt_rfid.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_rfid.setForeground(new java.awt.Color(0, 0, 0));
        txt_rfid.setBorder(null);
        txt_rfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_rfidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rfidKeyTyped(evt);
            }
        });
        getContentPane().add(txt_rfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 300, 30));

        tabel.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.getTableHeader().setResizingAllowed(false);
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 1020, 180));

        cb_hakakses.setBackground(new Color(0,0,0,0));
        cb_hakakses.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        cb_hakakses.setForeground(new java.awt.Color(0, 0, 0));
        cb_hakakses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Hak Akses...", "Admin", "Karyawan" }));
        getContentPane().add(cb_hakakses, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 260, 300, 33));

        btn_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clear.setOpaque(false);
        btn_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_clearLayout = new javax.swing.GroupLayout(btn_clear);
        btn_clear.setLayout(btn_clearLayout);
        btn_clearLayout.setHorizontalGroup(
            btn_clearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btn_clearLayout.setVerticalGroup(
            btn_clearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 358, 150, 38));

        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.setOpaque(false);
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_hapusLayout = new javax.swing.GroupLayout(btn_hapus);
        btn_hapus.setLayout(btn_hapusLayout);
        btn_hapusLayout.setHorizontalGroup(
            btn_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btn_hapusLayout.setVerticalGroup(
            btn_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 358, 150, 38));

        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.setOpaque(false);
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_editLayout = new javax.swing.GroupLayout(btn_edit);
        btn_edit.setLayout(btn_editLayout);
        btn_editLayout.setHorizontalGroup(
            btn_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btn_editLayout.setVerticalGroup(
            btn_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 358, 150, 38));

        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.setOpaque(false);
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_tambahLayout = new javax.swing.GroupLayout(btn_tambah);
        btn_tambah.setLayout(btn_tambahLayout);
        btn_tambahLayout.setHorizontalGroup(
            btn_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        btn_tambahLayout.setVerticalGroup(
            btn_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 358, 150, 38));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setForeground(new java.awt.Color(255, 255, 255));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Karyawan Frame.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusGained
        // TODO add your handling code here:
        if (txt_cari.getText().equals("Cari")) {
            txt_cari.setText(null);
            txt_cari.requestFocus();
            removeplaceholderstyle(txt_cari);
        }
    }//GEN-LAST:event_txt_cariFocusGained

    private void txt_cariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariFocusLost
        // TODO add your handling code here:
        if (txt_cari.getText().length() == 0) {

            addplaceholderstyle(txt_cari);
            txt_cari.setText("Cari");
        }
    }//GEN-LAST:event_txt_cariFocusLost

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        String cari = txt_cari.getText();
        load_tabel("WHERE nama LIKE '" + cari + "%'");
    }//GEN-LAST:event_txt_cariKeyReleased

    private void btn_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseClicked
        clear();
        load_tabel("");
    }//GEN-LAST:event_btn_clearMouseClicked

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked
        try {
            ResultSet res = readDb("SELECT COUNT(id_akun) AS jumlah FROM akun WHERE hak_akses = 'Admin'");
            if (res.next()) {
                if (Integer.parseInt(res.getString("jumlah")) == 2 && cb_hakakses.getSelectedItem().equals("Admin")) {
                    new PopupGagal("Jumlah admin sudah melebihi batas").setVisible(true);
                } else {
                    if (editHapus == true) {
                        new PopupGagal("Data sudah ada dan tidak bisa ditambah<br>Silahkan klik tombol edit untuk update data").setVisible(true);
                    } else {
                        String email = (String) txt_email.getText();

                        if (txt_nama1.getText().equals("")) {
                            new PopupGagal("Silahkan isi data nama terlebih dahulu").setVisible(true);
                        } else if (txt_email.getText().equals("")) {
                            new PopupGagal("Silahkan isi data email terlebih dahulu").setVisible(true);
                        } else if (!email.contains("@gmail.com")) {
                            new PopupGagal("Silahkan isi data email dengan benar").setVisible(true);
                        } else if (txt_namaUser.getText().equals("")) {
                            new PopupGagal("Silahkan isi data nama pengguna terlebih dahulu").setVisible(true);
                        } else if (txt_kataSandi.getText().equals("")) {
                            new PopupGagal("Silahkan isi data kata sandi terlebih dahulu").setVisible(true);
                        } else if (txt_rfid.getText().equals("")) {
                            new PopupGagal("Silahkan isi data RFID terlebih dahulu").setVisible(true);
                        } else if (cb_hakakses.getSelectedIndex() == 0) {
                            new PopupGagal("Silahkan pilih data hak akses terlebih dahulu").setVisible(true);
                        } else if (txt_kataSandi.getText().length() < 6) {
                            new PopupGagal("Silahkan masukkan kata sandi minimal 6 karakter").setVisible(true);
                        } else {
                            try {
                                //cek database
                                ResultSet resCek = readDb("SELECT * FROM akun WHERE email = '" + txt_email.getText() + "' "
                                        + "AND scan_ktp = '" + txt_rfid.getText() + "' "
                                        + "AND username = '" + txt_namaUser.getText() + "' "
                                        + "AND PASSWORD = '" + txt_kataSandi.getText() + "'");

                                if (resCek.next()) {
                                    new PopupGagal("Data sudah ada pada database").setVisible(true);
                                } else {
                                    insertDb("INSERT INTO akun VALUES (null, '" + txt_rfid.getText() + "', '" + txt_nama1.getText() + "',"
                                            + "'" + cb_hakakses.getSelectedItem().toString() + "', '" + txt_email.getText() + "',"
                                            + "'" + txt_namaUser.getText() + "','" + txt_kataSandi.getText() + "')");
                                    load_tabel("");
                                    clear();

                                    JDesktopPane desktopPane = getDesktopPane();
                                    new PopupSukses("Data berhasil ditambahkan", "karyawan", desktopPane).setVisible(true);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                new PopupGagal("Data gagal ditambahkan").setVisible(true);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        if (editHapus == false) {
            new PopupGagal("Silahkan pilih datanya terlebih dahulu").setVisible(true);
        } else {
            String email = (String) txt_email.getText();

            if (txt_nama1.getText().equals("")) {
                new PopupGagal("Silahkan isi data nama terlebih dahulu").setVisible(true);
            } else if (txt_email.getText().equals("")) {
                new PopupGagal("Silahkan isi data email terlebih dahulu").setVisible(true);
            } else if (!email.contains("@gmail.com")) {
                new PopupGagal("Silahkan isi data email dengan benar").setVisible(true);
            } else if (txt_namaUser.getText().equals("")) {
                new PopupGagal("Silahkan isi data nama pengguna terlebih dahulu").setVisible(true);
            } else if (txt_kataSandi.getText().equals("")) {
                new PopupGagal("Silahkan isi data kata sandi terlebih dahulu").setVisible(true);
            } else if (txt_rfid.getText().equals("")) {
                new PopupGagal("Silahkan isi data RFID terlebih dahulu").setVisible(true);
            } else if (cb_hakakses.getSelectedIndex() == 0) {
                new PopupGagal("Silahkan pilih data hak akses terlebih dahulu").setVisible(true);
            } else if (txt_kataSandi.getText().length() < 6) {
                new PopupGagal("Silahkan masukkan kata sandi minimal 6 karakter").setVisible(true);
            } else {
                try {
                    ResultSet resCek = readDb("SELECT * FROM akun WHERE email = '" + txt_email.getText() + "' "
                            + "AND scan_ktp = '" + txt_rfid.getText() + "' "
                            + "AND username = '" + txt_namaUser.getText() + "' "
                            + "AND PASSWORD = '" + txt_kataSandi.getText() + "'");

                    if (resCek.next()) {
                        new PopupGagal("Data sudah ada pada database").setVisible(true);
                    } else {
                        insertDb("UPDATE `akun` SET `nama` = '" + txt_nama1.getText() + "', scan_ktp = '" + txt_rfid.getText() + "',"
                                + "`hak_akses` = '" + cb_hakakses.getSelectedItem().toString() + "', "
                                + "`email` = '" + txt_email.getText() + "', `username` = '" + txt_namaUser.getText()
                                + "',`password` = '" + txt_kataSandi.getText() + "' "
                                + "WHERE `akun`.`id_akun` = '" + this.id_akun + "'");

                        load_tabel("");
                        clear();

                        JDesktopPane desktopPane = getDesktopPane();
                        new PopupSukses("Data berhasil diedit", "karyawan", desktopPane).setVisible(true);
                    }
                } catch (Exception e) {
                    new PopupGagal("Data gagal diedit").setVisible(true);
                }
            }
        }

    }//GEN-LAST:event_btn_editMouseClicked

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        if (editHapus == false) {
            new PopupGagal("Silahkan pilih datanya terlebih dahulu").setVisible(true);
        } else {
            int i = tabel.getSelectedRow();
            if (i == -1) {
                new PopupGagal("Silahkan pilih data yang ingin dihapus terlebih dahulu").setVisible(true);
            } else {
                JDesktopPane desktopPane = getDesktopPane();
                new PopupKonfirmasi("Apakah anda yakin ingin menghapus data ini?", this.id_akun, desktopPane).setVisible(true);
            }
        }

    }//GEN-LAST:event_btn_hapusMouseClicked

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        editHapus = true;

        int i = tabel.getSelectedRow();
        TableModel tbl = tabel.getModel();
        // Mengambil value dari table
        String field2 = tbl.getValueAt(i, 1).toString();
        String field3 = tbl.getValueAt(i, 2).toString();
        String field4 = tbl.getValueAt(i, 3).toString();
        String field5 = tbl.getValueAt(i, 4).toString();
        String field6 = tbl.getValueAt(i, 5).toString();
        String field7 = tbl.getValueAt(i, 6).toString();
        // Paste data yang telah diambil
        txt_nama1.setText(field2);
        txt_email.setText(field3);
        txt_rfid.setText(field4);
        cb_hakakses.setSelectedItem(field5);
        txt_namaUser.setText(field6);
        txt_kataSandi.setText(field7);

        //ngambil id
        try {
            ResultSet res = readDb("Select * from akun where email = '" + field3 + "'");
            if (res.next()) {
                setID_akun(res.getString("id_akun"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void txt_nama1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama1KeyReleased
        alphabetOnly(txt_nama1);
    }//GEN-LAST:event_txt_nama1KeyReleased

    private void txt_emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailKeyReleased
        txt_email.setText(txt_email.getText().replaceAll("[^a-zA-Z0-9@.]", ""));
    }//GEN-LAST:event_txt_emailKeyReleased

    private void txt_namaUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaUserKeyReleased
        txt_namaUser.setText(txt_namaUser.getText().replaceAll("[^a-z]", ""));
        txt_namaUser.setText(txt_namaUser.getText().toLowerCase());
        txt_namaUser.setText(txt_namaUser.getText().replaceAll("\\s+", ""));
    }//GEN-LAST:event_txt_namaUserKeyReleased

    private void txt_rfidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rfidKeyReleased
        numberOnly(txt_rfid);
    }//GEN-LAST:event_txt_rfidKeyReleased

    private void txt_rfidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rfidKeyTyped
        if (txt_rfid.getText().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_rfidKeyTyped

    private void txt_nama1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama1KeyTyped
        if (txt_nama1.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nama1KeyTyped

    private void txt_namaUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaUserKeyTyped
        if (txt_namaUser.getText().length() >= 30) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_namaUserKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel btn_clear;
    private javax.swing.JPanel btn_edit;
    private javax.swing.JPanel btn_hapus;
    private javax.swing.JPanel btn_tambah;
    private javax.swing.JComboBox<String> cb_hakakses;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_kataSandi;
    private javax.swing.JTextField txt_nama1;
    private javax.swing.JTextField txt_namaUser;
    private javax.swing.JTextField txt_rfid;
    // End of variables declaration//GEN-END:variables
}
