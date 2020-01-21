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
 * @date 2018 2018/1/4 11:11
 */
public class creatAll {
    //源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/123.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/all.pdf";
    public static void main(String[] args) throws Exception, DocumentException {
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
        pdfStamper.addAnnotation(form,1);
        TextField field2 = new TextField(writer, new Rectangle(300, 200, 400, 218), null);
        field2.setOptions(TextField.MULTILINE);
        form.addKid(field2.getTextField());
        pdfStamper.addAnnotation(form,1);


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

        RadioCheckField  checkField = new RadioCheckField(writer, new Rectangle(300, 500, 320, 518), null, "Yes");
        checkField.setCheckType(RadioCheckField.TYPE_CHECK);
        checkField.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
        checkField.setBorderColor(new BaseColor(0, 0, 0));
        checkField.setBackgroundColor(new BaseColor(255, 255, 255));
        form.addKid(checkField.getCheckField());
        pdfStamper.addAnnotation(form,1);

        //button
        form = PdfFormField.createPushButton(writer);
        PushbuttonField button = new PushbuttonField(writer, new Rectangle(100, 100, 200, 200), "Button1");
        button.setText("1111");
        button.setFontSize(0);
        button.setLayout(PushbuttonField.LAYOUT_ICON_TOP_LABEL_BOTTOM);
        button.setBackgroundColor(new BaseColor(0, 255, 255));
        form.addKid(button.getField());
        pdfStamper.addAnnotation(form,1);

        PushbuttonField button2 = new PushbuttonField(writer, new Rectangle(200, 100, 300, 200), "Button2");
        button2.setText("222");
        button2.setFontSize(0);
        button2.setLayout(PushbuttonField.LAYOUT_ICON_TOP_LABEL_BOTTOM);
        button2.setBackgroundColor(new BaseColor(0, 255, 255));
        form.addKid(button2.getField());
        pdfStamper.addAnnotation(form,1);

    }
}
