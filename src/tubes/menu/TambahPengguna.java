package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TambahPengguna {
    
    public void tampilMenu(Koneksi conn, User user) {
        boolean input = true;
        
        while (input) {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Nama User                     : ");
            String nama_user = scan.nextLine();
            System.out.print("Username                      : ");
            String username = scan.nextLine();
            System.out.print("Password                      : ");
            String password = scan.nextLine();
            System.out.print("Jabatan (Administrator/Kasir) : ");
            String jabatan = scan.nextLine();
            
            try {
                String sql = "INSERT INTO `user` (`nama_user`, `username`, `password`, `jabatan`) "
                        + "VALUES (?, ?, MD5(?), ?)";
                
                PreparedStatement prepStatement = conn.getConnection().prepareStatement(sql);
                prepStatement.setString(1, nama_user);
                prepStatement.setString(2, username);
                prepStatement.setString(3, password);
                prepStatement.setString(4, jabatan);
                prepStatement.execute();
                
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            
            System.out.print("Ingin memasukkan user lagi [y/t]? ");
            String confirm = scan.next();
            
            if (confirm.equals("t") || confirm.equals("T")) {
                input = false;
            }
            
            System.out.println("");
        }
    }
}
