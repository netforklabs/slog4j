package com.sophon.component.exception;

import com.sophon.component.SophonInit;
import com.sophon.component.hot.SophonListener;
import com.sophon.logger.source.ExceptionLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * 全局异常捕获器/处理器
 *
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/14 3:58
 * @since 1.8
 */
public class UncheckedExceptionHandler implements Thread.UncaughtExceptionHandler, SophonInit {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 被监听的异常方法
     */
    private static Hashtable<String, SophonListener> ERROR = new Hashtable();

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String classname = e.getStackTrace()[0].getClassName();
        String methodname = e.getStackTrace()[0].getMethodName();
        // 如果发生异常，先判断是否符合ERROR table中的key，如果符合则交给异常程序处理
        SophonListener listener = ERROR.get(classname.concat(".").concat(methodname));
        if(listener != null){
            try {
                Class<?> target = Class.forName(classname);
                Method method = target.getDeclaredMethod(methodname);
                listener.error(target,method,e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return;
        }
        String className = "--- ".concat(classname)
                .concat(": ")
                .concat(methodname)
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

    /**
     * 注册异常监听器
     *
     * @param classpath      类路径
     * @param sophonListener 处理类
     */
    public static void registerListener(String classpath, SophonListener sophonListener) {
        ERROR.put(classpath, sophonListener);
    }

}
