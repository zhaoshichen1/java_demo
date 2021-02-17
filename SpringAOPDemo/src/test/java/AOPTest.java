import com.mytest.MediaService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    // 测试MyAspect的切面方法执行情况
    @Test
    public void testMyAspect(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        MediaService ugc = (MediaService)ac.getBean("ugcService");
        try{
            ugc.start();
        } catch (Exception e){
            System.out.println("Got Exception!!!!!");
        }
        System.out.println("doOther的返回值："+ugc.doOther());
    }

    @Test
    public void testException(){
        try {
            int a = 3/0;
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("测试运行时异常后面执行不执行");
    }
}
