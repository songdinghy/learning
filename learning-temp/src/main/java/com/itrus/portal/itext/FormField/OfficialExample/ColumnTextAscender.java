package com.itrus.portal.itext.FormField.OfficialExample;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.OfficialExample
 * @Description:
 * @date 2018 2018/1/3 13:18
 */
public class ColumnTextAscender {
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/out.pdf";
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColumnTextAscender().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        // show the area for the column as a rectangle with red borders
        Rectangle rect = new Rectangle(50, 750, 250, 800);
        addColumn(writer, rect, false);
        rect = new Rectangle(300, 750, 500, 800);
        addColumn(writer, rect, true);
        // step 5
        document.close();
    }

    public void addColumn(PdfWriter writer, Rectangle rect, boolean useAscender) throws DocumentException {
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(0.5f);
        rect.setBorderColor(BaseColor.RED);
        PdfContentByte cb = writer.getDirectContent();
        cb.rectangle(rect);
        Phrase p = new Phrase("This text is added at the top of the column.");
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(rect);
        ct.setUseAscender(useAscender);
        ct.addText(p);
        ct.go();
    }
}
