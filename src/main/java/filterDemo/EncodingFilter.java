package filterDemo;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 建立一个过滤器，用于再全局范围内对request和response的编码规则进行指定为UTF-8，避免中文乱码问题
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {

    final String utf8 = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 强转成http的response和request
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        // req和resp都进行utf8编码设置
        resp.setContentType("text/html;charset="+utf8);
        req.setCharacterEncoding(utf8);

        // 放行向下走，必须要写否则无法访问指定内容
        chain.doFilter(request, response);
    }
}
