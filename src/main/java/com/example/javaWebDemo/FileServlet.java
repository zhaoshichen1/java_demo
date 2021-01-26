package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 通过response下发文件字节流的实现让浏览器下载图片资源
 */
@WebServlet(name = "FileServlet", value = "/FileServlet")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/测试test123.png");

        // 打开文件
        FileInputStream file = new FileInputStream(path);
        String fileName = path.substring(path.lastIndexOf("/")+1);

        // 准备向浏览器输出，文件名通过UTF-8编码否则中文会乱码
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
        ServletOutputStream out = response.getOutputStream();

        // 1024为批次进行读和写，最后一次读到多少写多少
        byte [] buf = new byte[1024];
        int len;
        while ((len = file.read(buf)) > 0){
            out.write(buf, 0, len);
        }

        // 释放资源
        out.close();
        file.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
