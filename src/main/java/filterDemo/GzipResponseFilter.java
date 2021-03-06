package filterDemo;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * 通过加强Response和OutputStream实现对于返回值的统一Gzip的压缩
 */
@WebFilter(filterName = "GzipResponseFilter", servletNames = "GzipResponseServlet")
public class GzipResponseFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        MyResponse r = new MyResponse((HttpServletResponse) response);
        chain.doFilter(request, r);
    }
}

/**
 *
 */
class MyResponse extends HttpServletResponseWrapper {
    ServletOutputStream stream;

    public MyResponse(HttpServletResponse s) throws IOException {
        super(s);
        s.setHeader("Content-Encoding","gzip"); // 设置Gzip相关的header头
        this.stream = new MyOutputStream(s.getOutputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return this.stream;
    }
}

class MyOutputStream extends ServletOutputStream{

    ServletOutputStream ss ;
    public MyOutputStream (ServletOutputStream s){
        super();
        ss = s;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
    }

    @Override
    public void write(int b) throws IOException {
    }

    /**
     * 增加gzip的逻辑
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        this.ss.write(new Gzip().GzipBytes(b));
    }
}

