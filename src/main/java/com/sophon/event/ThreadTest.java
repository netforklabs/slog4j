package com.sophon.event;

/**
 * @Author tiansheng
 * @Date 2019/9/8 23:53
 * @Description TODO
 */
public class ThreadTest extends Thread {

    public ThreadTest(){
        super.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
                System.out.println("--------------------> test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
