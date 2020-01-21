package com.itrus.portal.itext.FormField.OfficialExample;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.OfficialExample
 * @Description:
 * @date 2018 2018/1/3 11:25
 */
public class FileSelectionExample {
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/out.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FileSelectionExample().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        TextField file = new TextField(writer, new Rectangle(36, 788, 559, 806), "myfile");
        file.setOptions(TextField.FILE_SELECTION);
        PdfFormField upload = file.getTextField();
        upload.setAdditionalActions(PdfName.U,
                PdfAction.javaScript(
                        "this.getField('myfile').browseForFileToSubmit();"
                                + "this.getField('mytitle').setFocus();",
                        writer));
        writer.addAnnotation(upload);
        TextField title = new TextField(writer, new Rectangle(36, 752, 559, 770), "mytitle");
        writer.addAnnotation(title.getTextField());
        document.close();
    }
}
