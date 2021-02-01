package filterDemo;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过filter实现针对相同URI的get请求，可以优先通过内存缓存输出
 */
@WebFilter(filterName = "LocalCacheFilter", servletNames = "LocalCacheServlet")
public class LocalCacheFilter implements Filter {

    Map<String, byte[]> map;

    public void init(FilterConfig config) throws ServletException {
        this.map = new HashMap<>();
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        // 不是get，不走缓存逻辑
        if(!req.getMethod().equalsIgnoreCase("get")){
            System.out.println("不是GET，不走缓存逻辑");
            chain.doFilter(request, response);
            return ;
        }


        // 如果是get的，我们通过URI查找缓存数据
        String query = req.getRequestURL()+"?"+req.getQueryString();
        byte[] cache = map.get(query);

        if (cache != null){
            resp.getOutputStream().write(cache);
            System.out.println("LocalCache is working");
            return ;
        }

        // 缓存中没有时我们回源并且存到map中
        InterceptResponse myResp = new InterceptResponse(resp);
        chain.doFilter(request, myResp);
        cache = myResp.getBuffer();
        map.put(query, cache);
        resp.getOutputStream().write(cache);
    }
}
