/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import static master.data_dokter.alamat;
import static master.data_dokter.jadwal;
import static master.data_dokter.nama_dokter;
import static master.data_dokter.nip;
import static master.data_dokter.spesialis;

/**
 *
 * @author COMPUTER
 */
public class data_registrasipasien extends javax.swing.JFrame {

    /**
     * Creates new form data_registrasipasien
     */
    public data_registrasipasien() {
        initComponents();
           Locale indonesiaLocale = new Locale("in", "ID"); // Buat objek Locale untuk bahasa Indonesia
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocation(0, 0);
        tanggal();
    }
        public void tanggal(){
        Date Tanggal = new Date();
        SimpleDateFormat TanggalFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        txt_tglmasuk.setText(TanggalFormat.format(Tanggal));
    }
    private void tambahData() {
    String NIP = txt_nip.getText();
    String idpasien = txt_idpasien.getText();
    String Poli = (String) poli.getSelectedItem();
    String Tanggal = txt_tglmasuk.getText();
    String keluhan = txt_keluhan.getText();
    String IdUSer = "1";
    Locale locale = new Locale("id", "ID");
          Date currentDate = new Date();

        // Membuat objek SimpleDateFormat dengan format yang diinginkan
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Memformat tanggal ke dalam format yang diinginkan
        String formattedDate = dateFormat.format(currentDate);

    
        txt_tglmasuk.setText(formattedDate);


    // Memeriksa apakah ada input yang kosong
    if (NIP.isEmpty() || idpasien.isEmpty() || Poli.isEmpty() || keluhan.isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    // Panggil koneksi
    Connection connect = koneksi.getKoneksi();
    
    // Query untuk memasukkan data
    String query = "INSERT INTO `registrasi_pasien` ( `id_pasien`, `nip`, `poli`, `tanggal_masuk`, `keluhan`, `id_user`) "
                 + "VALUES ('"+idpasien+"', '"+NIP+"', '"+Poli+"','"+Tanggal+"', '"+keluhan+"','"+IdUSer+"')";

    try {
        // Menyiapkan statement untuk dieksekusi
        PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
        ps.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        

    } catch(SQLException | HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        System.out.print(e);
    } finally {
    
    }
}
   private void editData() {
    String NIP = txt_nip.getText();
    String idpasien = txt_idpasien.getText();
    String Poli = (String) poli.getSelectedItem();
    ;
    String keluhan = txt_keluhan.getText();

    // Memeriksa apakah ada input yang kosong
    if (NIP.isEmpty() || idpasien.isEmpty() || Poli.isEmpty() || keluhan.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    // Panggil koneksi
    Connection connect = koneksi.getKoneksi();

    // Query untuk memperbarui data
    String query = "UPDATE `registrasi_pasien` SET `nip` = ?, `poli` = ?, `keluhan` = ? WHERE `id_pasien` = ?";

    try {
        // Menyiapkan statement untuk dieksekusi
        PreparedStatement ps = connect.prepareStatement(query);
        ps.setString(1, NIP);
        ps.setString(2, Poli);
        ps.setString(3, keluhan);
        ps.setString(4, idpasien);

        // Menjalankan query update
        int rowsUpdated = ps.executeUpdate();

        // Memeriksa apakah data berhasil diperbarui
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
        } else {
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui. Data dengan NIP " + NIP + " tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan: " + e.getMessage());
    } finally {
       clearbrg();
    }
}

    public void clearbrg(){
        txt_idpasien.setText(null);
        txt_namapasien.setText(null);
        txt_nip.setText(null);
        txt_namadokter.setText(null);
         txt_keluhan.setText(null);
        txt_namadokter.setText(null);
      
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pil_pasien = new javax.swing.JButton();
        txt_namapasien = new javax.swing.JTextField();
        txt_idpasien = new javax.swing.JTextField();
        txt_namadokter = new javax.swing.JTextField();
        txt_nip = new javax.swing.JTextField();
        poli = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keluhan = new javax.swing.JTextArea();
        txt_tglmasuk = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        pil_dokter = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pil_pasien.setBackground(new java.awt.Color(204, 204, 204));
        pil_pasien.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        pil_pasien.setBorderPainted(false);
        pil_pasien.setContentAreaFilled(false);
        pil_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pil_pasienActionPerformed(evt);
            }
        });
        getContentPane().add(pil_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 110, 50));

        txt_namapasien.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_namapasien.setBorder(null);
        getContentPane().add(txt_namapasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 390, 50));

        txt_idpasien.setEditable(false);
        txt_idpasien.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_idpasien.setBorder(null);
        getContentPane().add(txt_idpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 290, 50));

        txt_namadokter.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_namadokter.setBorder(null);
        getContentPane().add(txt_namadokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 380, 50));

        txt_nip.setEditable(false);
        txt_nip.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_nip.setBorder(null);
        getContentPane().add(txt_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 230, 290, 50));

        poli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        poli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Umum", "Penyakit Dalam", "Bedah Umum", "Anak", "Kandungan dan Kebidanan", "THT", "Mata ", "Syaraf", " " }));
        getContentPane().add(poli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 420, 50));

        txt_keluhan.setColumns(20);
        txt_keluhan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_keluhan.setRows(5);
        jScrollPane1.setViewportView(txt_keluhan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 420, 130));

        txt_tglmasuk.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_tglmasuk.setBorder(null);
        getContentPane().add(txt_tglmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 390, 50));

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText(".\n");
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 660, 180, 50));

        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setText(".");
        btnTambah.setBorder(null);
        btnTambah.setBorderPainted(false);
        btnTambah.setContentAreaFilled(false);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 660, 170, 50));

        pil_dokter.setBackground(new java.awt.Color(204, 204, 204));
        pil_dokter.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        pil_dokter.setBorderPainted(false);
        pil_dokter.setContentAreaFilled(false);
        pil_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pil_dokterActionPerformed(evt);
            }
        });
        getContentPane().add(pil_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 230, 110, 50));

        jButton1.setText(".");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/data_registrasi.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pil_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pil_pasienActionPerformed
        // TODO add your handling code here:
     master.pilihpasien h = new pilihpasien();
        h.setVisible(true);
    }//GEN-LAST:event_pil_pasienActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        tambahData();
       
    }//GEN-LAST:event_btnTambahActionPerformed

    private void pil_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pil_dokterActionPerformed
        // TODO add your handling code here:
        master.pilihdokter h = new pilihdokter();
        h.setVisible(true);
    }//GEN-LAST:event_pil_dokterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        master.table_registrasi h = new master.table_registrasi();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(data_registrasipasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_registrasipasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_registrasipasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_registrasipasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_registrasipasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEdit;
    public static javax.swing.JButton btnTambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pil_dokter;
    private javax.swing.JButton pil_pasien;
    private javax.swing.JComboBox<String> poli;
    public static javax.swing.JTextField txt_idpasien;
    public static javax.swing.JTextArea txt_keluhan;
    public static javax.swing.JTextField txt_namadokter;
    public static javax.swing.JTextField txt_namapasien;
    public static javax.swing.JTextField txt_nip;
    public static javax.swing.JTextField txt_tglmasuk;
    // End of variables declaration//GEN-END:variables
}
