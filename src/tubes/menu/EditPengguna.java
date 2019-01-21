package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class EditPengguna {
    
    public void tampilMenu(Koneksi conn, User user) {
        System.out.println("");
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Masukkan ID yang akan diedit? ");
        int idUser = scan.nextInt();
        
        System.out.println("----------------------------------------");
        System.out.print("Masukkan nama user yang baru  : ");
        String namaUser = scan.next();
        System.out.print("Masukkan username yang baru   : ");
        String username = scan.next();
        System.out.print("Masukkan jabatan yang baru    : ");
        String jabatan = scan.next();
        
        try {
            String sql = "UPDATE `user` "
                    + "SET `nama_user` = ?, `username` = ?, `jabatan` = ? "
                    + "WHERE `id_user` = '" + idUser + "'";
            
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, namaUser);
            ps.setString(2, username);
            ps.setString(3, jabatan);
            ps.execute();
            
            System.out.println("Pengguna dengan ID " + idUser + " berhasil di perbarui!");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
