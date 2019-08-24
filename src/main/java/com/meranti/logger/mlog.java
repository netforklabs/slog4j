package com.meranti.logger;

/**
 * @Author Mr.luo
 * @Date 2019/8/25 1:31
 * @Description TODO
 */
public final class mlog {

    private static final MerantiLogger logger = new MLoggerImpl(3);

    public static void info(String v){
        logger.info(v);
    }

}
