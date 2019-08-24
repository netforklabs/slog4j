package com.meranti.logger;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 0:08
 * @Description sophon-logger
 */
public interface SophonLogger {

    /**
     * info
     * ------------
     * 以下方法都和info一样,没什么特别的,就不注释了
     * warn()  警告
     * error() 错误
     * debug() 调试
     */
    void info();

    /**
     * 输出
     * @param s
     */
    void info(String s);

    /**
     * 格式化单个字符
     * @param s
     * @param var1
     */
    void info(String s, Object var1);

    /**
     * 格式化两个字符
     * @param s
     * @param var1
     * @param var2
     */
    void info(String s, Object var1, Object var2);

    /**
     * 格式化多个字符
     * 支持字符串格式化,例如:
     *      log.info("今天%s去上学","小明");
     *      输出: 今天小明去上学
     * 这个功能用过jdbc的都知道
     * @param s 原字符串
     * @param args 携带参数
     */
    void info(String s, Object... args);

    /**
     * debug()
     */
    void debug();
    void debug(String s);
    void debug(String s, Object var1);
    void debug(String s, Object... args);
    void debug(String s, Object var1, Object var2);

    /**
     * error()
     */
    void error();
    void error(String s);
    void error(String s, Object var1);
    void error(String s, Object... args);
    void error(String s, Object var1, Object var2);

    /**
     * warn()
     */
    void warn();
    void warn(String s);
    void warn(String s, Object var1);
    void warn(String s, Object... args);
    void warn(String s, Object var1, Object var2);

    /**
     * 异常改为输出,不会对程序运行造成影响
     * @param e 异常信息
     */
    void exception(Throwable e);

}
