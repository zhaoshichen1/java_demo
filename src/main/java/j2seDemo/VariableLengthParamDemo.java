package j2seDemo;

import java.util.Arrays;
import java.util.List;

/**
 * 可变长参数的Demo
 */
public class VariableLengthParamDemo {
    public static void testParams(int... a){
        for(int x:a){
            System.out.println(x);
        }
    }

    public static void main(String[] args){
        List<String> l = Arrays.asList("a","b","c");
        System.out.println(l);

        testParams(1,2,3,4,5);
    }
}
