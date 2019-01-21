package tubes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    
    private Connection connection;

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Konfigurasi config) {
       String connString;
        
       try {
           connString = "jdbc:mysql://" + config.getHost() + "/" + config.getDatabase();
           
           this.connection = DriverManager.getConnection(connString, config.getUsername(), config.getPassword());
       } catch (SQLException e) {
           System.out.println("Terjadi kesalahan dalam koneksi database: " + e.toString());
       }
    }
}