package com.sophon.component.exception;

import com.sophon.component.SophonInit;
import com.sophon.logger.source.ExceptionLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/14 3:58
 * @Description 全局异常捕获器
 */
public class UncheckedExceptionHandler implements Thread.UncaughtExceptionHandler, SophonInit {

    /**
     * 线程对象
     */
    private ThreadGroup currentGroup;

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        ExceptionLogger.error("捕获到线程{}抛出的异常:,异常信息为:",t.getName(),e.getMessage());

    }

    @Override
    public void init() {
        currentGroup = Thread.currentThread().getThreadGroup();
        // 当前活动线程
        int activeCount = currentGroup.activeCount();
        Thread[] group = new Thread[activeCount];
        currentGroup.enumerate(group);
        for(Thread thread : group){
            thread.setUncaughtExceptionHandler(new UncheckedExceptionHandler());
        }
    }
}
