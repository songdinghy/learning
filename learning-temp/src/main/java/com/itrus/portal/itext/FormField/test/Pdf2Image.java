//package com.itrus.portal.itext.FormField.test;
//
//import com.sun.pdfview.PDFFile;
//import com.sun.pdfview.PDFPage;
//import com.sun.pdfview.PDFRenderer;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.RandomAccessFile;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//
///**
// * @author qiumeng
// * @Package com.itrus.portal.itext.FormField.test
// * @Description:
// * @date 2018 2018/1/8 10:34
// */
//public class Pdf2Image {
//    //源文件
//    // public static final String DEST ="F:\\公司相关资料\\pdf\\heng1.pdf";
//    public static final String DEST ="F:\\公司相关资料\\pdf\\test1.pdf";
//    //目标文件
//    public static final String target ="F:\\公司相关资料\\pdf\\test1.png";
//    public static void main(String[] args){
//        Pdf_Png(1,DEST,target);
//    }
//
//    public  static   void Pdf_Png(int pageNumber,String desk,String target )   {
//        int pagen= pageNumber;
//        File file = new File(DEST);
//
//        PDFFile pdffile=null;
////      set up the PDF reading
//        try{
//            RandomAccessFile raf = new RandomAccessFile(file, "r");
//            FileChannel channel = raf.getChannel();
//            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
//            buf.clear();
//            byte[] b = new byte[buf.capacity()];
//            buf.get(b, 0, b.length);
//            // FileOutputStream outputStream =new FileOutputStream("F:\\公司相关资料\\pdf\\test2.pdf");
//            // outputStream.write(b);
//            // outputStream.close();
//            pdffile = new PDFFile(buf);
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//
//        if(pagen<pdffile.getNumPages())
//            return;
//        //print出该pdf文档的页数
//        System.out.println(pdffile.getNumPages());
//
//        //设置将第pagen也生成png图片
//        PDFPage page = pdffile.getPage(pagen);
////      create and configure a graphics object
//        int width = (int)page.getBBox().getWidth();
//        int height =(int)page.getBBox().getHeight();
//        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = img.createGraphics();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////      do the actual drawing
//        PDFRenderer renderer = new PDFRenderer(page, g2,
//                new Rectangle(0, 0, width, height), null, Color.RED);
//        try{
//            page.waitForFinish();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        renderer.run();
//        g2.dispose();
//
//        try{
//            ImageIO.write(img, "png", new File(target));
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//}
