package tubes.menu;

import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuPelanggan {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        boolean close = false;
        
        while ( ! close) {
            System.out.println("-------------- MENU PELANGGAN --------------");
            System.out.println("1. Tampil Data Pelanggan");
            System.out.println("2. Tambah Pelanggan");
            System.out.println("3. Edit Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Cari Pelanggan");
            System.out.println("6. Keluar");
            System.out.println("============================================");
            System.out.print("Pilihan [1/2/3/4/5/6] = ");
            int menu = scan.nextInt();
            System.out.println("============================================");

            switch (menu){
                case 1: {
                    TampilPelanggan tampilPelanggan = new TampilPelanggan();
                    tampilPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    TambahPelanggan tambahPelanggan = new TambahPelanggan();
                    tambahPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 3: {
                    EditPelanggan editPelanggan = new EditPelanggan();
                    editPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 4: {
                    HapusPelanggan hapusPelanggan = new HapusPelanggan();
                    hapusPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 5: {
                    CariPelanggan cariPelanggan = new CariPelanggan();
                    cariPelanggan.tampilMenu(conn, user);
                    break;
                }
                case 6: { 
                    close = true;
                    break;
                }
            }
        }
    }
}
