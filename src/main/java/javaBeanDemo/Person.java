package javaBeanDemo;

public class Person {
    private String name;
    private int age;

    public Person(){

    }

    // getter和setter操作内部属性
    public int getAge(){
        return this.age;
    }
    public void setAge(int a){
        this.age = a;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
