import com.mytest.MediaService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    // 测试MyAspect的切面方法执行情况
    @Test
    public void testMyAspect(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        MediaService pgc = (MediaService)ac.getBean("pgcService");
        pgc.start();
        pgc.doSome();
        pgc.doOther();
        pgc.destroy();

        MediaService ugc = (MediaService)ac.getBean("ugcService");
        ugc.start();
        ugc.doSome();
        ugc.doOther();
        ugc.destroy();

    }
}
