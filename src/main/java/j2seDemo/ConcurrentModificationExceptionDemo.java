package j2seDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 演示使用Iterator遍历时修改列表所导致的并发修改错误
 */
public class ConcurrentModificationExceptionDemo {
    public static void main(String[] args){
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);

        // reproduceException(new ArrayList(l));
        iteratorMethod(new ArrayList(l));
        listMethod(new ArrayList(l));
    }

    /**
     * 使用Iterator遍历的过程中修改列表，引起异常
     * @param a
     */
//    public static void reproduceException(List<Integer> a){
//        Iterator<Integer> it = a.iterator();
//        while (it.hasNext()){
//            if(it.next() == 1){
//                a.add(3);
//            }
//        }
//    }

    public static void iteratorMethod(List<Integer> a){
        ListIterator<Integer> it = a.listIterator(); // 需要用listIterator,不然没有add方法
        while (it.hasNext()){
            if(it.next() == 1){
                it.add(33);
            }
        }
        System.out.println(a); // 1，33，2；插在了1的后面
    }

    public static void listMethod(List<Integer> a){
        for(int i=0;i<a.size();i++){
            if(a.get(i) == 1){
                a.add(33);
            }
        }
        System.out.println(a); // 1，2，33；插在了最后面
    }
}
