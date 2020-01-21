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
 * @Package com.itrus.portal.itext
 * @Description:
 * @date 2018 2018/1/2 10:52
 */
public class test {
    //源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/123.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/radio.pdf";
    public static void main(String[] args) throws Exception, DocumentException{
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(save));
            reader=new PdfReader(new FileInputStream(new File(DEST)));
            pdfStamper=new PdfStamper(reader,out);
            //创建radio框
            creatAll(pdfStamper);
            pdfStamper.close();
            System.out.println("添加完毕。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void creatAll(PdfStamper pdfStamper) throws IOException, DocumentException {
        PdfWriter writer = pdfStamper.getWriter();
        //使表单不再可写，也就是禁用表单了
        pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);

        PdfFormField form = PdfFormField.createEmpty(writer);

        //text
        TextField field = new TextField(writer, new Rectangle(200, 200, 300, 218), null);
        field.setOptions(TextField.MULTILINE);
        form.addKid(field.getTextField());
        //radio
        form=PdfFormField.createRadioButton(writer, true);

        RadioCheckField bt=new RadioCheckField(writer, new Rectangle(100, 500, 120, 518), null, "v1");
        bt.setCheckType(RadioCheckField.TYPE_CIRCLE);//圆型 radio
        bt.setBackgroundColor(new GrayColor(0.8f));//这个颜色看起来比较舒服，建议只采用设置背景颜色

        RadioCheckField bt2=new RadioCheckField(writer, new Rectangle(100, 600, 120, 618), null, "v2");
        bt2.setCheckType(RadioCheckField.TYPE_CIRCLE);//圆型 radio
        bt2.setBackgroundColor(new GrayColor(0.8f));
        form.addKid(bt.getRadioField());
        form.addKid(bt2.getRadioField());
        //checkbox



    }
}
