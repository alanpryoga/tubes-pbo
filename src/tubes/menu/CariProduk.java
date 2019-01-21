package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class CariProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan kata kunci: ");
        String keyword = scan.nextLine();
        
        try {
            String sql = "SELECT * FROM `produk` "
                    + "WHERE "
                    + "`kode_produk` LIKE '%" + keyword + "%' OR "
                    + "`nama_produk` LIKE '%" + keyword + "%' AND "
                    + "`aktif` = '1'";
            
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
