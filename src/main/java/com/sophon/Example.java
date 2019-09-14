package com.sophon;

import com.sophon.logger.SophonLogger;
import com.sophon.logger.source.ExceptionLogger;
import com.sophon.logger.source.Logger;
import com.sophon.logger.source.SystemLogger;
import com.sophon.util.TimeCalc;

import java.sql.Time;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    // private static final Logger log = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        // TimeCalc.invoke(Example.class,"string");
        TimeCalc.invoke(Example.class, "logger");
    }

    public void string() {

        String var = "leval:";
        for (int i = 0; i < 10000; i++) {
            // enum: 234ms
            // String: 229ms
            // String concat: 3ms
            // enum concat: 3ms
            // var.concat(String.valueOf(Level.INFO));
        }

    }

    //
    // -- log4j打印并且输出到文件 --
    //      4629ms
    //
    // -- 将日志输出到文件 --
    //      217852ms
    //      217.8秒
    //      3.6分钟
    //
    // -- 不将日志输出到文件 --
    //      20217ms
    //      20.2秒
    //      0.3分钟
    //
    // -- 不将日志输出到文件,并且用固定格式 --
    //      18121ms
    //      18.12100秒
    //      0.3分钟
    //
    // -- 将日志输出到文件,并且不使用固定格式,流不关闭 --
    //      27335ms
    //      ‭‪27秒
    //      0.3分钟
    public void logger() {
        for (int i = 0; i < 10000; i++) {
            String var = "test";
            Logger.info(var);
            Logger.info("count:{}", i);
            Logger.info(1 == 1);
            Logger.info(SophonLogger.Level.INFO);
            Logger.info("======================================== LOGGER INFO END.....");
            Logger.debug(var);
            Logger.debug("count:{}", i);
            Logger.debug(1 == 1);
            Logger.debug(SophonLogger.Level.DEBUG);
            Logger.debug("======================================== LOGGER DEBUG END.....");
            Logger.error(var);
            Logger.error("count:{}", i);
            Logger.error(1 == 1);
            Logger.error(SophonLogger.Level.ERROR);
            Logger.error("======================================== LOGGER ERROR END.....");
            Logger.warn(var);
            Logger.warn("count:{}", i);
            Logger.warn(1 == 1);
            Logger.warn(SophonLogger.Level.WARN);
            Logger.warn("======================================== LOGGER WARN END.....");

            System.err.println("===================================================================================================");
            System.err.println("===================================================================================================");
            System.err.println("===================================================================================================");

            SystemLogger.info(var);
            SystemLogger.info("count:{}", i);
            SystemLogger.info(1 == 1);
            SystemLogger.info(SophonLogger.Level.INFO);
            SystemLogger.info("======================================== SYSTEM LOGGER INFO END.....");
            SystemLogger.debug(var);
            SystemLogger.debug("count:{}", i);
            SystemLogger.debug(1 == 1);
            SystemLogger.debug(SophonLogger.Level.DEBUG);
            SystemLogger.debug("======================================== SYSTEM LOGGER DEBUG END.....");
            SystemLogger.error(var);
            SystemLogger.error("count:{}", i);
            SystemLogger.error(1 == 1);
            SystemLogger.error(SophonLogger.Level.ERROR);
            SystemLogger.error("======================================== SYSTEM LOGGER ERROR END.....");
            SystemLogger.warn(var);
            SystemLogger.warn("count:{}", i);
            SystemLogger.warn(1 == 1);
            SystemLogger.warn(SophonLogger.Level.WARN);
            SystemLogger.warn("======================================== SYSTEM LOGGER WARN END.....");

            System.err.println("===================================================================================================");
            System.err.println("===================================================================================================");
            System.err.println("===================================================================================================");

            ExceptionLogger.info(var);
            ExceptionLogger.info("count:{}", i);
            ExceptionLogger.info(1 == 1);
            ExceptionLogger.info(SophonLogger.Level.INFO);
            ExceptionLogger.info("======================================== EXCEPTION LOGGER INFO END.....");
            ExceptionLogger.debug(var);
            ExceptionLogger.debug("count:{}", i);
            ExceptionLogger.debug(1 == 1);
            ExceptionLogger.debug(SophonLogger.Level.DEBUG);
            ExceptionLogger.debug("======================================== EXCEPTION LOGGER DEBUG END.....");
            ExceptionLogger.error(var);
            ExceptionLogger.error("count:{}", i);
            ExceptionLogger.error(1 == 1);
            ExceptionLogger.error(SophonLogger.Level.ERROR);
            ExceptionLogger.error("======================================== EXCEPTION LOGGER ERROR END.....");
            ExceptionLogger.warn(var);
            ExceptionLogger.warn("count:{}", i);
            ExceptionLogger.warn(1 == 1);
            ExceptionLogger.warn(SophonLogger.Level.WARN);
            ExceptionLogger.warn("======================================== EXCEPTION LOGGER WARN END.....");
        }
    }

}
