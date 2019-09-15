import com.sophon.logger.SophonLogger;
import com.sophon.logger.source.Logger;
import com.sophon.util.TimeCalc;

import java.util.ArrayList;

/**
 * @Author tiansheng
 * @Date 2019/9/12 3:40
 * @Description TODO
 */
public class Example {

    public static long consoleTime = 0L;
    public static long ioWriteTime = 0L;
    public static long prefixGeneratorTime = 0L;

    // private static final Logger log = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        // TimeCalc.invoke(Example.class,"string");
        TimeCalc.invoke(Example.class, "logger");
        // TimeCalc.invoke(Example.class, "et");
        // Logger.info("system start...");
        // ArrayList<String> list = null;
        // list.add("string");
        Logger.info("console run time:{}", consoleTime);
        Logger.info("io write run time:{}", ioWriteTime);
        Logger.info("prefixGenerator run time:{}", prefixGeneratorTime);
        Logger.info("system end...");
    }

    public void stream() {

    }

    public void et() {
        Logger.info("system start...");
        ArrayList<String> list = null;
        list.add("string");
        Logger.info("system end...");
    }

    public void string() {

        String var = "leval:";
        for (int i = 0; i < 1000; i++) {
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
    //
    // -- 将日志输出到文件,并且不使用固定格式,流不关闭,设置缓冲区大小 --
    //      25088ms
    //      ‭‪25秒
    //      0.3分钟
    //
    public void logger() {
        String var = "test";
        for (int i = 0; i < 10000; i++) {
            // 优化一次后: 平均7秒
            Logger.info(var);
            Logger.info(1 == 1);
            Logger.info(SophonLogger.Level.INFO);
            Logger.info("======================================== LOGGER INFO END --- ",i);
            Logger.debug(var);
            Logger.debug(1 == 1);
            Logger.debug(SophonLogger.Level.DEBUG);
            Logger.debug("======================================== LOGGER DEBUG END --- ",i);
            Logger.error(var);
            Logger.error(1 == 1);
            Logger.error(SophonLogger.Level.ERROR);
            Logger.error("======================================== LOGGER WARN END --- {} --- ",i);
            Logger.warn(var);
            Logger.warn(1 == 1);
            Logger.warn(SophonLogger.Level.WARN);
            Logger.warn("======================================== LOGGER WARN END.....");
            Logger.info(var);
            Logger.info(1 == 1);
            Logger.info(SophonLogger.Level.INFO);
            Logger.info("======================================== LOGGER INFO END --- ",i);
            Logger.debug(var);
            Logger.debug(1 == 1);
            Logger.debug(SophonLogger.Level.DEBUG);
            Logger.debug("======================================== LOGGER DEBUG END --- ",i);
            Logger.error(var);
            Logger.error(1 == 1);
            Logger.error(SophonLogger.Level.ERROR);
            Logger.error("======================================== LOGGER WARN END --- {} --- ",i);
            Logger.warn(var);
            Logger.warn(1 == 1);
            Logger.warn(SophonLogger.Level.WARN);
            Logger.warn("======================================== LOGGER WARN END.....");
            Logger.info(var);
            Logger.info(1 == 1);
            Logger.info(SophonLogger.Level.INFO);
            Logger.info("======================================== LOGGER INFO END --- ",i);
            Logger.debug(var);
            Logger.debug(1 == 1);
            Logger.debug(SophonLogger.Level.DEBUG);
            Logger.debug("======================================== LOGGER DEBUG END --- ",i);
            Logger.error(var);
            Logger.error(1 == 1);
            Logger.error(SophonLogger.Level.ERROR);
            Logger.error("======================================== LOGGER WARN END --- {} --- ",i);
            Logger.warn(var);
            Logger.warn(1 == 1);
            Logger.warn(SophonLogger.Level.WARN);
            Logger.warn("======================================== LOGGER WARN END.....");
        }
    }

}
