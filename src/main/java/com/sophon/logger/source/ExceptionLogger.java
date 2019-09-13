package com.sophon.logger.source;

import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;
import com.sophon.logger.ExceptionLoggerImpl;
import com.sophon.logger.SophonLogger;
import com.sophon.logger.SystemLoggerImpl;

/**
 * @Author tiansheng
 * @Date 2019/9/14 7:08
 * @Description TODO
 */
public class ExceptionLogger {

    private static final SophonLogger logger = new ExceptionLoggerImpl(4,
            SophonFile.getFile(ConfigVo.getInstance().getLoggerSystemPrintCaptureExceptionPath()));

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
