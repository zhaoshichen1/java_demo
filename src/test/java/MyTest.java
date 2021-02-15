import SpringDemo.*;
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
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 根据我在spring配置文件中声明的key获取对象
        SpringHelloWorld instance = (SpringHelloWorld) ac.getBean("iocDemo");

        // 执行方法
        instance.doSome();
    }

    /**
     * 测试通过XML中的property来调用bean的setter方法赋值
     */
    @Test
    public void testXMLSet(){
        // 从classes路径开始加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("SpringXMLDemo/XMLSetterDemo.xml");

        XMLSetterDemo xsd = (XMLSetterDemo) ac.getBean("demo");

        // 应该正常输出x
        System.out.println(xsd); // XMLSetterDemo{age=123, name='测试名称', school=School{location='上海'}}
    }

    /**
     * 测试通过XML中的constructor-ag赋值
     */
    @Test
    public void testXMLConstructor(){
        // 从classes路径开始加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("SpringXMLDemo/XMLConstructorDemo.xml");

        XMLConstructorDemo xsd = (XMLConstructorDemo) ac.getBean("demo");

        // 应该正常输出
        System.out.println(xsd); // XMLConstructorDemo{name='测试名称', school=School{location='苏州'}}
    }

    /**
     * 测试通过XML中的ByName自动注入
     */
    @Test
    public void testXMLAutowireByName(){
        // 从classes路径开始加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("SpringXMLDemo/XMLAutowire.xml");

        // 获取autowire=byName的实例
        XMLAutowireByName xsd = (XMLAutowireByName) ac.getBean("demoName");
        System.out.println(xsd); // XMLAutowireByName{name='testName', myPrimarySchool=School{location='海南'}}

        // 获取autowire=byType的实例
        XMLAutowireByType xdbt = (XMLAutowireByType) ac.getBean("demoType");
        System.out.println(xdbt);
    }

    @Test
    public void testAnnotationDemo(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("SpringAnnotationDemo/applicationContext.xml");
        AnnotationDemo atd = (AnnotationDemo) ac.getBean("atDemo");
        System.out.println(atd);
    }

}
