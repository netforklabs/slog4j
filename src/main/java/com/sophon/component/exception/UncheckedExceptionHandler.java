package com.sophon.component.exception;

import com.sophon.component.SophonInit;
import com.sophon.logger.SystemLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/14 3:58
 * @Description 全局异常捕获器
 */
public class UncheckedExceptionHandler implements Thread.UncaughtExceptionHandler, SophonInit {

    /**
     * 线程对象
     */
    private static ThreadGroup currentGroup;

    static {
        currentGroup = Thread.currentThread().getThreadGroup();
        // 当前活动线程
        int activeCount = currentGroup.activeCount();
        Thread[] group = new Thread[activeCount];
        currentGroup.enumerate(group);
        for(Thread thread : group){
            thread.setUncaughtExceptionHandler(new UncheckedExceptionHandler());
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName()+" : "+e.getMessage());
    }

    @Override
    public void init() {
        // 不做任何操作,存在的目的只是为了调用static静态块
    }
}
