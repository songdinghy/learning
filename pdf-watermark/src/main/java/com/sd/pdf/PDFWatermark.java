package com.sd.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.FileInputStream;

public class PDFWatermark {


    public static void main(String[] args) {
        try {
            watermark(new File("D:/设计模式培训.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void watermark(File file) throws Exception {
        //创建新pdf文件
        File tmpPDF = new File("D:/demo_" + file.getName());
        //打开pdf文件
        PDDocument doc = PDDocument.load(file);
        doc.setAllSecurityToBeRemoved(true);
        //遍历pdf所有页
        for (PDPage page : doc.getPages()) {
            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);
            String ts = "你吃屁";
            //引入字体文件 解决中文汉字乱码问题
            PDFont font = PDType0Font.load(doc, new FileInputStream("D:/msyh.ttf"), true);
            float fontSize = 30;
            PDResources resources = page.getResources();
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 水印透明度
            r0.setNonStrokingAlphaConstant(0.2f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            //水印颜色
            cs.setNonStrokingColor(200, 0, 0);
            cs.beginText();
            cs.setFont(font, fontSize);
            //根据水印文字大小长度计算横向坐标需要渲染几次水印
            float h = ts.length() * fontSize;
            for (int i = 0; i <= 10; i++) {
                // 获取旋转实例
                cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, 0));
                cs.showText(ts);
                for (int j = 0; j < 20; j++) {
                    cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, j * h));
                    cs.showText(ts);
                }
            }
            cs.endText();
            cs.restoreGraphicsState();
            cs.close();
            break;
        }
        doc.save(tmpPDF);

    }

}
