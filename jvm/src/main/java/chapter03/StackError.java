package chapter03;

/**
 * 虚拟机栈异常
 */
public class StackError {
    private static  int count=1;
    public static void main(String[] args) {
        count++;
        System.out.println(count);
        main(args); //Exception in thread "main" java.lang.StackOverflowError
    }
    //设置栈大小  -Xss
    /**
     * 默认 count: 9742 次
     * 设置 -Xss 256k   count:2294次
     */
}
