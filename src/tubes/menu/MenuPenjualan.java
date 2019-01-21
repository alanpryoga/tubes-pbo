package tubes.menu;

import java.text.ParseException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuPenjualan {
    
    public void tampilMenu(Koneksi conn, User user) throws ParseException {
        boolean close = false;
        Scanner scan = new Scanner(System.in);
        
        while ( ! close) {
            System.out.println("------------ MENU PENJUALAN ------------");
            System.out.println("1. Entri Penjualan");
            System.out.println("2. Tampil Data Penjualan");
            System.out.println("3. Tutup");
            System.out.println("========================================");
            System.out.print("Pilihan [1/2/3] = ");
            int menu = scan.nextInt();
            System.out.println("========================================");
            
            switch (menu) {
                case 1: {
                    EntriPenjualan entriPenjualan = new EntriPenjualan();
                    entriPenjualan.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    TampilPenjualan tampilPenjualan = new TampilPenjualan();
                    tampilPenjualan.tampilMenu(conn, user);
                    break;
                }
                case 3: {
                    close = true;
                    break;
                }
            }
        }
        
    }
}
