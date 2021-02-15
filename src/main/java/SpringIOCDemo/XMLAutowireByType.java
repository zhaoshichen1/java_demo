package SpringIOCDemo;

/**
 * 演示使用Autowire ByType对引用对象进行赋值
 */
public class XMLAutowireByType {
    private String name;

    // 按照type自动引入，所以名字就无所谓了
    private School whateverName;

    public void setName(String name) {
        this.name = name;
    }

    public void setWhateverName(School w) {
        this.whateverName = w;
    }

    @Override
    public String toString() {
        return "XMLAutowireByType=" +
                "name='" + name + '\'' +
                ", myPrimarySchool=" + whateverName +
                '}';
    }
}
