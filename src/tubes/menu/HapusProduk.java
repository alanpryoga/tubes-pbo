package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class HapusProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan kode produk: ");
        String kode_produk = scan.nextLine();
        
        try {
            String sql = "UPDATE `produk` SET `aktif` = ? WHERE `kode_produk` = ?";
            
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, kode_produk);
            preparedStatement.execute();
            
            System.out.print("Produk berhasil dihapus...");
            new Scanner(System.in).nextLine();
            System.out.println("");
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
