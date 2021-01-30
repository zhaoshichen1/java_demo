package filterDemo;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 通过增强request 实现一个参数中的敏感词替换为***的逻辑
 * 只针对 SensitiveWordServlet 进行过滤测试
 */
@WebFilter(filterName = "SensitiveWordFilter", servletNames = "SensitiveWordServlet")
public class SensitiveWordFilter implements Filter {
    private ArrayList<String> words;

    public void init(FilterConfig config) throws ServletException {
        this.words = new ArrayList<>();
        this.words.add("敏感词");
        this.words.add("a1");
    }

    public void destroy() {
    }

    /**
     * 替换原有的ServletRequest，带上了增强request中的getParameter的逻辑
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        SensRequest req = new SensRequest((HttpServletRequest) request, this.words);
        chain.doFilter(req, response);
    }
}

/**
 * 定义一个增强request，Java提供了HttpServletRequestWrapper的类（相当于一个HttpServletRequest的标准实现）
 * 通过这个类，我们避免了自己去实现HttpServletRequest接口，而是可以通过继承+重写修改HttpServletRequest的部分方法
 */
class SensRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    private ArrayList<String> words;

    /**
     * 通过request初始化这个增量类，同时放入敏感词列表
     * @param req
     * @param w
     */
    public SensRequest(HttpServletRequest req, ArrayList<String> w){
        super(req);
        this.request = req;
        this.words = w;
    }

    /**
     * 继承后重写getParamter方法，增加部分业务逻辑
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name){
        String value = this.request.getParameter(name);
        if (value == null){
            return null;
        }
        for (String word: this.words){
            if (value.indexOf(word) < 0){ // 未命中
                continue;
            }
            // 替换成对应长度的***
            value = value.replace(word, replacement(word.length()));
        }
        return value;
    }

    String replacement(int length){
        String result = "";
        for (int i=0;i<length;i++){
            result += "*";
        }
        return result;
    }
}
