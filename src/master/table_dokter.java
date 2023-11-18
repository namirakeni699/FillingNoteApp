/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import icon.tableCustom;
import java.sql.Connection;
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

/**
 *
 * @author COMPUTER
 */

public class table_dokter extends javax.swing.JFrame {
DefaultTableModel table = new DefaultTableModel();
    /**
     * Creates new form table_dokter
     */
    public table_dokter() {
        initComponents();
            icon.tableCustom.apply(jScrollPane1, tableCustom.TableType.DEFAULT);
         koneksi conn = new koneksi();
        koneksi.getKoneksi();
        
        table_dokter.setModel(table);
        table.addColumn("NIP");
        table.addColumn("Nama Dokter");
        table.addColumn("Spesialis");
        table.addColumn("Nomor Telepon");
        table.addColumn("Alamat");
        table.addColumn("Jadwal");

        tampilData(); 
    }
      public void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_dokter.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
String query = "Select * from data_dokter";


        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String nip = rslt.getString("nip");
                    String nama = rslt.getString("nama_dokter");
                    String spesialis = rslt.getString("spesialis");
                    String notelp = rslt.getString("no_telpon");
                    String alamat = rslt.getString("alamat_dokter");                   
                    String jadwal = rslt.getString("jadwal");
                   

                //masukan semua data kedalam array
                String[] data = {nip,nama,spesialis,notelp,alamat,jadwal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_dokter.setModel(table);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        finally{
            
        }
       
    }
      private void cari(){
        //untuk mengahapus baris setelah input
        int row = table_dokter.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        String carii = txt_search.getText();
        
        String query = "SELECT * FROM `data_dokter` where nip LIKE '%"+carii+"%' or nama_dokter LIKE '%"+carii+"%' ";
        
        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String nip = rslt.getString("nip");
                    String nama = rslt.getString("nama_dokter");
                    String spesialis = rslt.getString("spesialis");
                    String notelp = rslt.getString("no_telpon");
                    String alamat = rslt.getString("alamat_dokter");                   
                    String jadwal = rslt.getString("jadwal");
                   

                //masukan semua data kedalam array
                String[] data = {nip,nama,spesialis,notelp,alamat,jadwal};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_dokter.setModel(table);
            
        }catch(SQLException e){
         
        }
       
    }
      private void editData(){
           int selectedRow = table_dokter.getSelectedRow();

                // Pastikan ada baris yang dipilih
if (selectedRow >= 0) {
    // Ambil data dari baris yang dipilih
    String nip = table_dokter.getValueAt(selectedRow, 0).toString();
    String namaDokter = table_dokter.getValueAt(selectedRow, 1).toString();
    String spesialis = table_dokter.getValueAt(selectedRow, 2).toString();
    String no_hp = table_dokter.getValueAt(selectedRow, 3).toString();
    String alamat = table_dokter.getValueAt(selectedRow, 4).toString();
  

    data_dokter editForm = new data_dokter();

    // Setel nilai-nilai dalam JTextField di data_dokter
    editForm.nip.setText(nip);
    editForm.nama_dokter.setText(namaDokter);
    // Setel nilai pada JComboBox "spesialis"
    editForm.spesialis.setSelectedItem(spesialis);

    editForm.nohp.setText(no_hp);
    editForm.alamat.setText(alamat);

    // Setel nilai pada JDateTimePicker "jadwal"
    
    editForm.setVisible(true);
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
        btn_tambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dokter = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
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

        table_dokter.setModel(new javax.swing.table.DefaultTableModel(
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
        table_dokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_dokterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_dokter);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 1160, 480));

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_hapus.setText(".\n");
        btn_hapus.setBorderPainted(false);
        btn_hapus.setContentAreaFilled(false);
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 130, 50));

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
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 180, 130, 50));

        jButton1.setText(".");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 60));

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

        btn_cari.setText(".");
        btn_cari.setBorderPainted(false);
        btn_cari.setContentAreaFilled(false);
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 60, 50));

        btn_cancel.setText(".");
        btn_cancel.setBorderPainted(false);
        btn_cancel.setContentAreaFilled(false);
        getContentPane().add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 60, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Data Dokter1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_dokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_dokterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_dokterMouseClicked

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
  editData();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
          master.data_dokter h = new master.data_dokter();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
try {
    // Lokasi file .jasper
    String reportPath = "/Report/dataDokterReport.jasper";

    // Mengambil koneksi ke database (pastikan Anda memiliki kelas 'koneksi' yang sesuai)
    Connection conn = koneksi.getKoneksi();

    // Parameter untuk laporan, jika diperlukan
    Map<String, Object> parameters = new HashMap<>();

    // Mengisi laporan dengan parameter (gunakan null jika tidak ada parameter)
    JasperPrint print = JasperFillManager.fillReport(
        getClass().getResourceAsStream(reportPath), parameters, conn);

    // Menampilkan laporan menggunakan JasperViewer
    JasperViewer viewer = new JasperViewer(print, false);
    viewer.setVisible(true);
} catch (JRException e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_btn_printActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        master.menu_utama h = new master.menu_utama();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txt_searchKeyPressed

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
            java.util.logging.Logger.getLogger(table_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(table_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(table_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(table_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new table_dokter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_dokter;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
