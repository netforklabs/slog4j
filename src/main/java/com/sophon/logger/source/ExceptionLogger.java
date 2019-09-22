package com.sophon.logger.source;

import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;
import com.sophon.logger.SophonLogger;
import com.sophon.logger.SystemLoggerImpl;

/**
 * @Author tiansheng
 * @Date 2019/9/14 7:08
 * @Description TODO
 */
public final class ExceptionLogger {

    private static final SystemLoggerImpl logger = new SystemLoggerImpl(5,
            SophonFile.getFile(ConfigVo.getInstance().getLoggerSystemPrintCaptureExceptionPath()));

    public static void info(String v) {
        logger.info(v);
    }

    public static void info(String v, Object... args) {
        logger.info(v, args);
    }

    public static void info(Object v) {
        logger.info(v);
    }

    public static void info(String v, Thread t) {
        logger.info(v, t);
    }

    public static void info(String v, Thread t, Object... args) {
        logger.info(v, t, args);
    }

    public static void debug(Object v) {
        logger.debug(v);
    }

    public static void debug(String v) {
        logger.debug(v);
    }

    public static void debug(String v, Object... args) {
        logger.debug(v, args);
    }

    public static void debug(String v, Thread t) {
        logger.debug(v, t);
    }

    public static void debug(String v, Thread t, Object... args) {
        logger.debug(v, t, args);
    }

    public static void error(Object v) {
        logger.error(v);
    }

    public static void error(String v) {
        logger.error(v);
    }

    public static void error(String v, Object... args) {
        logger.error(v, args);
    }

    public static void error(String v, Thread t) {
        logger.error(v, t);
    }

    public static void error(String v, Thread t, Object... args) {
        logger.error(v, t, args);
    }

    public static void warn(Object v) {
        logger.warn(v);
    }

    public static void warn(String v) {
        logger.warn(v);
    }

    public static void warn(String v, Object... args) {
        logger.warn(v, args);
    }

    public static void warn(String v, Thread t) {
        logger.warn(v, t);
    }

    public static void warn(String v, Thread t, Object... args) {
        logger.warn(v, t, args);
    }

    public static void exception(String v){
        logger.exception(v);
    }

}