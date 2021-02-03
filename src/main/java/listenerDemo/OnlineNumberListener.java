package listenerDemo;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 通过Listener监听Session创建和销毁
 * 利用ServletContext的全局性存储num代表同时的在线人数
 */
@WebListener
public class OnlineNumberListener implements HttpSessionListener {

    // 新的session创建，在线人数+1
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession ss = se.getSession();
        ServletContext ctx = ss.getServletContext();
        calculate(ctx, 1);
        System.out.println(ss.getId()+"创建了");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession ss = se.getSession();
        ServletContext ctx = ss.getServletContext();
        calculate(ctx, -1);
        System.out.println(ss.getId()+"销毁了");
    }

    public void calculate(ServletContext ctx, int delta){
        Object o = ctx.getAttribute("num");
        int num = 0;
        if (o != null){
            num = (int)o;
        }
        num += delta;
        ctx.setAttribute("num", num);
    }
}
