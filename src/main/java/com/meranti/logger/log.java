package com.meranti.logger;

/**
 *
 * @Author Mr.luo
 * @Date 2019/8/24 23:56
 */
public final class log {

    public static void info(String logger) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        System.out.println(className + " " + methodName + "[" + lineNumber + "]" + logger);
    }

}
