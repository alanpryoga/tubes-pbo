package tubes.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class TampilPenjualan {
    
    public void tampilMenu(Koneksi conn, User user) throws ParseException {
        try {
            String sql = "SELECT * FROM `penjualan` "
                    + "JOIN `user` ON `user`.`id_user` = `penjualan`.`id_user` "
                    + "JOIN `pelanggan` ON `pelanggan`.`id_pelanggan` = `penjualan`.`id_pelanggan` "
                    + "ORDER BY `tanggal` DESC";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            String leftAlignFormat = "| %-12s | %-20s | %-20s | %-10s | %-16s |%n";
            
            System.out.println("TAMPIL DATA PENJUALAN");
            System.out.format("+--------------------------------------------------------------------------------------------+%n");
            System.out.format("| No Penjualan |         User         |       Pelanggan      |   Tanggal  | Total Pembayaran |%n");
            System.out.format("+--------------------------------------------------------------------------------------------+%n");
            
            while (resultSet.next()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("tanggal"));
                
                System.out.format(leftAlignFormat, 
                        resultSet.getString("no_penjualan"), 
                        resultSet.getString("nama_user"),
                        resultSet.getString("nama_pelanggan"),
                        new SimpleDateFormat("dd-MM-yyyy").format(date),
                        "Rp " + resultSet.getInt("total_pembayaran"));
            }
            
            System.out.format("+--------------------------------------------------------------------------------------------+%n");
            System.out.print("Tekan enter untuk kembali...");
            new Scanner(System.in).nextLine();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("");
    }
}
