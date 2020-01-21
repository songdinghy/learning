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
 * @Package com.itrus.portal.itext.FormField.OfficialExample
 * @Description: x距离100 y距离18
 * @date 2018 2018/1/3 13:18
 */
public class ceatTextTest {
	//源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/heng1.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/text.pdf";
    public static void main(String[] args) {

	    FileOutputStream out= null;
	    PdfReader reader=null;
	    PdfStamper pdfStamper=null;
	    try {
	        out = new FileOutputStream(new File(save));
	        reader=new PdfReader(new FileInputStream(new File(DEST)));
	        pdfStamper=new PdfStamper(reader,out);
	        creatTextField(pdfStamper,reader);
	        pdfStamper.close();
	        System.out.println("添加完毕。。。。");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
    /**
     * 创建文本域
     * @param pdfStamper
     * @throws IOException
     * @throws DocumentException
     */
    public static void creatTextField(PdfStamper pdfStamper, PdfReader reader) throws IOException, DocumentException {
        PdfWriter writer = pdfStamper.getWriter();


        PdfFormField form = PdfFormField.createEmpty(writer);
        // PdfFormField form = PdfFormField.createTextField(writer,true,false,20);

        //普通文本框
        TextField  field = new TextField(writer, new Rectangle(200, 200, 400, 300), "text");
        field.setOptions(TextField.MULTILINE);
        field.setRotation(reader.getPageRotation(1));
        form.addKid(field.getTextField());

        System.out.println(form.isForm());

        // TextField file = new TextField(writer, new Rectangle(200, 182, 300, 200), null);
        // file.setOptions(TextField.VISIBLE);//文本域可见(相对于文本域是否高亮)
        // file.setOptions(TextField.HIDDEN);//文本域不可见
        // file.setOptions(TextField.VISIBLE_BUT_DOES_NOT_PRINT);//该字段可见，但不打印。
        // file.setOptions(TextField.HIDDEN_BUT_PRINTABLE);//该字段不可见，但不打印。
        // file.setOptions(TextField.HIDDEN_BUT_PRINTABLE);//该字段不可见，但不打印。
        // file.setOptions(TextField.READ_ONLY);//只读
        // file.setOptions(TextField.REQUIRED);//该字段在通过提交表单操作导出时必须具有值。
        // file.setOptions(TextField.MULTILINE);//规定区域内可以换行显示
        // file.setOptions(TextField.DO_NOT_SCROLL);//文本域不会有滚动条,对于单行字段为水平，对于多行字段为垂直,一旦区域满了，将不会再接受任何文字。
        // file.setOptions(TextField.PASSWORD);//该字段用于输入安全密码，该密码不应该被可视地显示在屏幕上。
        // file.setOptions(TextField.FILE_SELECTION);//个人理解好像是上传文件，不是很理解
        // file.setOptions(TextField.DO_NOT_SPELL_CHECK);//无拼写检查
        // file.setOptions(TextField.EDIT);//如果设置组合框包括一个可编辑的文本框以及一个下拉列表;如果清楚，它只包括一个下拉列表。这个标志只对组合字段有意义。
        // file.setOptions(TextField.MULTISELECT);//不管列表是否可以有多个选择。仅适用于/ ch列表字段，而不适用于组合框。
        // file.setOptions(TextField.COMB);//组合框标志。

        // PdfFormField upload = file.getTextField();
        // upload.setPlaceInPage(1);
        // form.addKid(upload);


        pdfStamper.addAnnotation(form,1);

    }

}
