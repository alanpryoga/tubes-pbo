package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class EditPelanggan {
    
    public void tampilMenu(Koneksi conn, User user) {
        System.out.println("");
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan ID pelanggan yang akan diedit? ");
        int idPelanggan = scan.nextInt();
        
        System.out.println("----------------------------------------");
        System.out.print("Masukkan nama pelanggan baru           : ");
        String namaPelanggan = scan.next();
        System.out.print("Masukkan alamat pelanggan baru         : ");
        String alamat = scan.next();
        System.out.print("Masukkan nomor telepon pelanggan baru  : ");
        String noTelp = scan.next();
        
        try {
            String sql = "UPDATE `pelanggan` "
                    + "SET `nama_pelanggan` = ?, `alamat` = ?, `no_telp` = ? "
                    + "WHERE `id_pelanggan` = '" + idPelanggan + "'";
            
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, namaPelanggan);
            ps.setString(2, alamat);
            ps.setString(3, noTelp);
            ps.execute();
            
            System.out.println("Pelanggan dengan ID " + idPelanggan + " berhasil di perbarui!");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
