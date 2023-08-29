/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu;

import SubMenu.Popup.PopupGagal;
import SubMenu.Popup.PopupSukses;
import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import koneksi.Connect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author rayrayaray
 */
public class TransaksiFrame extends javax.swing.JInternalFrame {

    int hari = 0;
    int jam = 0;
    int harga = 0;
    boolean klikEditHapus = false;
    List<Object> dataHari = new ArrayList<>();
    List<Object> dataJam = new ArrayList<>();

    /**
     * Creates new form TransaksiForm
     */
    public TransaksiFrame() {
        initComponents();
        txt_nama.setFont(new Font("Poppins", Font.PLAIN, 16));
        txt_nope.setEditable(false);
        txt_alamat.setEditable(false);
        TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
        combo_jenisPaket.setEnabled(false);
        txt_estimasi.setText("");
        txt_estimasi.setEditable(false);
        tbl_daftarPesanan.getTableHeader().setBackground(new Color(0, 168, 209));

        //ilangin border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        //tambah suggestion
        suggestionTextfield();

        //combo paket
        readComboPaket();

        //combo rak
        readComboRak();

        //set table
        dataTable();

        //validasi inputan string atau int
        cekInputanIntegerOnly(txt_nope);
        cekInputanStringOnly(txt_nama);
        cekInputanIntegerOnly(txt_jumlahSatuan);
        cekInputanIntegerOnly(txt_bayar);
    }

