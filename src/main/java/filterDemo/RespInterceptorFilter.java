package filterDemo;

import com.sun.tools.internal.ws.wsdl.document.Output;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 通过实现独有的OutputStream和PrintWriter，放到增强的Response对象中实现对于返回值的拦截
 */
@WebFilter(filterName = "RespInterceptorFilter", servletNames = "RespInterceptorServlet")
public class RespInterceptorFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        InterceptResponse resp = new InterceptResponse((HttpServletResponse) response);
        chain.doFilter(request, resp);

        byte[] InterceptContent = resp.getBuffer();
        System.out.println("拦截到内容:"+ new String(InterceptContent, StandardCharsets.UTF_8));

        // 拦截后通过真实的response进行正常的输出
        response.getOutputStream().write(InterceptContent);
    }
}