package SpringIOCDemo;

/**
 * 演示使用Autowire ByName对引用对象进行赋值
 */
public class XMLAutowireByName {
    private String name;

    // 按照type自动引入
    private School myPrimarySchool;

    public void setName(String name) {
        this.name = name;
    }

    public void setMyPrimarySchool(School myPrimarySchool) {
        this.myPrimarySchool = myPrimarySchool;
    }

    @Override
    public String toString() {
        return "XMLAutowireByName{" +
                "name='" + name + '\'' +
                ", myPrimarySchool=" + myPrimarySchool +
                '}';
    }
}
