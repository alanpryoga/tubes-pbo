package tubes.menu;

import java.util.Scanner;
import tubes.User;
import tubes.db.Koneksi;

public class MenuPengguna {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        boolean close = false;
        
        while ( ! close) {
            System.out.println("------------- MENU PENGGUNA ------------");
            System.out.println("1. Tampil Data Pengguna");
            System.out.println("2. Tambah Pengguna");
            System.out.println("3. Edit Pengguna");
            System.out.println("4. Hapus Pengguna");
            System.out.println("5. Cari Pengguna");
            System.out.println("6. Keluar");
            System.out.println("========================================");
            System.out.print("Pilihan [1/2/3/4/5/6] = ");
            int menu = scan.nextInt();
            System.out.println("========================================");
            
            switch (menu) {
                case 1: {
                    TampilPengguna tampilPengguna = new TampilPengguna();
                    tampilPengguna.tampilMenu(conn, user);
                    break;
                }
                case 2: {
                    TambahPengguna tambahPengguna = new TambahPengguna();
                    tambahPengguna.tampilMenu(conn, user);
                    break;
                }
                case 3: {
                    EditPengguna editPengguna = new EditPengguna();
                    editPengguna.tampilMenu(conn, user);
                    break;
                }
                case 4: {
                    HapusPengguna hapusPengguna = new HapusPengguna();
                    hapusPengguna.tampilMenu(conn, user);
                    break;
                }
                case 5: {
                    CariPengguna cariPengguna = new CariPengguna();
                    cariPengguna.tampilMenu(conn, user);
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
