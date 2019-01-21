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
import tubes.User;
import tubes.db.Koneksi;

public class LaporanPersediaan {
    
    public void tampilMenu(Koneksi conn, User user) throws FileNotFoundException, DocumentException {
        try {
            System.out.print("Sedang membuat laporan...\n");
            
            String sql = "SELECT * FROM `produk` "
                    + "ORDER BY `kode_produk` ASC";
            
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            Document document = new Document();
            PdfPTable table = new PdfPTable(4);
            
            Font tableFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
            
            table.addCell(new PdfPCell(new Phrase("Kode Produk", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Nama Produk", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Harga", tableFont)));
            table.addCell(new PdfPCell(new Phrase("Stok", tableFont)));
            table.setHeaderRows(1);
            
            PdfPCell[] cells = table.getRow(0).getCells();
            
            for (PdfPCell cell : cells) {
                cell.setBackgroundColor(BaseColor.GRAY);
            }
            
            while (resultSet.next()) {
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("kode_produk"), tableFont)));
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("nama_produk"), tableFont)));
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("harga"), tableFont)));
                table.addCell(new PdfPCell(new Phrase(resultSet.getString("stok"), tableFont)));
            }
            
            String documentLocation = "/Downloads/LaporanPersediaanBarang.pdf";
            
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + documentLocation));

            document.open();
            
            Paragraph title = new Paragraph("Laporan Persediaan Barang");
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
