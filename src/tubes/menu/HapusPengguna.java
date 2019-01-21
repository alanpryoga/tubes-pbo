package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class HapusPengguna {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan ID User yang akan dihapus? ");
        int idUser = scan.nextInt();
        
        try {
            String sql = "DELETE FROM `user` "
                    + "WHERE `id_user` = ?";
            
            PreparedStatement prepState = conn.getConnection().prepareStatement(sql);
            prepState.setInt(1, idUser);
            prepState.execute();
            
            System.out.print("Berhasil menghapus user dengan ID " + idUser + "!");
            new Scanner(System.in).nextLine();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
