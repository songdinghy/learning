package com.itrus.portal.itext.FormField.OfficialExample;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.OfficialExample
 * @Description:
 * @date 2018 2018/1/3 11:24
 */
public class GenericFields {
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/out.pdf";

    public class FieldChunk extends PdfPageEventHelper {
        @Override
        public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
            TextField field = new TextField(writer, rect, text);
            try {
                writer.addAnnotation(field.getTextField());
            } catch (IOException ex) {
                throw new ExceptionConverter(ex);
            } catch (DocumentException ex) {
                throw new ExceptionConverter(ex);
            }
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new GenericFields().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPageEvent(new FieldChunk());
        document.open();
        Paragraph p = new Paragraph();
        p.add("The Effective Date is ");
        Chunk day = new Chunk("     ");
        day.setGenericTag("day");
        p.add(day);
        p.add(" day of ");
        Chunk month = new Chunk("     ");
        month.setGenericTag("month");
        p.add(month);
        p.add(", ");
        Chunk year = new Chunk("            ");
        year.setGenericTag("year");
        p.add(year);
        p.add(" that this will begin.");
        document.add(p);
        document.close();
    }
}
