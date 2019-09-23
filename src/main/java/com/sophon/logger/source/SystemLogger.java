package com.sophon.logger.source;

import com.sophon.logger.SophonLogger;
import com.sophon.logger.SystemLoggerImpl;

/**
 * @author tiansheng
 * @Date 2019/9/13 3:49
 * @Description TODO
 */
public final class SystemLogger {

    private static final SophonLogger logger = new SystemLoggerImpl(5);

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

}
