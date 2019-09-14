package com.sophon.component.exception;

import com.sophon.component.SophonInit;
import com.sophon.logger.source.ExceptionLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String className = "--- ".concat(e.getStackTrace()[0].getClassName())
                .concat(": ")
                .concat(e.getStackTrace()[0].getMethodName())
                .concat(" ---");
        String datetime = "--- ".concat(sdf.format(new Date())).concat(" ---");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionInfo = sw.toString();
        StringBuffer buffer = new StringBuffer();
        buffer.append(className)
                .append("\n")
                .append(datetime)
                .append("\n\n     ")
                .append(exceptionInfo);
        String endExceptionInfo = "\n\n".concat(buffer.toString());
        ExceptionLogger.error(endExceptionInfo);
    }

    @Override
    public void init() {
        currentGroup = Thread.currentThread().getThreadGroup();
        // 当前活动线程
        int activeCount = currentGroup.activeCount();
        Thread[] group = new Thread[activeCount];
        currentGroup.enumerate(group);
        for (Thread thread : group) {
            thread.setUncaughtExceptionHandler(new UncheckedExceptionHandler());
        }
    }
}
