package com.itrus.portal.itext.Utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.Utils
 * @Description:
 * 此工具类是为了在pdf(已有内容)上创建表单域，如text、 radio、checkbox、button
 * 1.创建表单域的方式有2中，一种是docume 一种是PdfStamper,为什么要使用PdfStamper?因为document会
 * 将之前的pdf文件上的内容初始化，变为一张白纸，不符合我们要求。还有就是PdfStamper可以获得输出流，
 * 我们可以利用输出流做一下别的事情。
 * 2.在pdf上创建表单域，需要注意以下几点
 *    (1)需要一组坐标值llx.lly.urx.ury和页数,以此确定域在pdf中的位置，
 *    (2)所创建文本域的名字，可以理解为给域起了个名字。
 *  3.pdfStamper 将额外的内容应用于PDF文档的页面。这个额外的内容可以是pdfcontentbyte允许的所有对象，
 *  包括来自其他pdf的页面。原始PDF将保留所有的交互元素，包括书签，链接和表单字段。
 * @date 2018 2018/1/5 9:12
 */
public class CreatFormFieldUtils {
    //源文件
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/123.pdf";
    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/out.pdf";

    public static void main(String[] args){
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(save));
            reader=new PdfReader(new FileInputStream(new File(DEST)));
            pdfStamper=new PdfStamper(reader,out);

            FieldInfo fieldInfo=new FieldInfo();
            fieldInfo.setName("test");
            fieldInfo.setType("TextField");
            fieldInfo.setType("PushbuttonField");
            fieldInfo.setLlx(200);
            fieldInfo.setLly(200);
            fieldInfo.setUrx(300);
            fieldInfo.setUry(218);
            fieldInfo.setPage(1);
            creatButtonField(pdfStamper,fieldInfo);

