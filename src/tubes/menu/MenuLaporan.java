package tubes.menu;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuLaporan {
    
    public void tampilMenu(Koneksi conn, User user) throws FileNotFoundException, DocumentException, ParseException {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        
        while ( ! exit) {
            System.out.println("------------- MENU LAPORAN -------------");
            System.out.println("1. Laporan Penjualan");
            System.out.println("2. Laporan Persediaan Barang");
            System.out.println("3. Buku Penjualan");
            System.out.println("4. Keluar Menu Laporan");
            System.out.println("========================================");
            System.out.print("Pilihan [1/2/3/4] = ");
            int menu = scan.nextInt();
            System.out.println("========================================");
            
            switch (menu) {
                case 1: {
                    LaporanPenjualan laporanPenjualan = new LaporanPenjualan();
                    laporanPenjualan.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    LaporanPersediaan laporanPersediaan = new LaporanPersediaan();
                    laporanPersediaan.tampilMenu(conn, user);
                    break;
                }
                case 3: {
                    BukuPenjualan bukuLaporan = new BukuPenjualan();
                    bukuLaporan.tampilMenu(conn, user);
                    break;
                }
                case 4: {
                    exit = true;
                    break;
                }
            }
        }
    }
}