    public void dataTable() {
        DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tbl.addColumn("No.");
        tbl.addColumn("Nama Paket");
        tbl.addColumn("Jenis Paket");
        tbl.addColumn("Jumlah Satuan");
        tbl.addColumn("Rak");
        tbl.addColumn("Qty");
        tbl.addColumn("Harga");
        tbl.addColumn("Sub Total");
        tbl.addColumn("Estimasi hari");
        tbl.addColumn("Estimasi jam");

        tbl_daftarPesanan.setModel(tbl);
        preferedWidthTable();
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

    private String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    private String reFormat(String val) {
        return val.replaceAll("\\.", "");
    }

    public void setIdAkun(String id) {
        lbl_idAkun.setText(id);
    }

    private void cekInputanIntegerOnly(JTextField textfield) {
        textfield.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textfield.getText();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    textfield.setEditable(true);
                } else if (ke.getKeyCode() == 8) {
                    textfield.setEditable(true);
                } else {
                    textfield.setEditable(false);
                }
            }
        });
    }

    private void cekInputanStringOnly(JTextField textfield) {
        textfield.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textfield.getText();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    textfield.setEditable(false);
                } else if (ke.getKeyCode() == 8 || ke.getKeyCode() == 49) {
                    textfield.setEditable(true);
                } else {
                    textfield.setEditable(true);
                }
            }
        });
    }

    private void preferedWidthTable() {
        TableColumnModel columnModel = tbl_daftarPesanan.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(4).setPreferredWidth(10);
        columnModel.getColumn(5).setPreferredWidth(10);
        columnModel.getColumn(8).setMaxWidth(0);
        columnModel.getColumn(8).setMinWidth(0);
        columnModel.getColumn(8).setPreferredWidth(0);
        columnModel.getColumn(9).setMaxWidth(0);
        columnModel.getColumn(9).setMinWidth(0);
        columnModel.getColumn(9).setPreferredWidth(0);
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
        txt_alamat = new javax.swing.JTextArea();
        txt_nama = new textfield_suggestion.TextFieldSuggestion();
        txt_nope = new javax.swing.JTextField();
        combo_paket = new javax.swing.JComboBox<>();
        label_satuan = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_daftarPesanan = new javax.swing.JTable();
        btn_proses = new javax.swing.JPanel();
        txt_bayar = new javax.swing.JTextField();
        txt_total = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        lbl_kembalian = new javax.swing.JLabel();
        checkbox_bayarLunas = new javax.swing.JCheckBox();
        combo_jenisPaket = new javax.swing.JComboBox<>();
        txt_jumlahSatuan = new javax.swing.JTextField();
        combo_rak = new javax.swing.JComboBox<>();
        btn_hapus = new javax.swing.JPanel();
        btn_edit = new javax.swing.JPanel();
        btn_clear = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JPanel();
        txt_estimasi = new javax.swing.JTextField();
        btn_dataTransaksi = new utility.custom_panel.PanelRound();
        background = new javax.swing.JLabel();
        lbl_sisa = new javax.swing.JLabel();
        lbl_idAkun = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        txt_alamat.setBackground(new java.awt.Color(217, 217, 217));
        txt_alamat.setColumns(18);
        txt_alamat.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_alamat.setForeground(new java.awt.Color(0, 0, 0));
        txt_alamat.setLineWrap(true);
        txt_alamat.setRows(2);
        txt_alamat.setWrapStyleWord(true);
        txt_alamat.setBorder(null);
        jScrollPane1.setViewportView(txt_alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 354, 190, 60));

        txt_nama.setBorder(null);
        txt_nama.setForeground(new java.awt.Color(0, 0, 0));
        txt_nama.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_namaKeyPressed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 180, 190, 31));

        txt_nope.setBackground(new java.awt.Color(217, 217, 217));
        txt_nope.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_nope.setForeground(new java.awt.Color(0, 0, 0));
        txt_nope.setBorder(null);
        txt_nope.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nopeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_nope, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 266, 190, 31));

        combo_paket.setBackground(new Color(0,0,0,0));
        combo_paket.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        combo_paket.setForeground(new java.awt.Color(0, 0, 0));
        combo_paket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Paket..." }));
        combo_paket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_paket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_paketActionPerformed(evt);
            }
        });
        getContentPane().add(combo_paket, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 263, 37));

        label_satuan.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        label_satuan.setForeground(new java.awt.Color(0, 0, 0));
        label_satuan.setText("Kg");
        getContentPane().add(label_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 260, 40, 37));

        tbl_daftarPesanan.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tbl_daftarPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nama Paket", "Jenis Paket", "Jumlah Satuan", "Rak", "Qty", "Harga", "Sub Total"
            }
        ));
        tbl_daftarPesanan.getTableHeader().setResizingAllowed(false);
        tbl_daftarPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_daftarPesananMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_daftarPesanan);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 760, 134));

        btn_proses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_proses.setOpaque(false);
        btn_proses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_prosesMouseClicked(evt);
            }
        });
        getContentPane().add(btn_proses, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, 250, 140));

        txt_bayar.setBackground(new java.awt.Color(217, 217, 217));
        txt_bayar.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        txt_bayar.setForeground(new java.awt.Color(0, 0, 0));
        txt_bayar.setBorder(null);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 346, 180, 28));

        txt_total.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        txt_total.setForeground(new java.awt.Color(0, 0, 0));
        txt_total.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 180, 33));

        txt_qty.setBackground(new java.awt.Color(217, 217, 217));
        txt_qty.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        txt_qty.setForeground(new java.awt.Color(0, 0, 0));
        txt_qty.setBorder(null);
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtyKeyTyped(evt);
            }
        });
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 341, 30, 27));

        lbl_kembalian.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        lbl_kembalian.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 400, 180, 30));
        getContentPane().add(checkbox_bayarLunas, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 195, -1, 31));

        combo_jenisPaket.setBackground(new Color(0,0,0,0));
        combo_jenisPaket.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        combo_jenisPaket.setForeground(new java.awt.Color(0, 0, 0));
        combo_jenisPaket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Paket..." }));
        combo_jenisPaket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(combo_jenisPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 257, 172, 37));

        txt_jumlahSatuan.setBackground(new java.awt.Color(217, 217, 217));
        txt_jumlahSatuan.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        txt_jumlahSatuan.setForeground(new java.awt.Color(0, 0, 0));
        txt_jumlahSatuan.setBorder(null);
        txt_jumlahSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlahSatuanKeyTyped(evt);
            }
        });
        getContentPane().add(txt_jumlahSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 260, 30, 30));

        combo_rak.setBackground(new Color(0,0,0,0));
        combo_rak.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        combo_rak.setForeground(new java.awt.Color(0, 0, 0));
        combo_rak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Rak..." }));
        combo_rak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_rak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_rakActionPerformed(evt);
            }
        });
        getContentPane().add(combo_rak, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 336, 200, 36));

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
            .addGap(0, 57, Short.MAX_VALUE)
        );
        btn_hapusLayout.setVerticalGroup(
            btn_hapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 394, 57, 37));

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
            .addGap(0, 55, Short.MAX_VALUE)
        );
        btn_editLayout.setVerticalGroup(
            btn_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 394, 55, 37));

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
            .addGap(0, 56, Short.MAX_VALUE)
        );
        btn_clearLayout.setVerticalGroup(
            btn_clearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 394, 56, 37));

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
            .addGap(0, 55, Short.MAX_VALUE)
        );
        btn_tambahLayout.setVerticalGroup(
            btn_tambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 394, 55, 37));

        txt_estimasi.setBackground(new java.awt.Color(217, 217, 217));
        txt_estimasi.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        txt_estimasi.setForeground(new java.awt.Color(0, 0, 0));
        txt_estimasi.setBorder(null);
        getContentPane().add(txt_estimasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 146, 310, 30));

        btn_dataTransaksi.setBackground(new Color(0,0,0,0));
        btn_dataTransaksi.setRoundBottomRight(40);
        btn_dataTransaksi.setRoundTopRight(40);
        btn_dataTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dataTransaksiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_dataTransaksiLayout = new javax.swing.GroupLayout(btn_dataTransaksi);
        btn_dataTransaksi.setLayout(btn_dataTransaksiLayout);
        btn_dataTransaksiLayout.setHorizontalGroup(
            btn_dataTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        btn_dataTransaksiLayout.setVerticalGroup(
            btn_dataTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(btn_dataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 21, 133, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Transaksi Frame .jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(lbl_sisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 70, 20));

        lbl_idAkun.setText("jLabel1");
        getContentPane().add(lbl_idAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void readComboPaket() {
        try {
            ResultSet res = readDb("Select * from paket");
            while (res.next()) {
                combo_paket.addItem(res.getString("tipe_paket").toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readComboJenisPaket() {
        combo_jenisPaket.addItem("Pilih Jenis Paket...");
        try {
            Connection conn = Connect.GetConnection();

            //cari id paket
            ResultSet res = readDb("Select id_paket from paket where tipe_paket = "
                    + "'" + combo_paket.getSelectedItem().toString() + "'");
            res.next();

            //cari detail_paket
            ResultSet rs = readDb("Select * from detail_paket where id_paket = '" + res.getString("id_paket") + "'");

            while (rs.next()) {
                combo_jenisPaket.addItem(rs.getString("nama_paket"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void readComboRak() {
        try {
            ResultSet res = readDb("select * from rak where status = 'Kosong' or status = 'Belum Penuh'");

            //add data ke combo
            while (res.next()) {
                combo_rak.addItem(res.getString("nama_rak"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void clearSelection() {
        combo_paket.setSelectedIndex(0);
        combo_jenisPaket.addItem("Pilih Jenis Paket...");
        combo_jenisPaket.setSelectedIndex(0);
        txt_jumlahSatuan.setText("");
        tbl_daftarPesanan.clearSelection();
        combo_rak.addItem("Pilih Rak...");
        combo_rak.setEnabled(true);
        combo_rak.setSelectedIndex(0);
        txt_qty.setText("");
        combo_paket.requestFocus();

    }

    private void clearAll() {
        TransaksiFrame tf = new TransaksiFrame();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(tf);
        tf.setVisible(true);

        this.dispose();
    }

    private void suggestionTextfield() {
        try {
            ResultSet res = readDb("Select * from pelanggan");
            while (res.next()) {
                txt_nama.addItemSuggestion(res.getString("nama_pelanggan"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void txt_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyPressed
        try {
            String isiNama = txt_nama.getText();
            ResultSet res = readDb("SELECT * FROM pelanggan WHERE nama_pelanggan = '" + isiNama + "'");
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (res.next()) {
                    txt_nama.setText("");
                    txt_nope.setText("");
                    txt_alamat.setText("");
                    txt_nope.setEditable(false);
                    txt_alamat.setEditable(false);
                    txt_nama.setText(res.getString("nama_pelanggan"));
                    txt_nope.setText(res.getString("nope_pelanggan"));
                    txt_alamat.setText(res.getString("alamat_pelanggan"));
                } else {
                    txt_nope.requestFocus();
                    txt_nope.setEditable(true);
                    txt_alamat.setEditable(true);
                    txt_nope.setText("");
                    txt_alamat.setText("");
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                txt_nama.setEditable(true);
                txt_nope.setText("");
                txt_alamat.setText("");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txt_namaKeyPressed

    private void combo_paketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_paketActionPerformed
        combo_jenisPaket.setEnabled(true);
        combo_jenisPaket.removeAllItems();
        readComboJenisPaket();

        if (combo_paket.getSelectedIndex() == 0) {
            combo_jenisPaket.setEnabled(false);
            txt_jumlahSatuan.setText("");
            txt_jumlahSatuan.setEnabled(true);
            txt_qty.setEnabled(true);
        } else if (combo_paket.getSelectedItem().toString().equals("Satuan")) {
            label_satuan.setText("Pcs");
            txt_jumlahSatuan.setText("1");
            txt_jumlahSatuan.setEnabled(false);
            txt_qty.setText("1");
            txt_qty.setEnabled(false);
        } else {
            label_satuan.setText("Kg");
            txt_jumlahSatuan.setText("");
            txt_jumlahSatuan.setEnabled(true);
            txt_qty.setEnabled(true);
        }
    }//GEN-LAST:event_combo_paketActionPerformed

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) tbl_daftarPesanan.getModel();
        int hargaPaket = 0;
        int estimasiHari = 0;
        int estimasiJam = 0;

        if (combo_paket.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih paket terlebih dahulu..").setVisible(true);
        } else if (combo_jenisPaket.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih jenis paket terlebih dahulu..").setVisible(true);
        } else if (txt_jumlahSatuan.getText().equals("")) {
            new PopupGagal("Silahkan isi jumlah satuan terlebih dahulu..").setVisible(true);
        } else if (combo_rak.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih tempat rak terlebih dahulu..").setVisible(true);
        } else if (txt_qty.getText().equals("")) {
            new PopupGagal("Silahkan isi jumlah barang laundry terlebih dahulu..").setVisible(true);
        } else if (combo_paket.getSelectedIndex() == 1 && combo_jenisPaket.getSelectedIndex() == 1 && Integer.parseInt(txt_jumlahSatuan.getText()) < 5) {
            new PopupGagal("Pastikan kembali jenis paket dan jumlah satuan yang dipilih!").setVisible(true);
        } else if (combo_paket.getSelectedIndex() == 1 && combo_jenisPaket.getSelectedIndex() == 2 && Integer.parseInt(txt_jumlahSatuan.getText()) >= 5) {
            new PopupGagal("Pastikan kembali jenis paket dan jumlah satuan yang dipilih!").setVisible(true);
        } else if (lbl_sisa.getText().equals("Penuh")) {
            new PopupGagal("Rak yang anda pilih sudah penuh<br>Silahkan pilih rak lain").setVisible(true);
        } else {
            String paket = combo_paket.getSelectedItem().toString();
            String jenisPaket = combo_jenisPaket.getSelectedItem().toString();
            String jumlahSatuan = txt_jumlahSatuan.getText();
            String qty = txt_qty.getText();
            String rak = combo_rak.getSelectedItem().toString();
            String statusRak = lbl_sisa.getText();

            try {

                if (statusRak.equals("Kosong")) {
                    updateDb("Update rak set status = 'Belum Penuh' where nama_rak = '" + rak + "'");
                } else if (statusRak.equals("Belum Penuh")) {
                    updateDb("Update rak set status = 'Penuh' where nama_rak = '" + rak + "'");
                } else if (statusRak.equals("Penuh")) {
                    updateDb("Update rak set status = 'Penuh' where nama_rak = '" + rak + "'");
                } else {
                    System.out.println(lbl_sisa);
                }

                //ambil id paket
                ResultSet hasilCariIdPaket = readDb("select * from paket where tipe_paket = '" + paket + "'");
                hasilCariIdPaket.next();
                String idPaket = hasilCariIdPaket.getString("id_paket");

                //ambil estimasi dan harga
                ResultSet hasilCari = readDb("select * from detail_paket where id_paket = '" + idPaket + "' "
                        + "and nama_paket='" + jenisPaket + "'");
                hasilCari.next();

                estimasiHari = Integer.parseInt(hasilCari.getString("durasi_paket (hari)"));
                estimasiJam = Integer.parseInt(hasilCari.getString("durasi_paket (jam)"));
                hargaPaket = Integer.parseInt(hasilCari.getString("harga_paket"));

                //untuk perbadingan estimasi
//                dataHari.add(estimasiHari);
//                int hariTerbanyak = 0;
//
//                dataJam.add(estimasiJam);
//                int jamTerbanyak = 0;
                //nentuin estimasi
                if (txt_estimasi.getText().equals("") && txt_total.getText().equals("")) {
                    hari = estimasiHari;
                    jam = estimasiJam;
                    harga = hargaPaket * Integer.parseInt(jumlahSatuan);
                } else {

//                    for (int i = 0; i <= dataHari.size(); i++) {
//                        if (Integer.parseInt(String.valueOf(dataHari.get(i))) > hariTerbanyak) {
//                            hariTerbanyak = Integer.parseInt(String.valueOf(dataHari.get(i)));
//                        }
//                    }
//
//                    for (int i = 0; i <= dataJam.size(); i++) {
//                        if (Integer.parseInt(String.valueOf(dataJam.get(i))) > jamTerbanyak) {
//                            jamTerbanyak = Integer.parseInt(String.valueOf(dataJam.get(i)));
//                        }
//                    }
//
//                    hari = hariTerbanyak;
//                    jam = jamTerbanyak;
//                    if (jam >= estimasiJam) {
//                        jam = jam;
//                    } else if (jam <= estimasiJam) {
//                        jam = estimasiJam;
//                    }
//
//                    if (hari >= estimasiHari) {
//                        hari = hari;
//                    } else if (hari <= estimasiHari) {
//                        hari = estimasiHari;
//                    }
                    jam = jam + estimasiJam;
                    hari = hari + estimasiHari;
                    harga = harga + (hargaPaket * Integer.parseInt(jumlahSatuan));
                }

                txt_estimasi.setText(hari + " Hari dan " + jam + " Jam");
                txt_total.setText(formatDec(harga));
                clearSelection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            int rowCount = dtm.getRowCount() + 1;
            dtm.addRow(new Object[]{
                rowCount,
                paket,
                jenisPaket,
                jumlahSatuan,
                rak,
                qty,
                hargaPaket,
                hargaPaket * Integer.parseInt(jumlahSatuan),
                estimasiHari,
                estimasiJam
            });

        }
    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseClicked
        clearSelection();
    }//GEN-LAST:event_btn_clearMouseClicked

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        DefaultTableModel edit = (DefaultTableModel) tbl_daftarPesanan.getModel();
        int i = tbl_daftarPesanan.getSelectedRow();
        String dataPaketTable = edit.getValueAt(i, 1).toString();
        String dataJenisPaketTable = edit.getValueAt(i, 2).toString();
        String jumlahSatuanTable = edit.getValueAt(i, 3).toString();
        int estimasiHari = 0;
        int estimasiJam = 0;

        String paket = combo_paket.getSelectedItem().toString();
        String jenisPaket = combo_jenisPaket.getSelectedItem().toString();
        String jumlahSatuan = txt_jumlahSatuan.getText();
        String qty = txt_qty.getText();
        String rak = combo_rak.getSelectedItem().toString();
        int hargaPaket = 0;

        if (combo_paket.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih paket terlebih dahulu..").setVisible(true);
        } else if (combo_jenisPaket.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih jenis paket terlebih dahulu..").setVisible(true);
        } else if (txt_jumlahSatuan.getText().equals("")) {
            new PopupGagal("Silahkan isi jumlah satuan terlebih dahulu..").setVisible(true);
        } else if (combo_rak.getSelectedIndex() == 0) {
            new PopupGagal("Silahkan pilih tempat rak terlebih dahulu..").setVisible(true);
        } else if (txt_qty.getText().equals("")) {
            new PopupGagal("Silahkan isi jumlah barang laundry terlebih dahulu..").setVisible(true);
        } else if (combo_paket.getSelectedIndex() == 1 && combo_jenisPaket.getSelectedIndex() == 1 && Integer.parseInt(txt_jumlahSatuan.getText()) < 5) {
            new PopupGagal("Pastikan kembali jenis paket dan jumlah satuan yang dipilih!").setVisible(true);
        } else if (combo_paket.getSelectedIndex() == 1 && combo_jenisPaket.getSelectedIndex() == 2 && Integer.parseInt(txt_jumlahSatuan.getText()) >= 5) {
            new PopupGagal("Pastikan kembali jenis paket dan jumlah satuan yang dipilih!").setVisible(true);
        } else if (lbl_sisa.getText().equals("Penuh")) {
            new PopupGagal("Rak yang anda pilih sudah penuh<br>Silahkan pilih rak lain").setVisible(true);
        } else {
            try {

                //ambil estimasi dan harga yang di table
                ResultSet hasilCariIdPaketTable = readDb("select * from paket where tipe_paket = '" + dataPaketTable + "'");
                hasilCariIdPaketTable.next();
                String idPaketTable = hasilCariIdPaketTable.getString("id_paket");

                ResultSet hasilCariTable = readDb("select * from detail_paket where id_paket = '" + idPaketTable + "' "
                        + "and nama_paket='" + dataJenisPaketTable + "'");
                hasilCariTable.next();

                int estimasiHariTable = Integer.parseInt(hasilCariTable.getString("durasi_paket (hari)"));
                int estimasiJamTable = Integer.parseInt(hasilCariTable.getString("durasi_paket (jam)"));
                int hargaPaketTable = Integer.parseInt(hasilCariTable.getString("harga_paket"));

                //ambil estimasi dan harga yang di combobox
                ResultSet hasilCariIdPaket = readDb("select * from paket where tipe_paket = '" + paket + "'");
                hasilCariIdPaket.next();
                String idPaket = hasilCariIdPaket.getString("id_paket");

                ResultSet hasilCari = readDb("select * from detail_paket where id_paket = '" + idPaket + "' "
                        + "and nama_paket='" + jenisPaket + "'");
                hasilCari.next();

                estimasiHari = Integer.parseInt(hasilCari.getString("durasi_paket (hari)"));
                estimasiJam = Integer.parseInt(hasilCari.getString("durasi_paket (jam)"));
                hargaPaket = Integer.parseInt(hasilCari.getString("harga_paket"));

                //nentuin estimasi
//            if (estimasiJamTable >= estimasiJam) {
//                jam = estimasiJamTable;
//            } else if (jam <= estimasiJam) {
//                jam = estimasiJam;
//            }
//
//            if (estimasiHariTable >= estimasiHari) {
//                hari = estimasiHariTable;
//            } else if (hari <= estimasiHari) {
//                hari = estimasiHari;
//            }
                jam = jam - estimasiJamTable + estimasiJam;
                hari = hari - estimasiHariTable + estimasiHari;
                harga = harga - (hargaPaketTable * Integer.parseInt(jumlahSatuanTable)) + (hargaPaket * Integer.parseInt(jumlahSatuan));

                txt_estimasi.setText(hari + " Hari dan " + jam + " Jam");
                txt_total.setText(formatDec(harga));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            edit.removeRow(i);
            clearSelection();
            edit.insertRow(i, new Object[]{
                i + 1,
                paket,
                jenisPaket,
                jumlahSatuan,
                rak,
                qty,
                hargaPaket,
                hargaPaket * Integer.parseInt(jumlahSatuan),
                estimasiHari,
                estimasiJam
            });
        }
    }//GEN-LAST:event_btn_editMouseClicked

    private void tbl_daftarPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_daftarPesananMouseClicked
        int i = tbl_daftarPesanan.getSelectedRow();
        TableModel model = tbl_daftarPesanan.getModel();

        String field2 = model.getValueAt(i, 1).toString();
        String field3 = model.getValueAt(i, 2).toString();
        String field4 = model.getValueAt(i, 3).toString();
        String field5 = model.getValueAt(i, 4).toString();
        String field6 = model.getValueAt(i, 5).toString();

        combo_paket.setSelectedItem(field2);
        combo_jenisPaket.setSelectedItem(field3);
        txt_jumlahSatuan.setText(field4);
        combo_rak.setEnabled(false);
        combo_rak.setSelectedItem(field5);
        txt_qty.setText(field6);
    }//GEN-LAST:event_tbl_daftarPesananMouseClicked

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        String paket = combo_paket.getSelectedItem().toString();
        String jenisPaket = combo_jenisPaket.getSelectedItem().toString();
        String jumlahSatuan = txt_jumlahSatuan.getText();
        String statusRak = lbl_sisa.getText();
        String rak = combo_rak.getSelectedItem().toString();

        DefaultTableModel edit = (DefaultTableModel) tbl_daftarPesanan.getModel();
        int j = tbl_daftarPesanan.getSelectedRow();
        edit.removeRow(j);

        try {

            if (statusRak.equals("Penuh")) {
                updateDb("Update rak set status = 'Belum Penuh' where nama_rak = '" + rak + "'");
            } else if (statusRak.equals("Belum Penuh")) {
                updateDb("Update rak set status = 'Kosong' where nama_rak = '" + rak + "'");
            } else if (statusRak.equals("Kosong")) {
                updateDb("Update rak set status = 'Kosong' where nama_rak = '" + rak + "'");
            } else {
                System.out.println(lbl_sisa);
            }

            ResultSet hasilCariIdPaket = readDb("select * from paket where tipe_paket = '" + paket + "'");
            hasilCariIdPaket.next();
            String idPaket = hasilCariIdPaket.getString("id_paket");

            //ambil estimasi
            ResultSet hasilCari = readDb("select * from detail_paket where id_paket = '" + idPaket + "' "
                    + "and nama_paket='" + jenisPaket + "'");
            hasilCari.next();

            int estimasiHari = Integer.parseInt(hasilCari.getString("durasi_paket (hari)"));
            int estimasiJam = Integer.parseInt(hasilCari.getString("durasi_paket (jam)"));
            int hargaPaket = Integer.parseInt(hasilCari.getString("harga_paket"));

//            dataHari.remove(estimasiHari);
//            int hariTerbanyak = 0;
//
//            dataJam.remove(estimasiJam);
//            int jamTerbanyak = 0;
//
//            for (int i = 0; i <= dataHari.size(); i++) {
//                if (Integer.parseInt(String.valueOf(dataHari.get(i))) > hariTerbanyak) {
//                    hariTerbanyak = Integer.parseInt(String.valueOf(dataHari.get(i)));
//                }
//            }
//
//            for (int i = 0; i <= dataJam.size(); i++) {
//                if (Integer.parseInt(String.valueOf(dataJam.get(i))) > jamTerbanyak) {
//                    jamTerbanyak = Integer.parseInt(String.valueOf(dataJam.get(i)));
//                }
//            }
//
//            hari = hariTerbanyak;
//            jam = jamTerbanyak;
            //nentuin estimasi
//            if (jam >= estimasiJam) {
//                jam = jam;
//            } else if (jam <= estimasiJam) {
//                jam = estimasiJam;
//            }
//
//            if (hari >= estimasiHari) {
//                hari = hari;
//            } else if (hari <= estimasiHari) {
//                hari = estimasiHari;
//            }
            jam = jam - estimasiJam;
            hari = hari - estimasiHari;
            harga = harga - (hargaPaket * Integer.parseInt(jumlahSatuan));

            txt_estimasi.setText(hari + " Hari dan " + jam + " Jam");
            txt_total.setText(formatDec(harga));
            clearSelection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        int inputanBayar = Integer.parseInt(reFormat(txt_bayar.getText()));
        txt_bayar.setText(formatDec(inputanBayar));

        int total = Integer.parseInt(reFormat(txt_total.getText()));
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

    private void btn_prosesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prosesMouseClicked
        JDesktopPane desktopPane = getDesktopPane();

        if (checkbox_bayarLunas.isSelected() && lbl_kembalian.getText().equals("Uang Kurang")) {
            new PopupGagal("Silahkan cek pembayarannya kembali").setVisible(true);
        } else if (txt_nama.getText().equals("")) {
            new PopupGagal("Silahkan isi nama pelanggan terlebih dahulu").setVisible(true);
        } else if (txt_nope.getText().equals("")) {
            new PopupGagal("Silahkan isi nomor hp pelanggan terlebih dahulu").setVisible(true);
        } else if (txt_alamat.getText().equals("")) {
            new PopupGagal("Silahkan isi alamat pelanggan terlebih dahulu").setVisible(true);
        } else if (tbl_daftarPesanan.getRowCount() == 0) {
            new PopupGagal("Silahkan isi data laundry terlebih dahulu").setVisible(true);
        } else {
            try {
                int kembalian;
                String nama = txt_nama.getText();
                String noHP = txt_nope.getText();
                String alamat = txt_alamat.getText();
                String total = txt_total.getText();
                String tunai = txt_bayar.getText();
                String statusBayar;
                if (reFormat(total).equals(reFormat(tunai))) {
                    statusBayar = "Lunas";
                } else {
                    statusBayar = "Belum Lunas";
                }

                if (lbl_kembalian.getText().equals("Uang Kurang")) {
                    kembalian = 0;
                } else {
                    kembalian = Integer.parseInt(reFormat(lbl_kembalian.getText()));
                }

                //ambil id pelanggan
                ResultSet hasilCariPelanggan = readDb("Select * from pelanggan where nama_pelanggan = '" + nama + "' "
                        + "and nope_pelanggan = '" + noHP + "'");
                String idPelanggan = "";
                if (hasilCariPelanggan.next()) {
                    idPelanggan = hasilCariPelanggan.getString("id_pelanggan");
                } else {
                    insertDb("insert into pelanggan values (null, '" + txt_nama.getText() + "', '" + txt_nope.getText() + "', '" + txt_alamat.getText() + "')");
                    ResultSet res = readDb("select max(id_pelanggan) as id_pelanggan from pelanggan");
                    if (res.next()) {
                        idPelanggan = res.getString("id_pelanggan");
                    }
                }

                //ambil id akun
                String idAkun = lbl_idAkun.getText();

                //nentuin jam dan hari estimasi
//                DefaultTableModel model = (DefaultTableModel) tbl_daftarPesanan.getModel();
//                for (int row = 0; row < model.getRowCount(); row++) {
//                    Object value = model.getValueAt(row, 8);
//                    dataHari.add(value);
//                }
//                for (int row = 0; row < model.getRowCount(); row++) {
//                    Object value = model.getValueAt(row, 9);
//                    dataJam.add(value);
//                }
//
//                int hariTerbanyak = 0;
//                int jamTerbanyak = 0;
//
//                for (int i = 0; i <= dataHari.size(); i++) {
//                    if (Integer.parseInt(String.valueOf(dataHari.get(i))) > hariTerbanyak) {
//                        hariTerbanyak = Integer.parseInt(String.valueOf(dataHari.get(i)));
//                    }
//                }
//
//                for (int i = 0; i <= dataJam.size(); i++) {
//                    if (Integer.parseInt(String.valueOf(dataJam.get(i))) > jamTerbanyak) {
//                        jamTerbanyak = Integer.parseInt(String.valueOf(dataJam.get(i)));
//                    }
//                }
//
//                hari = hariTerbanyak;
//                jam = jamTerbanyak;
                //nentuin estimasi
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Calendar c = Calendar.getInstance();
                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.DAY_OF_WEEK, this.hari); //tambahan harinya
                c.add(Calendar.HOUR_OF_DAY, this.jam); //tambahan jamnya
                if (22 < c.getTime().getHours()) {
                    int sisaJam = c.getTime().getHours() - 22;
                    c.set(Calendar.HOUR_OF_DAY, 22);
                    c.add(Calendar.HOUR_OF_DAY, 11 + sisaJam);
                }

                //insert into transaksi
                insertDb("insert into transaksi values (null, '" + idPelanggan + "', '" + idAkun + "', "
                        + "'" + reFormat(total) + "', '" + reFormat(tunai) + "', '" + kembalian + "', "
                        + "'" + statusBayar + "', 'Belum Diambil', '" + sdf.format(c.getTime()) + "', now(), null)");

                //ambil id transaksi terbaru
                ResultSet hasilCariIdTransaksi = readDb("select max(id_transaksi) as id_transaksi from transaksi");
                hasilCariIdTransaksi.next();
                String idTransaksi = hasilCariIdTransaksi.getString("id_transaksi");

                //insert detail_transaksi
                int rowCount = tbl_daftarPesanan.getModel().getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String jenisPaket = tbl_daftarPesanan.getValueAt(i, 2).toString();
                    String jumlahSatuan = tbl_daftarPesanan.getValueAt(i, 3).toString();
                    String rak = tbl_daftarPesanan.getValueAt(i, 4).toString();
                    String qty = tbl_daftarPesanan.getValueAt(i, 5).toString();
                    String subTotal = tbl_daftarPesanan.getValueAt(i, 7).toString();

                    //ambil id detail paket
                    ResultSet hasilCariIdPaket = readDb("select * from detail_paket where nama_paket = '" + jenisPaket + "'");
                    hasilCariIdPaket.next();
                    String idDetailPaket = hasilCariIdPaket.getString("id_detailPaket");

                    //ambil id rak
                    ResultSet hasilCariRak = readDb("select * from rak where nama_rak = '" + rak + "'");
                    hasilCariRak.next();
                    String idRak = hasilCariRak.getString("id_rak");

                    //insert into detail transaksi
                    insertDb("insert into detail_transaksi values('" + idTransaksi + "', '" + idDetailPaket + "', '" + idRak + "',"
                            + "'" + qty + "', '" + jumlahSatuan + "', '" + subTotal + "')");
                }
                clearAll();

                Connection conn = Connect.GetConnection();

                Linear barcode = new Linear();
                barcode.setType(Linear.CODE39);
                barcode.setData(idTransaksi);
                String fName = idTransaksi;
                barcode.renderBarcode("src/assets/img/barcode/" + fName + ".png");

                String report = ("E:\\Project Kelompok 3 Semester 2\\Stay Clean_FIX\\StayClean\\src\\SubMenu\\Nota\\NotaTransaksi.jrxml");
                HashMap hash = new HashMap();
                hash.put("id_transaksi", idTransaksi);
                hash.put("path", "src/assets/img/barcode/" + fName + ".png");
                JasperReport jasper = JasperCompileManager.compileReport(report);
                JasperPrint jasperP = JasperFillManager.fillReport(jasper, hash, conn);

//               JasperViewer.viewReport(jasperP, false); // untuk liat preview
                JasperPrintManager.printReport(jasperP, false);
                System.out.println(desktopPane);
                new PopupSukses("Data Berhasil Diproses", "transaksi", desktopPane).setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }//GEN-LAST:event_btn_prosesMouseClicked

    private void txt_jumlahSatuanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahSatuanKeyTyped
        String isianJumlah = txt_jumlahSatuan.getText();
        if (isianJumlah.length() >= 2) {
            txt_jumlahSatuan.setEditable(false);
        }
    }//GEN-LAST:event_txt_jumlahSatuanKeyTyped

    private void txt_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyTyped
        String isianJumlah = txt_jumlahSatuan.getText();
        if (isianJumlah.length() >= 2) {
            txt_jumlahSatuan.setEditable(false);
        }
    }//GEN-LAST:event_txt_qtyKeyTyped

    private void btn_dataTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataTransaksiMouseClicked
        DataTransaksiFrame dtf = new DataTransaksiFrame();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(dtf);
        dtf.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btn_dataTransaksiMouseClicked

    private void txt_nopeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nopeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_alamat.requestFocus();
        }
    }//GEN-LAST:event_txt_nopeKeyPressed

    private void combo_rakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_rakActionPerformed
        try {
            ResultSet res = readDb("Select * from rak where nama_rak = '" + combo_rak.getSelectedItem().toString() + "'");
            if (res.next()) {
                lbl_sisa.setText(res.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_combo_rakActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel btn_clear;
    private utility.custom_panel.PanelRound btn_dataTransaksi;
    private javax.swing.JPanel btn_edit;
    private javax.swing.JPanel btn_hapus;
    private javax.swing.JPanel btn_proses;
    private javax.swing.JPanel btn_tambah;
    private javax.swing.JCheckBox checkbox_bayarLunas;
    private javax.swing.JComboBox<String> combo_jenisPaket;
    private javax.swing.JComboBox<String> combo_paket;
    private javax.swing.JComboBox<String> combo_rak;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_satuan;
    private javax.swing.JLabel lbl_idAkun;
    private javax.swing.JLabel lbl_kembalian;
    private javax.swing.JLabel lbl_sisa;
    private javax.swing.JTable tbl_daftarPesanan;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_estimasi;
    private javax.swing.JTextField txt_jumlahSatuan;
    private textfield_suggestion.TextFieldSuggestion txt_nama;
    private javax.swing.JTextField txt_nope;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JLabel txt_total;
    // End of variables declaration//GEN-END:variables
}
