package com.sd.learning;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//https://blog.csdn.net/qiumeng_1314/article/details/79114058
public class TestPdfTextField {

    public static void creatTextField(PdfStamper pdfStamper, PdfReader reader) throws IOException, DocumentException {
        PdfWriter writer = pdfStamper.getWriter();
        PdfFormField form = PdfFormField.createEmpty(writer);
        //普通文本框
        TextField  field = new TextField(writer, new Rectangle(100, 100, 100, 100), "text");
        field.setOptions(TextField.MULTILINE);
        //防止读取pdf文档时，就是有旋转角度的
        field.setRotation(reader.getPageRotation(1));
        //有些情况下，页数问题可以在这里设置，优先级最高
        field.getTextField().setPlaceInPage(1);
        form.addKid(field.getTextField());
        field.setOptions(TextField.VISIBLE);//文本域可见(相对于文本域是否高亮)
        // field.setOptions(TextField.HIDDEN);//文本域不可见
        // field.setOptions(TextField.VISIBLE_BUT_DOES_NOT_PRINT);//该字段可见，但不打印。
        // field.setOptions(TextField.HIDDEN_BUT_PRINTABLE);//该字段不可见，但不打印。
        // field.setOptions(TextField.HIDDEN_BUT_PRINTABLE);//该字段不可见，但不打印。
        // field.setOptions(TextField.READ_ONLY);//只读
        // field.setOptions(TextField.REQUIRED);//该字段在通过提交表单操作导出时必须具有值。
        // field.setOptions(TextField.MULTILINE);//规定区域内可以换行显示
        // field.setOptions(TextField.DO_NOT_SCROLL);//文本域不会有滚动条,对于单行字段为水平，对于多行字段为垂直,一旦区域满了，将不会再接受任何文字。
        // field.setOptions(TextField.PASSWORD);//该字段用于输入安全密码，该密码不应该被可视地显示在屏幕上。
        // field.setOptions(TextField.FILE_SELECTION);//个人理解好像是上传文件，不是很理解
        // field.setOptions(TextField.DO_NOT_SPELL_CHECK);//无拼写检查
        // field.setOptions(TextField.EDIT);//如果设置组合框包括一个可编辑的文本框以及一个下拉列表;如果清楚，它只包括一个下拉列表。这个标志只对组合字段有意义。
        // field.setOptions(TextField.MULTISELECT);//不管列表是否可以有多个选择。仅适用于/ ch列表字段，而不适用于组合框。
        // field.setOptions(TextField.COMB);//组合框标志。
        pdfStamper.addAnnotation(form,1);
    }


    public static  void main(String[] args) {

        System.out.println("args = [" + System.getProperty("user.dir")+"\\learning-temp\\" + "]");
        //模板路径
        String templatePath = "C:\\Users\\64229\\Desktop\\word2pdf\\汕尾旅游方案2.pdf";
        //生成的新文件路径
        String newPDFPath = "C:\\Users\\64229\\Desktop\\word2pdf\\汕尾旅游方案2_new.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);//输出流
            reader = new PdfReader(templatePath);//读取pdf模板


            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            // 创建文本域
            creatTextField(stamper,reader);

            AcroFields form = stamper.getAcroFields();
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                //填写内容部分
                form.setField(name, "2465465145648456");
                //
            }

            stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(
                    new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }
    }

}