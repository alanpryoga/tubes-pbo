package tubes.menu;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tubes.User;
import tubes.db.Koneksi;

public class BukuPenjualan {
    
    public void tampilMenu(Koneksi conn, User user) throws FileNotFoundException, DocumentException, ParseException {
        try {
            System.out.print("Sedang membuat laporan...\n");
            
            String sql = "SELECT `penjualan`.`tanggal`, `penjualan`.`no_penjualan`, `penjualan`.`subtotal`, `penjualan`.`diskon`, `penjualan`.`pajak`, `penjualan`.`total_pembayaran`, `pelanggan`.`nama_pelanggan` "
                    + "FROM `penjualan` "
                    + "JOIN `pelanggan` ON `penjualan`.`id_pelanggan` = `pelanggan`.`id_pelanggan` "
                    + "ORDER BY `tanggal` DESC";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            Document document = new Document();
            PdfPTable table = new PdfPTable(7);
            
            Font tableFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
            
            table.addCell(new PdfPCell(new Phrase("Tanggal", tableFont)));
            table.addCell(new PdfPCell(new Phrase("No. Penjualan", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Pelanggan", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Sub Total", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Diskon", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Pajak", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Total", tableFont)));
            table.setHeaderRows(1);
            
            PdfPCell[] cells = table.getRow(0).getCells();
            
            for (PdfPCell cell : cells) {
                cell.setBackgroundColor(BaseColor.GRAY);
            }       
            
            while (resultSet.next()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("tanggal"));
                
                table.addCell(new PdfPCell(new Phrase(new SimpleDateFormat("dd-MM-yyyy").format(date), tableFont)));
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("no_penjualan"), tableFont)));
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("nama_pelanggan"), tableFont)));
                table.addCell(new PdfPCell(new Phrase("Rp " + resultSet.getString("subtotal"), tableFont)));
                table.addCell(new PdfPCell(new Phrase("Rp " + resultSet.getString("diskon"), tableFont)));
                table.addCell(new PdfPCell(new Phrase("Rp " + resultSet.getString("pajak"), tableFont)));
                table.addCell(new PdfPCell(new Phrase("Rp " + resultSet.getString("total_pembayaran"), tableFont)));
            }
            
            String documentLocation = "/Downloads/BukuPenjualan.pdf";
            
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + documentLocation));

            document.open();
            
            Paragraph title = new Paragraph("Buku Penjualan");
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16f);
            
            document.add(title);
            document.add(table);
            document.close();
            
            System.out.println("Laporan telah dibuat pada folder: " + documentLocation);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
