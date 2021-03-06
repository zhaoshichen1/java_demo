package filterDemo;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class InterceptResponse extends HttpServletResponseWrapper {

    HttpServletResponse resp;

    // 用Byte数组的输出流，代替原本的输出流从而进行拦截
    ByteArrayOutputStream out;
    ServletOutputStream stream;
    PrintWriter writer;

    public InterceptResponse(HttpServletResponse s){
        // 增强response
        super(s);
        resp = s;

        // 初始化新的outputStream和PrintWriter
        this.out = new ByteArrayOutputStream();
        this.stream = new InterceptOutputStream(this.out);
        this.writer = new PrintWriter(this.out); // 这里PrintWriter直接初始化就可以了
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return this.writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return this.stream;
    }

    /**
     * 将ByteArrayOutputStream上积攒的数据进行输出并返回输出
     * @return
     */
    public byte[] getBuffer() throws IOException {
        // 刷新一下数据到缓存区
        this.writer.close();
        this.out.close();

        return this.out.toByteArray();
    }
}

/**
 * 这里不是增强，而是用ByteArrayOutputStream代替原有的
 */
class InterceptOutputStream extends ServletOutputStream{

    ByteArrayOutputStream out;

    public InterceptOutputStream(ByteArrayOutputStream out){
        super();
        this.out = out;
    }

    /**
     * 重写write逻辑，改为输出到ByteArrayOutputStream上
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}

