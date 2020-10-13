package chapter02;

public class Init {
    private static int num = 1;

    static {
        num = 2;
        number = 22;
//        System.out.println(number);//非法的前项引用
    }

    private static int number = 10; //linking-prepare: number=0 -> initial: 22->10

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }
}
