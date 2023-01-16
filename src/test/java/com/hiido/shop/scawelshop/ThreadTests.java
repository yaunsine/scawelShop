package com.hiido.shop.scawelshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @description: TODO 多线程测试类
 * @author YSLin
 * @date 2023/1/12 17:24
 */
public class ThreadTests {

    private static final Logger logger = LoggerFactory.getLogger(ThreadTests.class);

    public volatile static long currentTimeMillis;

    public static void thread1() throws InterruptedException {
        logger.info("thread1抢到时间片");
        Thread.sleep(1000);
        logger.info("thread1再次抢到时间片，thread1执行完毕，耗时{}s", (System.currentTimeMillis()-currentTimeMillis)/1000.0);
    }

    public static void thread2() throws InterruptedException {
        logger.info("thread2抢到时间片");
        Thread.sleep(2000);
        logger.info("thread2再次抢到时间片，thread2执行完毕，耗时{}s", (System.currentTimeMillis()-currentTimeMillis)/1000.0);
    }

    public static void thread3() throws InterruptedException {
        logger.info("thread3抢到时间片");
        Thread.sleep(3000);
        logger.info("thread3再次抢到时间片");
        logger.info("thread3执行完毕，耗时{}s", (System.currentTimeMillis()-currentTimeMillis)/1000.0);
    }

    public static void thread4() throws InterruptedException {
        logger.info("thread4抢到时间片");
        Thread.sleep(6000);
        logger.info("thread4再次抢到时间片");
        logger.info("thread4执行完毕，耗时{}s", (System.currentTimeMillis()-currentTimeMillis)/1000.0);
    }

    public static void main(String[] args) throws InterruptedException {
        currentTimeMillis = System.currentTimeMillis();
        logger.info("当前时间为: {}", System.currentTimeMillis());
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread4();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        logger.info("总耗时: {}秒", (System.currentTimeMillis() - currentTimeMillis)/1000.0);
    }
}
