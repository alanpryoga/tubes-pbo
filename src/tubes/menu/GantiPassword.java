package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class GantiPassword {
    
    public void tampilMenu(Koneksi conn, User user) {
        boolean passwordLamaBenar = false;
        
        while ( ! passwordLamaBenar) {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Masukkan Password lama anda? ");
            String passwordLama = scan.nextLine();

            try {
                String sql = "SELECT * FROM `user` WHERE `id_user` = '" + user.getId() + "' AND `password` = MD5('" + passwordLama + "')";

                Statement statement = conn.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);  

                int rowCount = 0;
                
                while (resultSet.next()) {
                    passwordLamaBenar = true;
                    rowCount = rowCount + 1;
                }
                
                if (rowCount == 0) {
                    System.out.println("Salah memasukkan password lama anda!");
                }
                
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        
        boolean passwordBaruBenar = false;
        
        while ( ! passwordBaruBenar) {
            Scanner scanNew = new Scanner(System.in);
            
            System.out.print("Masukkan Password baru anda? ");
            String passwordBaru = scanNew.nextLine();

            System.out.print("Konfirmasi Password baru anda? ");
            String passwordBaruKonfirmasi = scanNew.nextLine();
            
            if (passwordBaru.equals(passwordBaruKonfirmasi)) {
                try {
                    String sql = "UPDATE `user` SET `password` = MD5(?)";

                    PreparedStatement prepStatement = conn.getConnection().prepareStatement(sql);
                    prepStatement.setString(1, passwordBaru);
                    prepStatement.execute();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
                
                passwordBaruBenar = true;
            } else {
                System.out.println("Konfirmasi password salah!");
            }
        }
    }
}
