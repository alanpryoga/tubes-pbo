package tubes;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import tubes.db.Koneksi;
import tubes.db.Konfigurasi;
import tubes.menu.MenuLogin;

public class Tubes {

    public static void main(String[] args) throws DocumentException, FileNotFoundException, ParseException {
        Konfigurasi conf = new Konfigurasi();
        conf.setHost("localhost");
        conf.setUsername("root");
        conf.setPassword("");
        conf.setDatabase("db_penjualan_caffe");
        
        Koneksi conn = new Koneksi();
        conn.setConnection(conf);
        
        MenuLogin menuLogin = new MenuLogin();
        menuLogin.tampilMenu(conn);
    }
}