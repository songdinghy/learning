package com.itrus.portal.itext.FormField.OfficialExample;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextField {

    public static final String DEST = "C:\\Users\\64229\\Desktop\\word2pdf\\汕尾旅游方案2_new.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TextField().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        com.itextpdf.text.pdf.TextField file = new com.itextpdf.text.pdf.TextField(writer, new Rectangle(36, 788, 559, 806), "myfile");
        file.setOptions(com.itextpdf.text.pdf.TextField.FILE_SELECTION);
        PdfFormField upload = file.getTextField();
        upload.setAdditionalActions(PdfName.U,
                PdfAction.javaScript(
                        "this.getField('myfile').browseForFileToSubmit();"
                                + "this.getField('mytitle').setFocus();",
                        writer));
        writer.addAnnotation(upload);
        com.itextpdf.text.pdf.TextField title2 = new com.itextpdf.text.pdf.TextField(writer, new Rectangle(100,10,200,100), "mytitle");
        //com.itextpdf.text.pdf.TextField title = new com.itextpdf.text.pdf.TextField(writer, new Rectangle(40, 788, 559, 100), "mytitle");
        //writer.addAnnotation(title.getTextField());
        writer.addAnnotation(title2.getTextField());
        document.close();
    }
}
