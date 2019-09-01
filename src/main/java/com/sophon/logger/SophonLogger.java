package com.sophon.logger;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 0:08
 * @Description sophon-logger
 */
public interface SophonLogger {

    /**
     * info
     */
    void info(String v);

    /**
     * debug()
     */
    void debug(String v);

    /**
     * error()
     */
    void error(String v);

    /**
     * warn()
     */
    void warn(String v);

    /**
     * 异常改为输出,不会对程序运行造成影响
     * @param e 异常信息
     */
    void exception(String s,Throwable e);

    /**
     * 生成日志前缀,例如:
     * 2019-08-24 13:48:44 java.lang.Object | toString:235 - [INFO]: toString error
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    String prefixGenerate(String level);

}
