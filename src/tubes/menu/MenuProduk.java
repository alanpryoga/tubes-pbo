package tubes.menu;

import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuProduk {
    
    public void tampilMenu(Koneksi conn, User user) {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        
        while ( ! exit) {
            System.out.println("-------------- MENU PRODUK --------------");
            System.out.println("1. Tampil Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Cari Produk");
            System.out.println("6. Kembali");
            System.out.println("========================================");
            System.out.print("Pilihan [1/2/3/4/5/6] = ");
            int menu = scan.nextInt();
            System.out.println("========================================");
            
            switch (menu) {
                case 1: {
                    TampilProduk tampilProduk = new TampilProduk();
                    tampilProduk.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    TambahProduk tambahProduk = new TambahProduk();
                    tambahProduk.tampilMenu(conn, user);
                    break;
                }
                case 4: {
                    HapusProduk hapusProduk = new HapusProduk();
                    hapusProduk.tampilMenu(conn, user);
                    break;
                }
                case 5: {
                    CariProduk cariProduk = new CariProduk();
                    cariProduk.tampilMenu(conn, user);
                    break;
                }
                case 6: {
                    exit = true;
                    break;
                }
            }
        }
    }
}
