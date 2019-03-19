package com.sd.learning.jsontomysql;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author SONGDING
 * @Description
 * @date 2019/3/18 13:45
 */
public class RandomAccessFileDemo {

    /**
     * @param args
     * @throws IOException
     * @代码来自传智播客
     */
    public static void main(String[] args) throws IOException {
        /*
         * RandomAccessFile:
         * 特点：
         * 1，只能操作文件。
         * 2，既能读，又能写。
         * 3，维护了一个byte数组。内部定义了字节流的读取和写入。
         * 4，通过对指针的操作可以实现对文件的任意位置的读取和写入。
         */
        //writeFile();
        readFileLine();
    }

    public static void readFileLine() throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\myworkspace\\项目\\5.润心贷-个人信息接口开发\\中国邮编数据\\中国邮编数据.txt", "r");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test" + "?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true", "root", "123456");
            connection.setAutoCommit(false);
            //设置手动提交
            // 预编译sql对象,只编译一回
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `china_area` (`province`, `city`, `district`, `post_number`, `address`, `jd` )  values(?,?,?,?,?,?)");

            String content = randomAccessFile.readLine();
            int i = 0;
            while (content != null) {
                content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
                // System.out.println(content);
                JSONObject object = JSONObject.parseObject(content);
                ps.setString(1, object.getString("Province"));
                ps.setString(2, object.getString("City"));
                ps.setString(3, object.getString("District"));
                ps.setString(4, object.getString("PostNumber"));
                ps.setString(5, object.getString("Address"));
                ps.setString(6, object.getString("jd"));
                ps.addBatch();
                i++;
                //添加到批次
                if (i == 5000) {
                    ps.executeBatch();
                    //提交批处理
                    connection.commit();
                    i = 0;
                }
                content = randomAccessFile.readLine();
            }
            ps.executeBatch();
            //提交批处理
            connection.commit();
            // 执行
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        randomAccessFile.close();


    }


    /**
     * 原生sql执行
     *
     * @param stuNum 待生成的数据个数
     */
    private void saveBetchSql(int stuNum, String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb" + "?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true", "root", "root");
            connection.setAutoCommit(false);
            //设置手动提交
            // 预编译sql对象,只编译一回
            PreparedStatement ps = connection.prepareStatement("insert into tb_user (name) values(?)");
            for (int i = 0; i < stuNum; i++) {
                ps.setString(1, name);
                ps.addBatch();
                //添加到批次
            }
            ps.executeBatch();
            //提交批处理
            connection.commit();
            // 执行
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void readFile() throws IOException {

        RandomAccessFile raf = new RandomAccessFile("D:\\myworkspace\\项目\\5.润心贷-个人信息接口开发\\中国邮编数据\\中国邮编数据.txt", "r");

        //随机读取，只要通过设置指针的位置即可。
        raf.seek(8 * 1);

        byte[] buf = new byte[4];
        raf.read(buf);
        String name = new String(buf);

        int age = raf.readInt();

        System.out.println(name + ":" + age);

        raf.close();


    }

    public static void writeFile() throws IOException {

        //1，创建一个随机访问文件的对象。文件不存在，则创建，存在，则不创建不覆盖。
        RandomAccessFile raf = new RandomAccessFile("D:\\myworkspace\\项目\\5.润心贷-个人信息接口开发\\中国邮编数据\\RandomAccessFileDemo.txt", "rw");

        //2，写入姓名和年龄。
//		raf.write("张三".getBytes());
//		raf.writeInt(97);//保证整数的字节原样性。
//		raf.write("李四".getBytes());
//		raf.writeInt(99);//保证整数的字节原样性。

        //3,随机写入。
        raf.seek(8);//设置指针的位置。
        raf.write("王武".getBytes());
        raf.writeInt(100);
        System.out.println(raf.getFilePointer());

        raf.close();


    }

}