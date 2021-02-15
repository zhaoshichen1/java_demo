package SpringIOCDemo;

/**
 * 演示使用XML的Setter对成员属性进行赋值
 */
public class XMLSetterDemo {
    // 基础类型
    private int age;
    private String name;
    private School school;

    // 三个getter方法
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    // 演示Spring只是调用setXXX方法
    public void setWhatever(int whatever){
        System.out.println("Spring是无脑调用setXXX的，打印："+whatever);
    }

    @Override
    public String toString() {
        return "XMLSetterDemo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}

class School{
    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "School{" +
                "location='" + location + '\'' +
                '}';
    }
}

class PrimarySchool extends School{

}