            pdfStamper.close();
            System.out.println("添加完毕。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  创建文本域
     * @method  creatTextField
     * @param pdfStamper  额外的内容应用于PDF文档的页面
     * @param fieldInfo 封装创建域的一些信息
     * @return void
     * @date: 2018-01-05
     * @author:qiumeng
     */
    public static void creatTextField(PdfStamper pdfStamper, FieldInfo fieldInfo) throws IOException, DocumentException {
        PdfWriter writer=pdfStamper.getWriter();
        //创建text表单
        PdfFormField form = PdfFormField.createEmpty(writer);
        //chuan表单域
        BaseField field = fieldInfo.getType(writer);

        TextField textField= ((TextField) field);
        //textField.set？可以对文本域设置一下属性 可读、等等
        setComomProperty(fieldInfo,textField);

        form.addKid(textField.getTextField());

        pdfStamper.setFormFlattening(true);//使表单不再可写，也就是禁用表单了
        pdfStamper.addAnnotation(form,fieldInfo.getPage());
    }

    /**
     *  创建radio和checkbox域
     * @method  creatRadioField
     * @param pdfStamper  额外的内容应用于PDF文档的页面
     * @param fieldInfo 封装创建域的一些信息
     * @return void
     * @date: 2018-01-05
     * @author:qiumeng
     */
    public static void creatRadioField(PdfStamper pdfStamper, FieldInfo ... fieldInfo) throws IOException, DocumentException {
        PdfWriter writer=pdfStamper.getWriter();
        PdfFormField form =PdfFormField.createRadioButton(writer, true);
        for (FieldInfo info : fieldInfo) {
            BaseField field = info.getType(writer);
            //radioCheckField.set? 可以设置一些radio背景颜色等
            RadioCheckField radioCheckField=((RadioCheckField)field);

            PdfFormField radioFormField=radioCheckField.getRadioField();
            PdfFormField checkFormField=radioCheckField.getRadioField();
            setComomProperty(info,field);
            switch (info.getType()) {
                case "RadioField":
                    radioFormField.setPlaceInPage(info.getPage());
                    form.addKid(radioFormField);
                    break;
                case "CheckboxField":
                    checkFormField.setPlaceInPage(info.getPage());
                    form.addKid(checkFormField);
                    break;
                default:
                    throw new IllegalArgumentException("Field type conversion failed 'BaseField to " + info.getType() + "'");
            }
        }
        //因为radiocheck域比较特殊，只能被加入一次。页数处理在PdfFormField，优先级最高
        pdfStamper.addAnnotation(form,1);
        pdfStamper.setFormFlattening(true);//使表单不再可写，也就是禁用表单了
    }



    /**
     *  创建button
     * @method  creatButtonField
     * @param pdfStamper  额外的内容应用于PDF文档的页面
     * @param fieldInfo 封装创建域的一些信息
     * @return void
     * @date: 2018-01-05
     * @author:qiumeng
     */
    public static void creatButtonField(PdfStamper pdfStamper, FieldInfo fieldInfo) throws IOException, DocumentException {
        PdfWriter writer=pdfStamper.getWriter();

        PdfFormField buttonForm = PdfFormField.createPushButton(writer);

        BaseField field = fieldInfo.getType(writer);

        PushbuttonField button=(PushbuttonField) field;
        //button value值
        button.setText("\u3042\u304d\u3089");
        // button.setImage(Image.getInstance("image.png"));
        button.setFontSize(0);
        button.setLayout(PushbuttonField.LAYOUT_ICON_TOP_LABEL_BOTTOM);
        button.setBackgroundColor(new BaseColor(0, 255, 255));

        setComomProperty(fieldInfo,field);

        PdfFormField buttonFormField = button.getField();
        buttonFormField.setAction(PdfAction.createSubmitForm("http://www.baidu.com", null, 0));

        buttonForm.addKid(buttonFormField);


        pdfStamper.setFormFlattening(true);//使表单不再可写，也就是禁用表单了
        pdfStamper.addAnnotation(buttonForm,fieldInfo.getPage());
    }


    /**
     *  根据fieldInfo.getType()类型类型来创建不同域，可同时创建。
     *  目前可以根据传入的坐标和类型同时创造text、radio、checkbox、button
     * @method  creatAllFormField
     * @param stamper  额外的内容应用于PDF文档的页面
     * @param fields 封装创建域的一些信息
     * @return void
     * @date: 2018-01-05
     * @author:qiumeng
     */
    public static void creatAllFormField(PdfStamper stamper, FieldInfo... fields) throws IOException, DocumentException {
        PdfWriter writer = stamper.getWriter();
        //使表单不再可写，也就是禁用表单了
        stamper.setFormFlattening(true);

        List<FieldInfo> radioCheckfields=new ArrayList();
        for (FieldInfo fieldInfo : fields) {
            PdfFormField form = PdfFormField.createEmpty(writer);
            if("RadioField".equals(fieldInfo.getType())||"CheckboxField".equals(fieldInfo.getType())){
                radioCheckfields.add(fieldInfo);
                continue;
            }else if("PushbuttonField".equals(fieldInfo.getType())){
                form=PdfFormField.createPushButton(writer);
            }
            //获取表单域
            BaseField field = fieldInfo.getType(writer);
            if(field instanceof  PushbuttonField){//如果是button域，需要设置边框等。。可以明显看到
                field.setText(fieldInfo.getName());
                field.setFontSize(0);
                ((PushbuttonField)field).setLayout(PushbuttonField.LAYOUT_ICON_TOP_LABEL_BOTTOM);
                field.setBackgroundColor(new BaseColor(0, 255, 255));
                field.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
                field.setBorderColor( new BaseColor(255, 0, 0));
                field.setBorderWidth(3);
            }
            setComomProperty(fieldInfo,field);
            switch (fieldInfo.getType()) {
                case "TextField":
                    form.addKid(((TextField) field).getTextField());
                    break;
                case "PushbuttonField":
                    //这步设置是为了给button加一下功能，超链接。。等等。目前还是不需要的
                    // ((PushbuttonField) field).getField().setAction(PdfAction.createSubmitForm("http://www.baidu.com", null, 0));
                    form.addKid(((PushbuttonField) field).getField());
                    break;
                default:
                    throw new IllegalArgumentException("Field type conversion failed 'BaseField to " + fieldInfo.getType() + "'");
            }
            stamper.addAnnotation(form,fieldInfo.getPage());
        }
        //radio需要单独处理
        PdfFormField radioFormField =PdfFormField.createRadioButton(writer, true);
        // PdfFormField checkboxFormField =PdfFormField.createRadioButton(writer, true);
        for (FieldInfo fieldInfo : radioCheckfields) {
            BaseField field = fieldInfo.getType(writer);
            setComomProperty(fieldInfo,field);
            switch (fieldInfo.getType()) {
                case "RadioField":
                    ((RadioCheckField)field).getRadioField().setPlaceInPage(fieldInfo.getPage());
                    radioFormField.addKid(((RadioCheckField)field).getRadioField());
                    break;
                case "CheckboxField":
                    ((RadioCheckField)field).getCheckField().setPlaceInPage(fieldInfo.getPage());
                    radioFormField.addKid(((RadioCheckField)field).getCheckField());
                    break;
                default:
                    throw new IllegalArgumentException("Field type conversion failed 'BaseField to " + fieldInfo.getType() + "'");
            }
        }
        //radioFormField设置页数优先于stamper设置页数
        stamper.addAnnotation(radioFormField,1);
    }









    /**
     * 设置表单域公共属性
     * @param fieldInfo
     * @param field
     * @throws IOException
     * @throws DocumentException
     */
    private  static  void setComomProperty(FieldInfo fieldInfo,BaseField field) throws IOException, DocumentException {
        String text = fieldInfo.getText();
        if (text != null && !"".equals(text)) {
            field.setText(text);
        }
        BaseColor textColor = fieldInfo.getTextColor();
        if (textColor != null) {
            field.setTextColor(textColor);
        }
        BaseFont font = fieldInfo.getFont();
        if (font != null) {
            field.setFont(font);
        }

        float fontSize = fieldInfo.getFontSize();
        if (fontSize != 0) {
            field.setFontSize(fontSize);
        }
        Integer visibility = fieldInfo.getVisibility();
        if (visibility != null) {
            field.setVisibility(visibility);
        }
    }
}
