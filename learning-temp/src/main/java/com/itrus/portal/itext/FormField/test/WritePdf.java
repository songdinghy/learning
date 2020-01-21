package com.itrus.portal.itext.FormField.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.test
 * @Description:
 * @date 2018 2018/1/11 10:51
 */
public class WritePdf {
    //text域
    // public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/text.pdf";
    //checkbox
    // public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/checkbox.pdf";
    //radio
    public static final String DEST =System.getProperty("user.dir")+"/pdf/FormField/radio.pdf";

    //目标文件
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/Write.pdf";

    public static void main(String[] args) {
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(save));
            reader=new PdfReader(new FileInputStream(new File(DEST)));
            pdfStamper=new PdfStamper(reader,out);
            // writePdf(reader,pdfStamper,new String("v1"));
            writeRadioPdf(reader,pdfStamper,new String("v1"));

            System.out.println("添加完毕。。。。");
            pdfStamper.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

    public static  void writeTextPdf( PdfReader reader,PdfStamper pdfStamper) throws IOException, DocumentException {
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        s .setField("text","我的老家");
        // pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);
    }

    /**
     * 给checkbox赋值
     * @param reader
     * @param pdfStamper
     * @param value Yes为勾选 Off为不勾选
     * @throws IOException
     * @throws DocumentException
     */
    public static  void writecheckboxPdf( PdfReader reader,PdfStamper pdfStamper,String value) throws IOException, DocumentException {
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        for (Map.Entry<String, AcroFields.Item> entry : acroFieldMap.entrySet()) {
            AcroFields.Item item =s.getFieldItem(entry.getKey());
            PdfDictionary merged = item.getMerged(0);
            PdfName type = merged.getAsName(PdfName.FT);
            if(PdfName.BTN.equals(type)){
                s .setField(entry.getKey(),value);
            }
        }
        pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);
    }

    /**
     * 给创建的radio赋值
     * @param reader
     * @param pdfStamper
     * @param value
     * @throws IOException
     * @throws DocumentException
     */
    public static  void writeRadioPdf( PdfReader reader,PdfStamper pdfStamper,String value) throws IOException, DocumentException {
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();

        for (Map.Entry<String, AcroFields.Item> entry : acroFieldMap.entrySet()) {
            String fieldName=entry.getKey();
            List<AcroFields.FieldPosition> fieldPositionLis=s.getFieldPositions(fieldName);
            String  [] getAppearanceStates=s.getAppearanceStates(fieldName);

            s .setField(entry.getKey(),value);
        }
        s.setField("test",value);
        pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);
    }

    /**
     * 给表单域赋值
     * text域   value-> 即文本内容
     * checkbox value-> “Yes”勾选 "Off"b不勾选
     * radio    value->  单个radio的名字
     * @param reader
     * @param pdfStamper
     * @param value
     * @throws IOException
     * @throws DocumentException
     */
    public static  void writePdf( PdfReader reader,PdfStamper pdfStamper,String value) throws IOException, DocumentException {
        AcroFields s = pdfStamper.getAcroFields();
        Map<String, AcroFields.Item> acroFieldMap=s.getFields();
        for (Map.Entry<String, AcroFields.Item> entry : acroFieldMap.entrySet()) {
            AcroFields.Item item =s.getFieldItem(entry.getKey());
            PdfDictionary merged = item.getMerged(0);
            PdfName type = merged.getAsName(PdfName.FT);

            s .setField(entry.getKey(),value);
        }
        pdfStamper.setFormFlattening(true);
        pdfStamper.setFreeTextFlattening(true);
    }


}
