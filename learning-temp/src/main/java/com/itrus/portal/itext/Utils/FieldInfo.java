package com.itrus.portal.itext.Utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.entity
 * @Description:封装创建域所需要的一些信息
 * @date 2018 2018/1/5 9:34
 */
public class FieldInfo {
    private String type;    //["TextField", "RadioCheckField"]
    private String name;
    private String text; // 只有设置了Form表单名称才会显示text的内容
    private String textColor; // [WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,RED,PINK,ORANGE,YELLOW,GREEN,MAGENTA,CYAN,BLUE,FACTOR]
    private String fontName;
    private String encoding;    //["UniGB-UCS2-H", "UniGB-UTF8-V"]
    private String formName;

    private int llx;
    private int lly;
    private int urx;
    private int ury;

    private float fontSize; //[ "STSong-Light" ]
    private int visibility; //BaseField ["VISIBLE"=0, "HIDDEN"=1, "VISIBLE_BUT_DOES_NOT_PRINT"=2, "HIDDEN_BUT_PRINTABLE"=3]
    private int page;

    public BaseField getType(PdfWriter writer) {
        BaseField field = null;
        if (name != null && !name.equals("")) {
            //域名字，每一个域名字不同以此来区分，
            Rectangle box = new Rectangle(llx, lly, urx, ury);
            switch (type) {
                case "TextField":
                    field = new TextField(writer, box, name);
                    field.setOptions(TextField.MULTILINE);//换行属性
                    break;
                case "RadioField":
                    //为radio是 filename为radio域名字，可以为空？做回显可以用到。onvalue 值区分不同的radio
                    field = new RadioCheckField(writer, box, name,name);
                    ( (RadioCheckField)field).setCheckType(RadioCheckField.TYPE_CIRCLE);
                    ( (RadioCheckField)field).setBackgroundColor(new GrayColor(0.8f));
                    break;
                case "CheckboxField":
                    field = new RadioCheckField(writer, box, name, "Yes");
                    ( (RadioCheckField)field).setCheckType(RadioCheckField.TYPE_CHECK);
                    break;
                case "PushbuttonField":
                    field = new PushbuttonField(writer, box, name);
                    break;
                default:
                    throw new IllegalArgumentException("failed to create field  '" + type + "'");
            }
        } else {
            throw new IllegalArgumentException("failed to create field  '" + type + "'  field name is null");
        }
        return field;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BaseColor getTextColor() {
        BaseColor color = null;
        if (textColor != null && !textColor.equals("")) {
            switch (textColor) {
                case "WHITE":
                    color = BaseColor.WHITE;
                    break;
                case "LIGHT_GRAY":
                    color = BaseColor.LIGHT_GRAY;
                    break;
                case "DARK_GRAY":
                    color = BaseColor.DARK_GRAY;
                    break;
                case "BLACK":
                    color = BaseColor.BLACK;
                    break;
                case "RED":
                    color = BaseColor.RED;
                    break;
                case "PINK":
                    color = BaseColor.PINK;
                    break;
                case "ORANGE":
                    color = BaseColor.ORANGE;
                    break;
                case "YELLOW":
                    color = BaseColor.YELLOW;
                    break;
                case "GREEN":
                    color = BaseColor.GREEN;
                    break;
                case "MAGENTA":
                    color = BaseColor.MAGENTA;
                    break;
                case "CYAN":
                    color = BaseColor.CYAN;
                    break;
                case "BLUE":
                    color = BaseColor.BLUE;
                    break;
                default:
                    throw new IllegalArgumentException("failed to create text color  '" + textColor + "'");
            }

        }
        return color;
    }

    public String getType() {
        return type;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public int getLlx() {
        return llx;
    }

    public void setLlx(int llx) {
        this.llx = llx;
    }

    public int getLly() {
        return lly;
    }

    public void setLly(int lly) {
        this.lly = lly;
    }

    public int getUrx() {
        return urx;
    }

    public void setUrx(int urx) {
        this.urx = urx;
    }

    public int getUry() {
        return ury;
    }

    public void setUry(int ury) {
        this.ury = ury;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public BaseFont getFont() throws IOException, DocumentException {
        BaseFont font = null;
        if (fontName != null && !fontName.equals("")) {
            switch (fontName) {
                case "STSong-Light":
                    font = BaseFont.createFont(fontName, encoding, BaseFont.EMBEDDED);
                    break;
            }
        }
        return font;
    }
}
