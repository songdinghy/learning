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
 * 这是创建文本域的列子
 */
public class PushbuttonFieldTest {
	//源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/123.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/button.pdf";

    public static final String JAPANESE = "\u3042\u304d\u3089";
    public static void main(String[] args) {

	    FileOutputStream out= null;
	    PdfReader reader=null;
	    PdfStamper pdfStamper=null;
	    try {
	        out = new FileOutputStream(new File(save));
	        reader=new PdfReader(new FileInputStream(new File(DEST)));
	        pdfStamper=new PdfStamper(reader,out);
	        creatPushbuttonField(pdfStamper);
	        pdfStamper.close();
	        System.out.println("添加完毕。。。。");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
    /**
     * 
     * @param pdfStamper
     * @throws IOException
     * @throws DocumentException
     */
    public static void creatPushbuttonField(PdfStamper pdfStamper) throws IOException, DocumentException {
    	
   	 PdfWriter writer = pdfStamper.getWriter();
        //使表单不再可写，也就是禁用表单了
         pdfStamper.setFormFlattening(true);
         pdfStamper.setFreeTextFlattening(true);
         
        PdfFormField form = PdfFormField.createPushButton(writer);

        PushbuttonField button = new PushbuttonField(writer, new Rectangle(100, 100, 200, 200), "Button1");
        button.setText(JAPANESE);
        button.setFontSize(0);
        // button.setImage(Image.getInstance("image.png"));
        button.setLayout(PushbuttonField.LAYOUT_ICON_TOP_LABEL_BOTTOM);
        button.setBackgroundColor(new BaseColor(0, 255, 255));
        // button.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
        // button.setBorderColor( new BaseColor(255, 0, 0));
        // button.setBorderWidth(3);

        PdfFormField buttonFormField = button.getField();
        // buttonFormField.setAction(PdfAction.createSubmitForm("http://www.baidu.com", null, 0));
        // buttonFormField.setAction(PdfAction.createSubmitForm("http://www.baidu.com", null, 0));
        form.addKid(buttonFormField);


        pdfStamper.addAnnotation(form,1);
   }
}
