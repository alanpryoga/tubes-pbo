package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class CariPengguna {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan kata pencarian? ");
        String cariPengguna = scan.nextLine();
        
        try {
            String sql = "SELECT * FROM `user` "
                    + "WHERE `id_user` LIKE '%" + cariPengguna+ "%' OR "
                    + "`nama_user` LIKE '%" + cariPengguna + "%' OR "
                    + "`username` LIKE '%" + cariPengguna + "%'";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            String leftAlignFormat = "| %-11s | %-23s | %-14s | %-15s |%n";
            
            System.out.println("TAMPIL DATA PENGGUNA");
            System.out.format("+------------------------- ------------------------------------------------+%n");
            System.out.format("|   ID User   |        Nama User        |    Username    |     Jabatan     |%n");
            System.out.format("+--------------------------------------------------------------------------+%n");
            
            while(resultSet.next()){
                System.out.format(leftAlignFormat,
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama_user"),
                        resultSet.getString("username"),
                        resultSet.getString("jabatan"));
            }
            
            System.out.format("+--------------------------------------------------------------------------+%n");
            
            System.out.print("Tekan enter untuk kembali...");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
