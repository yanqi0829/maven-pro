package chapter03;

/**
 * -Xms10m -Xmx10m
 * 使用 jvisualvm.exe  查看堆空间
 */
public class Heap {
    public static void main(String[] args) {
        System.out.println("start....");
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
