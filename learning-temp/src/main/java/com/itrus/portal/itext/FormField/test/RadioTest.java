package com.itrus.portal.itext.FormField.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * radio域x设置为距离20 y距离为18体检最好
 * radio表单只能创建一次，也只能被加入到pdfStamper一次，之前被加入的不能被选中
 */
public class RadioTest {
	//源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/heng1.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/radio.pdf";

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
         //radio框
         // PdfFormField radioForm=PdfFormField.createRadioButton(writer, true);
         PdfFormField  radiogroup=PdfFormField.createRadioButton(writer, true);

        radiogroup.setFieldName("test");
        RadioCheckField bt=new RadioCheckField(writer, new Rectangle(100, 500, 120, 518), null, "v1");

        // bt.setCheckType(RadioCheckField.TYPE_CROSS);//X型
        // bt.setCheckType(RadioCheckField.TYPE_DIAMOND);//菱形
        // bt.setCheckType(RadioCheckField.TYPE_SQUARE);//正方形
        // bt.setCheckType(RadioCheckField.TYPE_STAR);//星星图案
        // checkField.setCheckType(RadioCheckField.TYPE_CHECK);//checkbox
        bt.setCheckType(RadioCheckField.TYPE_CIRCLE);//圆型 radio
        bt.setBackgroundColor(new GrayColor(0.8f));//这个颜色看起来比较舒服，建议只采用设置背景颜色
        // bt.setChecked(true);//设置radio是否被选中
        PdfFormField f1 = bt.getRadioField();
        f1.setPlaceInPage(1);

        radiogroup.addKid(f1);


        RadioCheckField bt3=new RadioCheckField(writer, new Rectangle(100, 300, 120, 318), null, "v3");
        bt3.setCheckType(RadioCheckField.TYPE_CIRCLE);//圆型 radio
        bt3.setBackgroundColor(new GrayColor(0.8f));
        // bt2.setChecked(false);
        PdfFormField f3 = bt3.getRadioField();
        radiogroup.addKid(f3);

        RadioCheckField bt2=new RadioCheckField(writer, new Rectangle(100, 400, 120, 418), null, "v2");
        bt2.setCheckType(RadioCheckField.TYPE_CIRCLE);//圆型 radio
        bt2.setBackgroundColor(new GrayColor(0.8f));
        // bt2.setChecked(false);
        PdfFormField f2 = bt2.getRadioField();
        radiogroup.addKid(f2);






        pdfStamper.addAnnotation(radiogroup,1);




    }
    
    
    
}
