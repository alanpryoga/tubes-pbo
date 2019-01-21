package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class HapusPelanggan {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan ID Pelanggan yang akan dihapus? ");
        int idPelanggan = scan.nextInt();
        
        try {
            String sql = "DELETE FROM `pelanggan` "
                    + "WHERE `id_pelanggan` = ?";
            
            PreparedStatement prepState = conn.getConnection().prepareStatement(sql);
            prepState.setInt(1, idPelanggan);
            prepState.execute();
            
            System.out.print("Berhasil menghapus pelanggan dengan ID " + idPelanggan + "!");
            new Scanner(System.in).nextLine();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
