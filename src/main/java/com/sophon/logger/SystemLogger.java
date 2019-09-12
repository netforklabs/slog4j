package com.sophon.logger;

/**
 * @Author tiansheng
 * @Date 2019/9/13 3:49
 * @Description TODO
 */
public class SystemLogger {

    private static final String formatString = "\\{\\}";
    private static final SophonLogger logger = new SystemLoggerImpl(4);

    public static void info(String v){
        logger.info(v);
    }

    public static void info(String v,Object... args){
        logger.info(v,args);
    }

    public static void debug(String v){
        logger.debug(v);
    }

    public static void debug(String v,Object... args){
        logger.debug(v,args);
    }

    public static void error(String v){
        logger.error(v);
    }

    public static void error(String v,Object... args){
        logger.error(v,args);
    }

    public static void warn(String v){
        logger.warn(v);
    }

    public static void warn(String v,Object... args){
        logger.warn(v,args);
    }

    public static void exception(String s,Throwable throwable){
        logger.exception(s,throwable);
    }

}
