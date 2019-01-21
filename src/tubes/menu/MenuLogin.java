package tubes.menu;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuLogin {
    
    public void tampilMenu(Koneksi conn) throws DocumentException, FileNotFoundException, ParseException {
        String username, password;
        boolean isLogin = false;
        Scanner scan = new Scanner(System.in);
        User user = new User();
        
        while ( ! isLogin) {
            System.out.println("----------------------------------------");
            System.out.println("            Login Pengguna              ");
            System.out.println("========================================");
            System.out.print("Masukkan Username: ");
            username = scan.nextLine();
            System.out.print("Masukkan Password: ");
            password = scan.nextLine();
            
            if (username.length() > 0 && password.length() > 0) {
                try {
                    String sql = "SELECT * FROM `user` WHERE `username` = '" + username + "' AND `password` = MD5('" + password + "') LIMIT 1";
                    
                    Statement statement = conn.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    
                    int countRecord = 0;
                    while (resultSet.next()) {
                        countRecord = countRecord + 1;
                        
                        if (countRecord > 0) {
                            isLogin = true;
                            
                            user.setId(resultSet.getInt("id_user"));
                            user.setNamaUser(resultSet.getString("nama_user"));
                            user.setUsername(resultSet.getString("username"));
                            user.setPassword(resultSet.getString("password"));
                            user.setJabatan(resultSet.getString("jabatan"));
                        }
                    }
                    
                    if (countRecord == 0) {
                        System.out.println("Username atau Password salah!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            } else {
                System.out.println("Username atau Password tidak boleh kosong!");
            }
            
            System.out.println("========================================");
            System.out.println("");
        }
        
        MenuUtama menuUtama = new MenuUtama();
        menuUtama.tampilMenu(conn, user);
    }
}
