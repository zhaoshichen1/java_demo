package listenerDemo;

/** *
 * 事件源Person
 *
 * 事件源要提供方法注册监听器(即在事件源上关联监听器对象) */
class Person {
    private PersonListener l;

    public Person(){}
    public Person(PersonListener listener){
        this.l = listener;
    }

    public void eat(){
        /**
         * eat的逻辑代码
         */
        // 主动调用Listener
        if (this.l != null){
            this.l.onEat(new PersonEvent(this));
        }
    }

    public void sleep(){
        /**
         * sleep的逻辑代码
         */
        // 主动调用Listener
        if (this.l != null){
            this.l.onSleep(new PersonEvent(this));
        }
    }

}

/**
 * Event类，定义事件需要包含的信息
 */
class PersonEvent {
    Person p;
    public PersonEvent(){

    }
    public PersonEvent(Person p){
        this.p = p;
    }
}

/**
 * 定义PersonListener的实现范式，希望监听时implements这个接口即可
 */
interface PersonListener {
    public void onEat(PersonEvent event);
    public void onSleep(PersonEvent event);
}

class PersonListenerDemo implements PersonListener{

    @Override
    public void onEat(PersonEvent event) {
        System.out.println("Person eats");
    }

    @Override
    public void onSleep(PersonEvent event) {
        System.out.println("Person sleeps");
    }
}

public class JavaListenerDemo {
    public static void main(String[] args){
        // 注册监听器
        Person p = new Person(new PersonListenerDemo());
        p.eat();
        p.sleep();
    }
}