package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TambahProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        boolean exit = false;
        
        while ( ! exit) {
            Scanner scan = new Scanner(System.in);
        
            String kode_produk, nama_produk;
            int harga, stok;
            
            System.out.print("Masukkan kode produk    : ");
            kode_produk = scan.nextLine();
            System.out.print("Masukkan nama produk    : ");
            nama_produk = scan.nextLine();
            System.out.print("Masukkan harga produk   : Rp ");
            harga = scan.nextInt();
            System.out.print("Masukkan stok produk    : ");
            stok = scan.nextInt();
            
            try {
                String sql = "INSERT INTO `produk` (`kode_produk`, `nama_produk`, `harga`, `stok`, `aktif`) VALUES (?, ?, ?, ?, ?)";
            
                PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, kode_produk);
                preparedStatement.setString(2, nama_produk);
                preparedStatement.setInt(3, harga);
                preparedStatement.setInt(4, stok);
                preparedStatement.setInt(5, 1);
                
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            
            Scanner scanExit = new Scanner(System.in);
            System.out.print("Ingin memasukkan data lagi [y/n]? ");
            String confirm = scanExit.nextLine();
            
            if (confirm.equals("n") || confirm.equals("N")) {
                exit = true;
            } else {
                System.out.println("");
            }
        }
    }
}
