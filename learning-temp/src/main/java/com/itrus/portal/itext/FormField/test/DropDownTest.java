package com.itrus.portal.itext.FormField.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.test
 * @Description:这是下拉列表
 * @date 2018 2018/1/4 15:05
 */
public class DropDownTest {

    //源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/123.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/dropDown.pdf";
    public static void main(String[] args) {

        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(save));
            reader=new PdfReader(new FileInputStream(new File(DEST)));
            pdfStamper=new PdfStamper(reader,out);
            creatTextField(pdfStamper);
            pdfStamper.close();
            System.out.println("添加完毕。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param pdfStamper
     * @throws IOException
     * @throws DocumentException
     */
    public static void creatTextField(PdfStamper pdfStamper) throws IOException, DocumentException {
        PdfWriter writer = pdfStamper.getWriter();
        //使表单不再可写，也就是禁用表单了
        pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);

        String [] arr={"1","2","3"};

        PdfFormField form = PdfFormField.createCombo(writer,false,arr,1);

        TextField textField=new TextField(writer, new Rectangle(200, 200, 300, 230),"qq");
        textField.setChoices(arr);

        PdfFormField pdfFormField=textField.getListField();
        pdfFormField.setPlaceInPage(1);

        form.addKid(pdfFormField);

        pdfStamper.addAnnotation(form,1);

    }
}
