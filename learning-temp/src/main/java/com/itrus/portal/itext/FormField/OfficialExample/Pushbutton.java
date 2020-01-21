package com.itrus.portal.itext.FormField.OfficialExample;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal
 * @Description:
 * @date 2017 2017/12/28 9:14
 */
public class Pushbutton {
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/out.pdf";
    public static final String JAPANESE = "\u3042\u304d\u3089";

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Pushbutton().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PushbuttonField button = new PushbuttonField(writer, new Rectangle(0, 0, 100, 100), "japanese");

        button.setText(JAPANESE);
        writer.addAnnotation(button.getField());
        document.close();
    }
}
