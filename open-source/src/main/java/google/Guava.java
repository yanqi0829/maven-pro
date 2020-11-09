package google;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Guava {
    //创建令牌桶，设定吞吐率是指每秒多少令牌，此处每秒创建300
    static RateLimiter rateLimiter = RateLimiter.create(300);
//    static int DEAL_MESSAGE = 300; //处理300条短信


    /**
     * 令牌桶
     */
    public static void main(String[] args) throws InterruptedException {

        //从RateLimiter获取指定令牌，该方法会被阻塞直到获取到请求
        System.out.println(rateLimiter.acquire(1));//返回0.0表示获取令牌成功，否则阻塞等待获取
        System.out.println(rateLimiter.getRate());//即创建时的令牌生成速率
        NumMessage num = new NumMessage();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            executorService.execute(new Send(rateLimiter, num));
        }
        executorService.shutdown();
        while (true) {
            try {
                if (executorService.isTerminated()) {
                    break;
                }
            } catch (Exception e) {
            }
        }

       /* for (int i = 0; i < 300; i++) {
            double acquire = rateLimiter.acquire();
            if (acquire == 0) {
                System.out.println(i + "get it");
                Thread.sleep(110);//模拟每次短信调用消耗110ms
            } else {
                System.out.println(i + "wait  " + acquire);
            }

        }*/
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 + "s");
    }
}

/**
 *
 */
class Send implements Runnable {
    RateLimiter rateLimiter;
    NumMessage num;

    public Send(RateLimiter rateLimiter, NumMessage num) {
        this.rateLimiter = rateLimiter;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            int count = num.getNum();
            if (count == 0) {return;}
            double acquire = rateLimiter.acquire();
            if (acquire == 0) {
                System.out.println(Thread.currentThread().getName() + "....." + count + "get it");
                try {
                    Thread.sleep(110);//模拟每次短信调用消耗110ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(110);//模拟每次短信调用消耗110ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "....." + count + "wait  " + acquire);
            }

        }
    }


}

class NumMessage{ //短信条数
    int num=600;
    public synchronized int getNum() {
        if (num > 0) {
            return num--;
        }
        return 0;
    }
}
