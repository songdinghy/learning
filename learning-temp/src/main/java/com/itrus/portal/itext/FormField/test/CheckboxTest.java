package com.itrus.portal.itext.FormField.test;

import com.itextpdf.text.BaseColor;
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
 * @Description:
 * @date 2018 2018/1/11 13:17
 */
public class CheckboxTest {
    //源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/heng1.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/checkbox.pdf";

    public static void main(String[] args) {

        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(save));
            reader=new PdfReader(new FileInputStream(new File(DEST)));
            pdfStamper=new PdfStamper(reader,out);
            //创建radio框
            creatRadioCheckField(pdfStamper,reader);
            pdfStamper.close();
            System.out.println("添加完毕。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void creatRadioCheckField(PdfStamper pdfStamper,PdfReader reader) throws IOException, DocumentException {
        PdfWriter writer = pdfStamper.getWriter();
        //checkbox 创建实例时，filename值要有不能为null onvalue值为Yes为选择状态，Off为固定状态
        PdfFormField checkBoxgroup=PdfFormField.createCheckBox(writer);

        RadioCheckField checkField = new RadioCheckField(writer, new Rectangle(300, 500, 320, 518), "checkbox", "Yes");
        checkField.setCheckType(RadioCheckField.TYPE_CHECK);
        checkField.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
        checkField.setBorderColor(new BaseColor(0, 0, 0));
        checkField.setBackgroundColor(new BaseColor(255, 255, 255));
        checkField.setChecked(true);
        checkField.setRotation(reader.getPageRotation(1));
        checkBoxgroup.addKid(checkField.getCheckField());
        pdfStamper.addAnnotation(checkBoxgroup,1);

        PdfFormField  checkBoxgroup1=PdfFormField.createCheckBox(writer);

        RadioCheckField  checkField1 = new RadioCheckField(writer, new Rectangle(400, 500, 420, 518), "checkbox1", "Yes");
        checkField1.setCheckType(RadioCheckField.TYPE_CHECK);
        checkField1.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
        checkField1.setBorderColor(new BaseColor(0, 0, 0));
        checkField1.setBackgroundColor(new BaseColor(255, 255, 255));
        checkField1.setRotation(reader.getPageRotation(1));
        checkBoxgroup1.addKid(checkField1.getCheckField());
        pdfStamper.addAnnotation(checkBoxgroup1,1);
    }
}
