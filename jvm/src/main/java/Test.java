public class Test {
    /**
     * 使用jps  打印当前程序中的进程
     * @param args
     */
    public static void main(String[] args) {
        int i=0;
        System.out.println(i);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
