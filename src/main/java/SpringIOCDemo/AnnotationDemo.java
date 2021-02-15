package SpringIOCDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 演示使用注解，创建对象
 */
// 和Bean一样，创建一个名为atDemo的单例对象
@Component("atDemo")
public class AnnotationDemo {

    // 通过Value注解赋值，无需setter方法，比较简单
    @Value("23")
    private int age;

    @Value("测试名称")
    private String name;

    // 自动引入，默认是byType，需要byName的话增加Qualifier即可
    @Autowired
    @Qualifier("animal")
    private Animal animal;

    // 自动引入的另一种写法（JDK提供），默认逻辑是优先byName，再byType
    @Resource(name = "car2")
    private Car car;

    @Override
    public String toString() {
        return "AnnotationDemo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", animal=" + animal +
                ", car=" + car +
                '}';
    }
}

// 不给名字，默认是animal
@Component
class Animal {
    @Value("小动物")
    private String name;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

@Component("car2")
class Car {
    @Value("东风雪铁龙")
    private String brand;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}