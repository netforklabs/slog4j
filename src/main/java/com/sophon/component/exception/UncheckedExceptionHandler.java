package com.sophon.component.exception;

import com.sophon.component.SophonInit;
import com.sophon.logger.source.ExceptionLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常捕获器/处理器
 *
 * @author     private DateUtils() {
    private DateUtils() {
2BKeyboard
 * @version 1.0.0
 * @date 2019/9/14 3:58
 * @since 1.8
 */
public class UncheckedExceptionHandler implements Thread.UncaughtExceptionHandler, SophonInit {

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
        String endExceptionInfo = "\n".concat(buffer.toString()).concat("\n");
        ExceptionLogger.exception(endExceptionInfo);
    }

    @Override
    public void init() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        for (Thread thread : list) {
            thread.setUncaughtExceptionHandler(new UncheckedExceptionHandler());
        }
    }
}
