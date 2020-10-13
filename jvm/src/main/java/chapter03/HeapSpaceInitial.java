package chapter03;

public class HeapSpaceInitial {
    public static void main(String[] args) {
        //观测默认 堆起始大小  最大堆空间大小
        long initialMemory =Runtime.getRuntime().totalMemory()/1024/1024;
        long maxMemory =Runtime.getRuntime().maxMemory()/1024/1024;
        System.out.println("-Xms: "+initialMemory+"M");
        System.out.println("-Xmx: "+maxMemory+"M");
        //自定义大小  -Xms600m -Xmx600m   而打印输出 为 575M   因为Survivor只能有一个在使用中 只计算了其中一个
        // -XX:+PrintGCDetails  打印gc过程中的细节(执行完)
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
