package listenerDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SessionListenerServlet", value = "/SessionListenerServlet")
public class SessionBindingServlet extends HttpServlet {

    /**
     * 获取参数name并且生成对象，加入到session中
     *
     * curl -X GET \
     *   'http://localhost:8080/javaWebDemo_war_exploded/SessionListenerServlet?name=test444' \
     *   -H 'Postman-Token: 0a681fef-781f-43b6-a51f-dec33c9e779e' \
     *   -H 'cache-control: no-cache'
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null){
            response.getWriter().write("Bad Request, Give name");
            return;
        }
        request.getSession().setAttribute("user", new SessionBindingListener(name));
    }

    /**
     * 移除上面get请求添加的对象，测试解绑监听
     *
     * curl -X POST \
     *   'http://localhost:8080/javaWebDemo_war_exploded/SessionListenerServlet?name=test2222' \
     *   -H 'Postman-Token: 2fa954b0-d835-49b8-83a1-afecf3a3f3ac' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
    }
}
