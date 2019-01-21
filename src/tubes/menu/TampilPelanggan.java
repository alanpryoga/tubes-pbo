package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TampilPelanggan {
 
    public void tampilMenu(Koneksi conn, User user) {
        System.out.println("");
        
        try {
            String sql = "SELECT * FROM `pelanggan`";
            
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            String leftAlignFormat = "| %-12s | %-15s | %-24s | %-14s |%n" ;

            System.out.println("TAMPIL PELANGGAN");
            System.out.format("+----------------------------------------------------------------------------+%n");
            System.out.format("| Id Pelanggan | Nama Pelanggan  |          Alamat          |     No Telp    |%n");
            System.out.format("+----------------------------------------------------------------------------+%n");
            
            while(rs.next()){
                System.out.format(leftAlignFormat,
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_telp"));
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
