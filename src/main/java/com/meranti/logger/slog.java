package com.meranti.logger;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 1:31
 * @Description TODO
 */
public class slog {

    private static final String formatString = "\\{\\}";
    private static final SophonLogger logger = new SophonLoggerImpl(4);

    public static void info(String v){
        logger.info(v);
    }

    public static void info(String v,Object... args){
        logger.info(format(v,args));
    }

    public static void debug(String v){
        logger.debug(v);
    }

    public static void debug(String v,Object... args){
        logger.debug(format(v,args));
    }

    public static void error(String v){
        logger.error(v);
    }

    public static void error(String v,Object... args){
        logger.error(format(v,args));
    }

    public static void warn(String v){
        logger.warn(v);
    }

    public static void warn(String v,Object... args){
        logger.warn(format(v,args));
    }

    public static void exception(Throwable throwable){
        logger.exception(throwable);
    }

    /**
     * 将字符串格式化
     * @param v
     * @param args
     * @return
     */
    private static String format(String v,Object... args) {
        v = v.replaceAll(formatString,"%s");
        v = String.format(v,args);
        return v;
    }

}
