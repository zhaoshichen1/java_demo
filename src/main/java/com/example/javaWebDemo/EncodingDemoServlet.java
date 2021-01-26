package com.example.javaWebDemo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "encodingServlet", value = "/encoding-servlet", loadOnStartup=1)

/**
 * 主要演示从request中获取中文参数以及从response输出中文时所遇到的编码问题和解决方式，
 */
public class EncodingDemoServlet extends HttpServlet {

    private String UTF8_Encoding = "UTF-8";

    public void init() { }

    /**
     *      curl -X POST \
     *      http://localhost:8080/javaWebDemo_war_exploded/encoding-servlet \
     *      -H 'Content-Type: application/x-www-form-urlencoded' \
     *      -H 'Postman-Token: ba8d5da4-dacf-4a0a-aace-635b329b53f1' \
     *      -H 'cache-control: no-cache' \
     *      -d 'test=%E4%B8%AD%E6%96%87%E6%B5%8B%E8%AF%95&undefined='
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 从request中获取中文，因为Tomcat默认是将UTF-8编码的中文再用ISO 8859-1解码;
        // 如果需要解成中文，则指定request中HTTP Body的编码格式为UTF-8即可
        request.setCharacterEncoding(UTF8_Encoding);
        String res = request.getParameter("test");
        System.out.println(request.getParameter("test"));

        // response输出中文时也需要设置编码格式，否则默认也是走ISO 8859-1编码
        this.writeRes(response, res);
    }

//    // Tomcat<8时，由于GET的参数在uri里，不在HTTP Body中，所以解决编码问题的方式和doPost不一样（尽量用POST提交数据）
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        // 此时得到的数据已经是被ISO 8859-1编码后的字符串了，如果有中文的话这个是乱码
//        String test = request.getParameter("test");
//        System.out.println("默认解码后："+test);
//
//        // 反查ISO编码方式，得到未被Tomcat解码过的数据
//        System.out.println(test.getBytes());
//        byte[] bytes = test.getBytes(ISO_Encoding);
//
//        // 重新进行UTF-8解码后得到中文字符串
//        String value = new String(bytes, "UTF-8");
//        System.out.println("重新解码后："+value);
//
//        this.writeRes(response, value);
//    }


    /**
     * curl -X GET \
     *   'http://localhost:8080/javaWebDemo_war_exploded/encoding-servlet?test=321%E5%95%8A' \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: a0e2c375-f065-462e-8c11-5cbefd1b543e' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // >=Tomcat 8.0后，URI的默认编码为UTF-8，无需手动设置或者转换
        this.writeRes(response, request.getParameter("test"));
    }

    public void writeRes(HttpServletResponse resp, String data) throws IOException{
        if(data == null){
            data = "request error";
        }
        resp.setCharacterEncoding(UTF8_Encoding);
        resp.getWriter().write(data);
    }

    public void destroy() { }
}