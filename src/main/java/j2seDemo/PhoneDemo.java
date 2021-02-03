package j2seDemo;

class Phone{
    String Brand;
    int price;

    Phone(String a, int b){
        this.Brand = a;
        this.price = b;
    }
}

public class PhoneDemo {
    final int test1 = 123;
    public static void main(String[] args){
        Phone p1 = new Phone("Apple",5000);
        Phone p2 = new Phone("Huawei",4000);

        System.out.println(p1.price);
        System.out.println(p2.price);
    }

    public int test(){
        int a = 5 + 10;      //验证直接相加在编译阶段已合并完结果
        int b = a + 3;        //探究变量与常量的相加过程
        return b;
    }
}
