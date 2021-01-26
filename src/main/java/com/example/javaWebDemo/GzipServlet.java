package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 通过gzip压缩输出文本的实现
 */
@WebServlet(name = "GzipServlet", value = "/GzipServlet")
public class GzipServlet extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/GzipServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 6055d1c7-9972-4a6c-9d77-13cb7bf66f2e' \
     *   -H 'cache-control: no-cache'
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 告诉浏览器，压缩方式为gzip
        response.setHeader("Content-Encoding","gzip");

        // 向浏览器/客户端输出字节流
        response.getOutputStream().write(gzipText("尝试一下压缩"));
    }

    private byte[] gzipText(String data) throws IOException{
        //创建GZIPOutputStream对象，给予它ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);

        //GZIP对数据压缩，GZIP写入的数据是保存在byteArrayOutputStream上的
        gzipOutputStream.write(data.getBytes());

        //gzipOutputStream有缓冲，把缓冲清了，并顺便关闭流
        gzipOutputStream.close();

        // 获取压缩后的byte数组
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
