package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class CariPelanggan {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan kata pencarian? ");
        String cariPelanggan = scan.nextLine();
        
        try {
            String sql = "SELECT * FROM `pelanggan` "
                    + "WHERE `id_pelanggan` LIKE '%" + cariPelanggan + "%' OR "
                    + "`nama_pelanggan` LIKE '%" + cariPelanggan + "%' OR "
                    + "`no_telp` LIKE '%" + cariPelanggan + "%'";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            String leftAlignFormat = "| %-12s | %-15s | %-24s | %-14s |%n" ;

            System.out.println("TAMPIL PELANGGAN");
            System.out.format("+----------------------------------------------------------------------------+%n");
            System.out.format("| Id Pelanggan | Nama Pelanggan  |          Alamat          |     No Telp    |%n");
            System.out.format("+----------------------------------------------------------------------------+%n");
            
            while(resultSet.next()){
                System.out.format(leftAlignFormat,
                        resultSet.getInt("id_pelanggan"),
                        resultSet.getString("nama_pelanggan"),
                        resultSet.getString("alamat"),
                        resultSet.getString("no_telp"));
            }
            
            System.out.format("+----------------------------------------------------------------------------+%n");
            System.out.print("Tekan enter untuk kembali....");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
