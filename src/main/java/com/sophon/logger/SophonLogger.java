package com.sophon.logger;

/**
 * @Author tiansheng
 * @Date 2019/8/25 0:08
 * @Description sophon-logger
 */
public interface SophonLogger {

    /**
     * 枚举 日志级别
     */
    enum Level {INFO, DEBUG, ERROR, WARN}

    /**
     * info: 传入一个Object对象
     */
    void info(Object v);

    /**
     * info: 传入一个String对象
     *
     * @param v
     */
    void info(String v);

    /**
     * info: 字符串格式化输出
     *
     * @param v    String格式化字符串
     * @param args 参数
     */
    void info(String v, Object... args);

    /**
     * info: 传入一个String和一个线程
     *
     * @param v String字符串
     * @param t 线程
     */
    void info(String v, Thread t);

    /**
     * 传入格式化String字符串和线程以及格式化参数
     *
     * @param v    格式化字符串
     * @param t    线程
     * @param args 参数
     */
    void info(String v, Thread t, Object... args);

    /**
     * debug
     */
    void debug(Object v);

    void debug(String v);

    void debug(String v, Object... args);

    void debug(String v, Thread t);

    void debug(String v, Thread t, Object... args);

    /**
     * error
     */
    void error(Object v);

    void error(String v);

    void error(String v, Object... args);

    void error(String v, Thread t);

    void error(String v, Thread t, Object... args);

    /**
     * warn
     */
    void warn(Object v);

    void warn(String v);

    void warn(String v, Object... args);

    void warn(String v, Thread t);

    void warn(String v, Thread t, Object... args);

    /**
     * 生成日志前缀,例如:
     * 2019-08-24 13:48:44 java.lang.Object | toString:235 - [INFO]: toString error
     *
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    String prefixGenerate(Level level);

    /**
     * 前缀生成,传入指定线程
     *
     * @param level
     * @param t
     * @return
     */
    String prefixGenerate(Level level, Thread t);
}
