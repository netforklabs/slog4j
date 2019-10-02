package com.sophon.logger.cache.statics;

import com.sophon.config.Slog4jConfiguration;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;
import com.sophon.logger.SophonLogger;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h3>slog4j</h3>
 * <p>存储静态信息</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-10-01 18:48
 **/
public class Store {
    public static final String formatString = "\\{\\}";
    /**
     * 日期格式化工具
     */
    public static final DateFormat sdf = Slog4jConfiguration.getInstance().getSimpleDateFormat();

    /**
     * 日志打印模板
     */
    public static final String printTemplate = Slog4jConfiguration.getInstance().getLoggerPrintTemplate();

    /**
     * 忽略打印
     */
    public static final Set<SophonLogger.Level> printIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelConsole();

    /**
     * 忽略写出
     */
    public static final Set<SophonLogger.Level> writeIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelFile();

    /**
     * 数据写出接口
     */
    public static final SophonWrite write = SophonIO.getWrite();

    /**
     * 这是线程追踪数组的值
     * 为了方便理解,举个栗子:
     * 当前有 A,B 两个类
     * <p>
     * A 如果直接调用 slog.info("xxx"),那么上级线程就应该是 4!
     * <p>
     * 它们的调用关系是这样的: A ---> slog ---> SophonLoggerImpl
     * <p>
     * 所以我们 info 方法调用的线程就是 A, 所以 A 的线程值也就是4!
     * <p>
     * -----------------------------------------------------------------------------
     * <p>
     * 再假设 A 封装了 slog 类,B 通过 A.info("xxx") 来进行日志打印
     * <p>
     * 现在关系图就是这样了: B ---> A ---> slog ---> SophonLoggerImpl
     * <p>
     * 那么 trace 值就应该为 5 了,因为经过了 B、A、slog、MloggerImpl 4 个类,然后以此类推
     * <p>
     * 简单的说就是经过了一个类,追踪值为 2,经过两个追踪值为 3,经过了三个追踪值为 4
     */
    public static int trace = 4;

    public static final Map<String, String[]> featureCache = new HashMap<>();

    // 改为true，则禁用缓存功能，直接输出
    public static final boolean disableCache = Slog4jConfiguration.getInstance().getStackCache();
}
