import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class PDFExporter {

    public static void exportHistoryToPDF(String historyText) throws Exception {
        Document document = new Document();
        String outputPath = "billing_history.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        Paragraph title = new Paragraph("Electricity Billing History", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // History Text
        Font contentFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.DARK_GRAY);
        Paragraph content = new Paragraph(historyText, contentFont);
        content.setAlignment(Element.ALIGN_LEFT);
        document.add(content);

        document.close();
    }
}
