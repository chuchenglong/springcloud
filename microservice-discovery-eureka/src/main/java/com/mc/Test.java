package com.mc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/05/02 10:46
 */
public class Test {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }
    public static void main(String[] args) throws Exception{
//        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//        System.out.println(ctl);
//        System.out.println(29);
//        System.out.println(~29);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("第1个任务，抓一只狗回来");
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("第2个任务，抓一只猫回来");
            }
        };
        Callable task1 = new Callable() {
            @Override
            public Object call() throws Exception {
                return "抓了一只狗";
            }
        };
        Callable task2 = new Callable() {
            @Override
            public Object call() throws Exception {
                return "抓了一只猫";
            }
        };
        LinkedBlockingQueue works = new LinkedBlockingQueue();
        works.put(r1);
        works.put(r2);


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 7; i++) {

            Future<String> f =  executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread t = Thread.currentThread();
                    System.out.println(t.getName()+"正在执行");
                    return t.getName();
                }
            });
//            System.out.println(f.get());
        }

        ThreadPoolExecutor t = new ThreadPoolExecutor(4,5,1,TimeUnit.SECONDS, works);
//        t.submit();
//        while (true) {
//
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    Thread t = Thread.currentThread();
//
//                    System.out.println(Thread.activeCount() + "---------" + t.getName()+"正在执行");
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
    }
}
