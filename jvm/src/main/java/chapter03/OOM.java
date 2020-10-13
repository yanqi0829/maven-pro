package chapter03;

import java.util.ArrayList;

public class OOM {
    public static void main(String[] args) throws InterruptedException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Thread.sleep(50);
            arrayList.add(new int[1024 * 1024]);
        }
    }
}
