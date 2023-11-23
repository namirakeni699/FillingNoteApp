/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

/**
 *
 * @author COMPUTER
 */
import icon.tableCustom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
public class table_registrasi extends javax.swing.JFrame {

    /**
     * Creates new form table_registrasi
     */
    DefaultTableModel table = new DefaultTableModel();
    
    public table_registrasi() {
        initComponents(); icon.tableCustom.apply(jScrollPane1, tableCustom.TableType.DEFAULT);
         koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_registrasi.setModel(table);
        table.addColumn("Id Registrasi");
        table.addColumn("No Rekam Medis");
        table.addColumn("Nama Pasien");
        table.addColumn("NIP Dokter");
        table.addColumn("Nama Dokter");
        table.addColumn("Poli");
        table.addColumn("Tanggal Masuk");

        tampilData(); 
    }
      public void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_registrasi.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
String query = "SELECT a.id_registrasi,b.id_pasien,b.nama_pasien,c.nip,c.nama_dokter,a.poli,a.tanggal_masuk from registrasi_pasien a JOIN data_pasien b on a.id_pasien = b.id_pasien JOIN data_dokter c on a.nip = c.nip";


        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String id_regist = rslt.getString("id_registrasi");
                    String idpasien = rslt.getString("id_pasien");
                    String namapasien = rslt.getString("nama_pasien");
                    String nip = rslt.getString("nip");
                    String namadokter = rslt.getString("nama_dokter");                   
                    String poli = rslt.getString("poli");
                    String tgl = rslt.getString("tanggal_masuk");
                   

                //masukan semua data kedalam array
                String[] data = {id_regist,idpasien,namapasien,nip,namadokter,poli,tgl};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_registrasi.setModel(table);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        finally{
            
        }
       
    }
       private void cari(){
        //untuk mengahapus baris setelah input
        int row = table_registrasi.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        String carii = txt_search.getText();
        
        String query = "SELECT a.id_registrasi,b.id_pasien,b.nama_pasien,c.nip,c.nama_dokter,a.poli,a.tanggal_masuk from registrasi_pasien a JOIN data_pasien b on a.id_pasien = b.id_pasien JOIN data_dokter c on a.nip = c.nip where b.nama_pasien LIKE '%"+carii+"%' or c.nama_dokter LIKE '%"+carii+"%' ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                   String id_regist = rslt.getString("id_registrasi");
                    String idpasien = rslt.getString("id_pasien");
                    String namapasien = rslt.getString("nama_pasien");
                    String nip = rslt.getString("nip");
                    String namadokter = rslt.getString("nama_dokter");                   
                    String poli = rslt.getString("poli");
                    String tgl = rslt.getString("tanggal_masuk");
                   

                //masukan semua data kedalam array
                String[] data = {id_regist,idpasien,namapasien,nip,namadokter,poli,tgl};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_registrasi.setModel(table);
            
        }catch(SQLException e){
         
        }
       
    }
       private void deleteData() {
    int selectedRow = table_registrasi.getSelectedRow();

    if (selectedRow >= 0) {
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data terpilih?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String id_pasien = table_registrasi.getValueAt(selectedRow, 0).toString(); // Mengambil ID pasien dari tabel
            // Query untuk menghapus data dari database berdasarkan ID pasien yang dipilih
            String query = "DELETE FROM registrasi_pasien WHERE id_registrasi = ?";

            try {
                Connection connection = koneksi.getKoneksi();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id_pasien); // Mengganti parameter pertama dengan ID pasien yang dipilih
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");

                // Setelah menghapus data, Anda bisa me-refresh tabel dengan mengambil data terbaru dari database
               tampilData();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
   
}
       private void setDataFromSelectedRowToForm() {
    int selectedRow = table_registrasi.getSelectedRow();

    if (selectedRow >= 0) {
        String id_pasien = table_registrasi.getValueAt(selectedRow, 0).toString(); // Mengambil ID pasien dari tabel
        // Query untuk mengambil data dari database berdasarkan ID pasien yang dipilih
        String query = "SELECT a.id_registrasi,b.id_pasien,b.nama_pasien,c.nip,c.nama_dokter,a.poli,a.tanggal_masuk,a.keluhan from registrasi_pasien a JOIN data_pasien b on a.id_pasien = b.id_pasien JOIN data_dokter c on a.nip = c.nip WHERE a.id_registrasi = ?";

        try {
            Connection connection = koneksi.getKoneksi();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id_pasien); // Mengganti parameter pertama dengan ID pasien yang dipilih
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                data_registrasipasien formPasien = new data_registrasipasien();
                
                formPasien.txt_idpasien.setText(resultSet.getString("id_pasien"));
                formPasien.txt_namapasien.setText(resultSet.getString("nama_pasien"));
                formPasien.txt_nip.setText(resultSet.getString("nip"));
                formPasien.txt_namadokter.setText(resultSet.getString("nama_dokter"));
                formPasien.txt_tglmasuk.setText(resultSet.getString("tanggal_masuk"));
                formPasien.txt_keluhan.setText(resultSet.getString("keluhan"));
            
                
                formPasien.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
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

        btn_edit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_registrasi = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_edit.setText(". ");
        btn_edit.setBorderPainted(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 140, 50));

        table_registrasi.setModel(new javax.swing.table.DefaultTableModel(
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
        table_registrasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_registrasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_registrasi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 1160, 480));

        btn_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_tambah.setText(".\n");
        btn_tambah.setBorderPainted(false);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 140, 50));

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_hapus.setText(".\n");
        btn_hapus.setBorderPainted(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, 130, 50));

        btn_print.setBackground(new java.awt.Color(255, 255, 255));
        btn_print.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_print.setText(".\n");
        btn_print.setBorderPainted(false);
        btn_print.setContentAreaFilled(false);
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 130, 50));

        txt_search.setBorder(null);
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 390, 40));

        jButton1.setText(".");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tb_registrasi.png"))); // NOI18N
        jLabel1.setText("\n");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
       setDataFromSelectedRowToForm();
       data_registrasipasien.btnTambah.setEnabled(false);
    }//GEN-LAST:event_btn_editActionPerformed

    private void table_registrasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_registrasiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_registrasiMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        master.data_registrasipasien h = new master.data_registrasipasien();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        master.menu_utama h = new master.menu_utama();
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
            java.util.logging.Logger.getLogger(table_registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(table_registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(table_registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(table_registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new table_registrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_registrasi;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
