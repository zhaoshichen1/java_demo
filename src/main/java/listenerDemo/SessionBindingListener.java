package listenerDemo;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Serializable;

/**
 * 实现一个普通类，同时实现了HttpSessionActivationListener和HttpSessionBindingListener
 * 可以监听当前类被加入session和移除出session的事件
 * 可以监听当前对象所在的session被钝化/活化（在内存和硬盘之间切换）
 */
@WebListener
public class SessionBindingListener implements HttpSessionActivationListener, Serializable, HttpSessionBindingListener {

    String name;
    public SessionBindingListener(){
        this.name = "default name";
    }

    public SessionBindingListener(String n) {
        this.name = n;
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        HttpSession ss = se.getSession();
        printSession("钝化",ss);
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        HttpSession ss = se.getSession();
        printSession("激活",ss);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession ss = event.getSession();
        printSession("绑定",ss);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession ss = event.getSession();
        printSession("解绑",ss);
    }

    public void printSession(String step, HttpSession ss){
        System.out.println("Step:"+step+",Name:"+this.name+",Session:"+ss.getId()+",Created:"+ss.getCreationTime());
    }
}
