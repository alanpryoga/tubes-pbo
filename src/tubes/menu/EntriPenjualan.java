package tubes.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import tubes.DetailPenjualan;
import tubes.Pelanggan;
import tubes.Penjualan;
import tubes.Produk;
import tubes.User;
import tubes.db.Koneksi;

public class EntriPenjualan {
    
    public void tampilMenu(Koneksi conn, User user) {
        Scanner scan = new Scanner(System.in);
        Penjualan penjualan = new Penjualan();
        String noPenjualan = "";
        int subtotal, diskon, pajak, totalPembayaran;
        
        subtotal = 0;
           
        try {
            // Mendapatkan nomor penjualan sebelumnya
            String sql = "SELECT * FROM `penjualan` "
                    + "ORDER BY `no_penjualan` DESC "
                    + "LIMIT 1";
               
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
               
            if (resultSet.next()) {
                String noPnj = resultSet.getString("no_penjualan").substring(3);
                String an = "" + (Integer.parseInt(noPnj) + 1);
                String no1 = "";
                   
                switch (an.length()) {
                    case 1:
                        no1 = "000";
                        break;
                    case 2:
                        no1 = "00";
                        break;
                    case 3:
                        no1 = "0";
                        break;
                    case 4:
                        no1 = "";
                        break;
                }
                   
                noPenjualan = "PJL" + no1 + an;
            } else {
                noPenjualan = "PJL0001";
            }
               
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        System.out.println("No. Penjualan    : " + noPenjualan);
        System.out.println("Nama User        : " + user.getNamaUser());
        
        boolean inputPelanggan = false;
        Pelanggan pelanggan = new Pelanggan();
        
        while ( ! inputPelanggan) {
            System.out.print("ID Pelanggan     : ");
            int idPelanggan = scan.nextInt();

            try {
                String sql = "SELECT * FROM `pelanggan` "
                        + "WHERE `id_pelanggan` = '" + idPelanggan + "'";

                Statement statement = conn.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    pelanggan.setIdPelanggan(resultSet.getInt("id_pelanggan"));
                    pelanggan.setNamaPelanggan(resultSet.getString("nama_pelanggan"));
                    pelanggan.setAlamat(resultSet.getString("alamat"));
                    pelanggan.setNoTelp(resultSet.getString("no_telp"));
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            
            if (pelanggan.isNotNull()) {
                inputPelanggan = true;
            } else {
                System.out.print("Pelanggan tidak ditemukan, ulangi lagi!");
                new Scanner(System.in).nextLine();
            }
        }
        
        penjualan.setNoPenjualan(noPenjualan);
        penjualan.setIdUser(user.getId());
        penjualan.setIdPelanggan(pelanggan.getIdPelanggan());
        
        int no = 1;
        
        ArrayList<DetailPenjualan> listDetailPenjualan = new ArrayList<DetailPenjualan>();
        
        System.out.println("----------------------------------------");
        
        boolean inputDetail = true;
        
        while (inputDetail) {
            Scanner scanInputDetail = new Scanner(System.in);
            System.out.println("Masukkan barang ke-" + no);
            
            DetailPenjualan detailPenjualan = new DetailPenjualan();
            boolean produkInput = false;
            Produk produk = new Produk();
            
            while ( ! produkInput) {
                Scanner scanBarang = new Scanner(System.in);
                
                System.out.print("Kode Produk           : ");
                String kodeProduk = scanBarang.nextLine();

                try {
                    String sql = "SELECT * FROM `produk` "
                            + "WHERE `kode_produk` = '" + kodeProduk + "'";

                    Statement statement = conn.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        produk.setKodeProduk(resultSet.getString("kode_produk"));
                        produk.setNamaProduk(resultSet.getString("nama_produk"));
                        produk.setHarga(resultSet.getInt("harga"));
                        produk.setStok(resultSet.getInt("stok"));
                        produk.setAktif(resultSet.getInt("aktif"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }

                if (produk.isNotNull()) {
                    produkInput = true;
                } else {
                    System.out.print("Barang tidak ditemukan, ulangi lagi!");
                    new Scanner(System.in).nextLine();
                }
            }
            
            detailPenjualan.setNoPenjualan(noPenjualan);
            detailPenjualan.setKodeProduk(produk.getKodeProduk());
            
            System.out.println("Nama Produk           : " + produk.getNamaProduk());
            System.out.println("Harga Produk          : Rp " + produk.getHarga());
            System.out.print("Masukkan Jumlah       : ");
            int jumlah = scanInputDetail.nextInt();
            System.out.println("Subtotal              : Rp " + jumlah * produk.getHarga());
            
            detailPenjualan.setJumlah(jumlah);
            subtotal = subtotal + (jumlah * produk.getHarga());
            listDetailPenjualan.add(detailPenjualan);
            
            System.out.print("Ingin memasukkan barang lagi [y/t]? ");
            String confirm = scanInputDetail.next();
            
            if (confirm.equals("t") || confirm.equals("T")) {
                inputDetail = false;
            } else {
                no = no + 1;
            }
        }
        
        System.out.println("----------------------------------------");
        
        penjualan.setSubtotal(subtotal);
        System.out.println("Subtotal            : Rp " + penjualan.getSubtotal());
        
        System.out.print("Masukkan diskon     : Rp ");
        diskon = scan.nextInt();
        penjualan.setDiskon(diskon);
        
        System.out.print("Masukkan pajak      : Rp ");
        pajak = scan.nextInt();
        penjualan.setPajak(pajak);
        
        penjualan.setTotalPembayaran(subtotal - diskon + pajak);
        
        
        // Insert data penjualan
        try {
            String sql = "INSERT INTO `penjualan` (`no_penjualan`, `id_user`, `id_pelanggan`, `subtotal`, `diskon`, `pajak`, `total_pembayaran`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, penjualan.getNoPenjualan());
            preparedStatement.setInt(2, penjualan.getIdUser());
            preparedStatement.setInt(3, penjualan.getIdPelanggan());
            preparedStatement.setInt(4, penjualan.getSubtotal());
            preparedStatement.setInt(5, penjualan.getDiskon());
            preparedStatement.setInt(6, penjualan.getPajak());
            preparedStatement.setInt(7, penjualan.getTotalPembayaran());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        // Insert data detail penjualan
        for (DetailPenjualan listDetPnj : listDetailPenjualan) {
            try {
                String sql = "INSERT INTO `detail_penjualan` (`no_penjualan`, `kode_produk`, `jumlah`) "
                        + "VALUES (?, ?, ?)";
                
                PreparedStatement prepStatement = conn.getConnection().prepareStatement(sql);
                prepStatement.setString(1, listDetPnj.getNoPenjualan());
                prepStatement.setString(2, listDetPnj.getKodeProduk());
                prepStatement.setInt(3, listDetPnj.getJumlah());
                prepStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        
        System.out.print("Sukses melakukan penjualan!");
        new Scanner(System.in).nextLine();
    }
}
