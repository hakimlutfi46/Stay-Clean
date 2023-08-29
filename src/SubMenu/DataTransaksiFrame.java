/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubMenu;

import SubMenu.Popup.Transaksi.PopupDetailTransaksi;
import SubMenu.Popup.Transaksi.PopupKonfirmasiPengambilan;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDesktopPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import koneksi.Connect;
import utility.custom_table.table.TableCustom;

/**
 *
 * @author rayrayaray
 */
public class DataTransaksiFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form DataTransaksiFrame
     */
    public DataTransaksiFrame() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        tbl_dataTransaksi.getTableHeader().setBackground(new Color(0, 168, 209));

        //ilangin border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bif = (BasicInternalFrameUI) this.getUI();
        bif.setNorthPane(null);

        dataTable();
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

        tbl_dataTransaksi.setModel(dtm);

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
                    + "JOIN pelanggan ON pelanggan.id_pelanggan = transaksi.id_pelanggan");

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
        TableColumnModel columnModel = tbl_dataTransaksi.getColumnModel();

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

    private void filterTable(String filter) {
        DefaultTableModel model = (DefaultTableModel) tbl_dataTransaksi.getModel(); // Mendapatkan model tabel

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model); // Membuat sorter untuk model tabel
        tbl_dataTransaksi.setRowSorter(sorter); // Menetapkan sorter pada tabel

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + filter); // Membuat row filter dengan teks filter (case-insensitive)
        sorter.setRowFilter(rowFilter); // Menetapkan row filter pada sorter
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_transaksi = new utility.custom_panel.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dataTransaksi = new javax.swing.JTable();
        btn_detailTransaksi = new javax.swing.JPanel();
        btn_pengambilan = new javax.swing.JPanel();
        tf_filter = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_transaksi.setBackground(new Color(0,0,0,0));
        btn_transaksi.setRoundBottomLeft(40);
        btn_transaksi.setRoundTopLeft(40);
        btn_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_transaksiLayout = new javax.swing.GroupLayout(btn_transaksi);
        btn_transaksi.setLayout(btn_transaksiLayout);
        btn_transaksiLayout.setHorizontalGroup(
            btn_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );
        btn_transaksiLayout.setVerticalGroup(
            btn_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 21, 85, 30));

        jScrollPane1.setBorder(null);

        tbl_dataTransaksi.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tbl_dataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        tbl_dataTransaksi.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tbl_dataTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 137, 1000, 370));

        btn_detailTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_detailTransaksi.setOpaque(false);
        btn_detailTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_detailTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(btn_detailTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 530, 158, 37));

        btn_pengambilan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pengambilan.setOpaque(false);
        btn_pengambilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengambilanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_pengambilanLayout = new javax.swing.GroupLayout(btn_pengambilan);
        btn_pengambilan.setLayout(btn_pengambilanLayout);
        btn_pengambilanLayout.setHorizontalGroup(
            btn_pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        btn_pengambilanLayout.setVerticalGroup(
            btn_pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(btn_pengambilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 530, 260, 40));

        tf_filter.setBackground(new Color(0,0,0,0)
        );
        tf_filter.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        tf_filter.setBorder(null);
        tf_filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_filterKeyReleased(evt);
            }
        });
        getContentPane().add(tf_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 86, 165, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Data Transaksi Frame.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseClicked
        TransaksiFrame tf = new TransaksiFrame();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(tf);
        tf.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btn_transaksiMouseClicked

    private void btn_detailTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_detailTransaksiMouseClicked
        int i = tbl_dataTransaksi.getSelectedRow();
        TableModel model = tbl_dataTransaksi.getModel();

        String idTransaksi = model.getValueAt(i, 1).toString();
        new PopupDetailTransaksi(idTransaksi).setVisible(true);

    }//GEN-LAST:event_btn_detailTransaksiMouseClicked

    private void btn_pengambilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengambilanMouseClicked
        JDesktopPane desktopPane = getDesktopPane();
        PopupKonfirmasiPengambilan pkp = new PopupKonfirmasiPengambilan();
        pkp.setPane(desktopPane);
        pkp.setVisible(true);
    }//GEN-LAST:event_btn_pengambilanMouseClicked

    private void tf_filterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_filterKeyReleased
        String filter = tf_filter.getText(); // Mendapatkan teks filter dari komponen txt_filter
        filterTable(filter); // Memanggil method filterTable dengan argumen filter
    }//GEN-LAST:event_tf_filterKeyReleased

    public void cekwidth() {
        TableColumnModel columnModel = tbl_dataTransaksi.getColumnModel();
        System.out.println("field 1 : " + columnModel.getColumn(0).getWidth());
        System.out.println("field 2 : " + columnModel.getColumn(1).getWidth());
        System.out.println("field 3 : " + columnModel.getColumn(2).getWidth());
        System.out.println("field 4 : " + columnModel.getColumn(3).getWidth());
        System.out.println("field 5 : " + columnModel.getColumn(4).getWidth());
        System.out.println("field 6 : " + columnModel.getColumn(5).getWidth());
        System.out.println("field 7 : " + columnModel.getColumn(6).getWidth());
        System.out.println("field 8 : " + columnModel.getColumn(7).getWidth());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel btn_detailTransaksi;
    private javax.swing.JPanel btn_pengambilan;
    private utility.custom_panel.PanelRound btn_transaksi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_dataTransaksi;
    private javax.swing.JTextField tf_filter;
    // End of variables declaration//GEN-END:variables
}
