package SpringDemo;

/**
 * 演示使用XML的Constructor对成员属性进行赋值
 */
public class XMLConstructorDemo {
    private String name;
    private School school;

    public XMLConstructorDemo(String n, School s){
        this.name = n;
        this.school = s;
    }

    @Override
    public String toString() {
        return "XMLConstructorDemo{" +
                "name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}
