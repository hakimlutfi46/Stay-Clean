/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import SubMenu.Popup.PopupGagalLogin;
import SubMenu.Popup.PopupSuksesLogin;
import koneksi.Connect;
import java.sql.*;
import javax.swing.JTextField;

/**
 *
 * @author perlengkapan
 */
public class Verification extends javax.swing.JFrame {

    int kodeVerifikasi;
    String idAkun;

    /**
     * Creates new form Verification
     */
    public Verification() {
        initComponents();

        iconEyeClose.setVisible(false);
        iconEyeClose1.setVisible(false);
        txt_passBaru.setEchoChar('\u2022');
        txt_konfirmPassBaru.setEchoChar('\u2022');
    }

    public void getVerif(int kodeVerif, String id) {
        this.kodeVerifikasi = kodeVerif;
        this.idAkun = id;
    }

    private void caseSensitive(JTextField textfield) {
        textfield.setText(textfield.getText().toLowerCase());
        textfield.setText(textfield.getText().replaceAll("\\s+", ""));
    }

    private void numberOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^0-9]", ""));
    }

    private void alphabetOnly(JTextField textfield) {
        textfield.setText(textfield.getText().replaceAll("[^a-z]", ""));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_otp = new javax.swing.JTextField();
        btn_reset = new javax.swing.JLabel();
        txt_passBaru = new javax.swing.JPasswordField();
        txt_konfirmPassBaru = new javax.swing.JPasswordField();
        iconEyeClose = new javax.swing.JLabel();
        iconEyeOpen = new javax.swing.JLabel();
        iconEyeOpen1 = new javax.swing.JLabel();
        iconEyeClose1 = new javax.swing.JLabel();
        btn_close = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_otp.setBackground(new java.awt.Color(217, 217, 217));
        txt_otp.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txt_otp.setForeground(new java.awt.Color(0, 0, 0));
        txt_otp.setBorder(null);
        txt_otp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_otpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_otpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_otp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 336, 370, 38));

        btn_reset.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetMouseClicked(evt);
            }
        });
        getContentPane().add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, 430, 40));

        txt_passBaru.setBackground(new java.awt.Color(217, 217, 217));
        txt_passBaru.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txt_passBaru.setBorder(null);
        txt_passBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passBaruKeyReleased(evt);
            }
        });
        getContentPane().add(txt_passBaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 419, 340, 40));

        txt_konfirmPassBaru.setBackground(new java.awt.Color(217, 217, 217));
        txt_konfirmPassBaru.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txt_konfirmPassBaru.setBorder(null);
        txt_konfirmPassBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_konfirmPassBaruKeyReleased(evt);
            }
        });
        getContentPane().add(txt_konfirmPassBaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 504, 340, 40));

        iconEyeClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icon/eye close.png"))); // NOI18N
        iconEyeClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconEyeClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconEyeCloseMouseClicked(evt);
            }
        });
        getContentPane().add(iconEyeClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 418, 30, 40));

        iconEyeOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icon/eye open.png"))); // NOI18N
        iconEyeOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconEyeOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconEyeOpenMouseClicked(evt);
            }
        });
        getContentPane().add(iconEyeOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 418, 30, 40));

        iconEyeOpen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icon/eye open.png"))); // NOI18N
        iconEyeOpen1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconEyeOpen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconEyeOpen1MouseClicked(evt);
            }
        });
        getContentPane().add(iconEyeOpen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 503, 30, 40));

        iconEyeClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icon/eye close.png"))); // NOI18N
        iconEyeClose1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconEyeClose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconEyeClose1MouseClicked(evt);
            }
        });
        getContentPane().add(iconEyeClose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 503, 30, 40));

        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/icon/close login.png"))); // NOI18N
        btn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
        });
        getContentPane().add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1235, 16, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img/layout/Reset Password Page.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked

        if (txt_otp.getText().equals("")) {
            new PopupGagalLogin("Kode OTP tidak boleh kosong").setVisible(true);
        } else if (txt_otp.getText().equals(String.valueOf(this.kodeVerifikasi))) {
            if (txt_passBaru.getText().equals("")) {
                new PopupGagalLogin("Kata sandi tidak boleh kosong").setVisible(true);
            } else if (txt_passBaru.getText().length() < 6) {
                new PopupGagalLogin("Kata sandi baru minimal 6 karakter").setVisible(true);
            } else if (txt_passBaru.getText().equals(txt_konfirmPassBaru.getText())) {
                try {
                    Connection conn = Connect.GetConnection();
                    String sql = "UPDATE akun SET PASSWORD = '" + txt_passBaru.getText() + "' WHERE id_akun = '" + this.idAkun + "'";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                    this.dispose();
                    new LoginPage().setVisible(true);
                    new PopupSuksesLogin("Password anda telah diperbarui, silahkan melakukan proses login", "").setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                new PopupGagalLogin("Silahkan cek kembali password yang anda masukkan").setVisible(true);
                txt_passBaru.requestFocus();
            }
        } else {
            new PopupGagalLogin("Silahkan cek kembali kode verifikasi yang telah dikirimkan melalui email").setVisible(true);
            txt_otp.requestFocus();
        }
    }//GEN-LAST:event_btn_resetMouseClicked

    private void iconEyeCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconEyeCloseMouseClicked
        iconEyeOpen.setVisible(true);
        iconEyeClose.setVisible(false);
        txt_passBaru.setEchoChar('\u2022');
    }//GEN-LAST:event_iconEyeCloseMouseClicked

    private void iconEyeOpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconEyeOpenMouseClicked
        iconEyeOpen.setVisible(false);
        iconEyeClose.setVisible(true);
        txt_passBaru.setEchoChar((char) 0);
    }//GEN-LAST:event_iconEyeOpenMouseClicked

    private void iconEyeOpen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconEyeOpen1MouseClicked
        iconEyeOpen1.setVisible(false);
        iconEyeClose1.setVisible(true);
        txt_konfirmPassBaru.setEchoChar((char) 0);
    }//GEN-LAST:event_iconEyeOpen1MouseClicked

    private void iconEyeClose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconEyeClose1MouseClicked
        iconEyeOpen1.setVisible(true);
        iconEyeClose1.setVisible(false);
        txt_konfirmPassBaru.setEchoChar('\u2022');
    }//GEN-LAST:event_iconEyeClose1MouseClicked

    private void txt_otpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otpKeyReleased
        numberOnly(txt_otp);
    }//GEN-LAST:event_txt_otpKeyReleased

    private void txt_otpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otpKeyTyped
        if (txt_otp.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_otpKeyTyped

    private void txt_passBaruKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passBaruKeyReleased
        txt_passBaru.setText(txt_passBaru.getText().replaceAll("\\s+", ""));
    }//GEN-LAST:event_txt_passBaruKeyReleased

    private void txt_konfirmPassBaruKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_konfirmPassBaruKeyReleased
        txt_konfirmPassBaru.setText(txt_konfirmPassBaru.getText().replaceAll("\\s+", ""));
    }//GEN-LAST:event_txt_konfirmPassBaruKeyReleased

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_closeMouseClicked

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
            java.util.logging.Logger.getLogger(Verification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Verification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Verification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Verification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Verification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel btn_reset;
    private javax.swing.JLabel iconEyeClose;
    private javax.swing.JLabel iconEyeClose1;
    private javax.swing.JLabel iconEyeOpen;
    private javax.swing.JLabel iconEyeOpen1;
    private javax.swing.JPasswordField txt_konfirmPassBaru;
    private javax.swing.JTextField txt_otp;
    private javax.swing.JPasswordField txt_passBaru;
    // End of variables declaration//GEN-END:variables

}
