package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TambahPelanggan {
    
    public void tampilMenu(Koneksi conn, User user) {
        boolean input = true;
        
        while (input) {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Nama Pelanggan    : ");
            String namaPelanggan = scan.nextLine();
            System.out.print("Alamat            : ");
            String alamat = scan.nextLine();
            System.out.print("No. Telp          : ");
            String noTelp = scan.nextLine();
            
            try {
                String sql = "INSERT INTO `pelanggan` (`nama_pelanggan`, `alamat`, `no_telp`) "
                        + "VALUES (?, ?, ?)";
                
                PreparedStatement prepStatement = conn.getConnection().prepareStatement(sql);
                prepStatement.setString(1, namaPelanggan);
                prepStatement.setString(2, alamat);
                prepStatement.setString(3, noTelp);
                prepStatement.execute();
                
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            
            System.out.print("Ingin memasukkan pelanggan lagi [y/t]? ");
            String confirm = scan.next();
            
            if (confirm.equals("t") || confirm.equals("T")) {
                input = false;
            }
            
            System.out.println("");
        }
    }
}
