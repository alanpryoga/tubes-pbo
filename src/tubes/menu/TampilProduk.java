package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TampilProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        try {
            String sql = "SELECT * FROM `produk` "
                    + "WHERE `aktif` = 1";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            System.out.format("+-------------------------------------------------------+%n");
            System.out.format("| Kode Produk |      Nama Produk     |   Harga   | Stok |%n");
            System.out.format("+=======================================================+%n");
            
            while (resultSet.next()) {
                String kode_produk = resultSet.getString("kode_produk");
                String nama_produk = resultSet.getString("nama_produk");
                String harga = resultSet.getString("harga");
                String stok = resultSet.getString("stok");
                
                System.out.format("| %-11s | %-20s | %-9s | %-4s |%n", kode_produk, nama_produk, harga, stok);
            }
            
            System.out.format("+=======================================================+%n");
            
            
            System.out.print("Tekan Enter untuk kembali...");
            new Scanner(System.in).nextLine();
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
