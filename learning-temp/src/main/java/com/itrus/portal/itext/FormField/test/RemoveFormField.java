package com.itrus.portal.itext.FormField.test;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

/**
 * @author qiumeng
 * @Package com.itrus.portal.itext.FormField.test
 * @Description:
 * @date 2018 2018/1/17 11:29
 */
public class RemoveFormField {
    //目标文件
    // public static final String save =System.getProperty("user.dir")+"/pdf/FormField/text.pdf";
    // public static final String save =System.getProperty("user.dir")+"/pdf/FormField/radio.pdf";
    public static final String save =System.getProperty("user.dir")+"/pdf/FormField/checkbox.pdf";
    //第二次读取
    public static final String second =System.getProperty("user.dir")+"/pdf/FormField/second.pdf";

    public static void main(String[] args) {
        FileOutputStream out= null;
        PdfReader reader=null;
        PdfStamper pdfStamper=null;
        try {
            out = new FileOutputStream(new File(second));

            reader=new PdfReader(new FileInputStream(new File(save)));
            pdfStamper=new PdfStamper(reader,out);
            AcroFields s = pdfStamper.getAcroFields();


            Map<String, AcroFields.Item> acroFieldMap=s.getFields();
            // Iterator<Map.Entry<String, AcroFields.Item>> it = s.getFields().entrySet().iterator();
            // while(it.hasNext()){
            //     Map.Entry<String, AcroFields.Item> entry = it.next();
            //     it.remove();
            //     s.removeField(entry.getKey());
            // }

            Set<String> keySet = acroFieldMap.keySet();
            Object[] keySetStr = keySet.toArray();
            for (Object s1 : keySetStr) {
                s.removeField(s1.toString());
            }



            pdfStamper.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
