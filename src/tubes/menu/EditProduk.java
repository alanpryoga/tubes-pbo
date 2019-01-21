package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class EditProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan kode produk: ");
        String kode_produk = scan.nextLine();
        
        try {
            String sql = "UPDATE `produk` SET `nama_produk` = ?, `harga` = ?, `stok` = ? WHERE `kode_produk` = '" + kode_produk + "'";
            
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            
            System.out.print("Masukkan nama produk baru: ");
            preparedStatement.setString(1, scan.nextLine());
            
            System.out.print("Masukkan harga produk baru: ");
            preparedStatement.setInt(2, scan.nextInt());
            
            System.out.print("Masukkan stok produk baru: ");
            preparedStatement.setInt(3, scan.nextInt());
            
            preparedStatement.execute();
            
            System.out.print("Berhasil mengedit produk!");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
