package tubes.menu;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuUtama {
    
    public void tampilMenu(Koneksi conn, User user) throws DocumentException, FileNotFoundException, ParseException {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Selamat datang, anda login sebagai: " + user.getNamaUser());
        
        while ( ! exit) {
            System.out.println("----------------------------------------");
            System.out.println("        Caffe Selatan Jakarta ☕        ");
            System.out.println("-------------- MENU UTAMA --------------");
            System.out.println("1. Penjualan");
            System.out.println("2. Data Produk");
            System.out.println("3. Data Pelanggan");
            System.out.println("4. Data Pengguna");
            System.out.println("5. Laporan");
            System.out.println("6. Ganti Password");
            System.out.println("7. Keluar");
            System.out.println("========================================");
            System.out.print("Pilihan [1/2/3/4/5/6/7] = ");
            int menu = scan.nextInt();
            System.out.println("========================================");
            System.out.println("");
            
            switch (menu) {
                case 1: {
                    MenuPenjualan menuPenjualan = new MenuPenjualan();
                    menuPenjualan.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    MenuProduk menuProduk = new MenuProduk();
                    menuProduk.tampilMenu(conn, user);
                    break;
                }
                case 3: {
                    MenuPelanggan menuPelanggan = new MenuPelanggan();
                    menuPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 4: {
                    MenuPengguna menuPengguna = new MenuPengguna();
                    menuPengguna.tampilMenu(conn, user);
                    break;
                }
                case 5: {
                    MenuLaporan menuLaporan = new MenuLaporan();
                    menuLaporan.tampilMenu(conn, user);
                    break;
                }
                case 6: {
                    GantiPassword gantiPassword = new GantiPassword();
                    gantiPassword.tampilMenu(conn, user);
                    break;
                }
                case 7: {
                    Scanner confirmScan = new Scanner(System.in);
                    System.out.print("Anda yakin ingin keluar [y/t] ? ");
                    String confirm = confirmScan.nextLine();
                    
                    if (confirm.equals("Y") || confirm.equals("y")) {
                       exit = true; 
                    }
                    
                    break;
                }
                default: {
                    System.out.println("Anda salah memeasukkan menu!");
                    break;
                }
            }
            
            System.out.println("");
        }
        
        System.out.println("Anda keluar dari program!");
        
        MenuLogin menuLogin = new MenuLogin();
        menuLogin.tampilMenu(conn);
    }
}
