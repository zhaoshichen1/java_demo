import SpringDemo.SpringIOCDemo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    /**
     * 测试通过Spring容器创建对象，读取对象并使用其中的方法
     */
    @Test
    public void testCreateObject(){
        // 从classes路径开始加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("MyBeans.xml");

        // 根据我在spring配置文件中声明的key获取对象
        SpringIOCDemo instance = (SpringIOCDemo) ac.getBean("iocDemo");

        // 执行方法
        instance.doSome();
    }
}
