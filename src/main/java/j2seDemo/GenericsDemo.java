package j2seDemo;

/**
 * 泛型类Demo，在类上指定类型
 * @param <T>
 */
public class GenericsDemo<T> {
    public T testMethod(T t){
        return t;
    }

    /**
     * 泛型方法Demo，在方法上指定类型
     * @param e
     * @param <E>
     */
    public <E> void testMethod2(E e){
        System.out.println(e);
    }

}

interface GenericsInt<E> {
    public E testMethod(E e);
}


