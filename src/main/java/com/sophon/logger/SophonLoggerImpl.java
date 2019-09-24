package com.sophon.logger;

import com.sophon.config.Slog4jConfiguration;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @author tiansheng
 * @Date 2019/8/24 23:56
 * @Description 普通日志打印的实现类
 */
public class SophonLoggerImpl implements SophonLogger {

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
    protected int trace = 2;

    private static final String formatString = "\\{\\}";

    /**
     * 忽略打印
     */
    protected final Set<Level> printIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelConsole();
    /**
     * 忽略写出
     */
    protected final Set<Level> writeIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelFile();

    /**
     * 日期格式化工具
     */
    protected final DateFormat sdf = Slog4jConfiguration.getInstance().getSimpleDateFormat();

    /**
     * 日志打印模板
     */
    protected final String printTemplate = Slog4jConfiguration.getInstance().getLoggerPrintTemplate();

    /**
     * 数据写出接口
     */
    protected final SophonWrite write = SophonIO.getWrite();

    public SophonLoggerImpl() {
    }

    /**
     * 设置追踪哪个线程
     *
     * @param trace
     */
    public SophonLoggerImpl(int trace) {
        this.trace = trace;
    }

    @Override
    public void info(String v) {
        console(prefixGenerate(Level.INFO).concat(v), Level.INFO);
    }

    @Override
    public void info(String v, Object... args) {
        console(prefixGenerate(Level.INFO).concat(format(v, args)), Level.INFO);
    }

    @Override
    public void info(Object v) {
        console(prefixGenerate(Level.INFO).concat(String.valueOf(v)), Level.INFO);
    }

    @Override
    public void info(String v, Thread t) {
        console(prefixGenerate(Level.INFO, t).concat(v), Level.INFO);
    }

    @Override
    public void info(String v, Thread t, Object... args) {
        console(prefixGenerate(Level.INFO, t).concat(format(v, args)), Level.INFO);
    }

    @Override
    public void debug(Object v) {
        console(prefixGenerate(Level.DEBUG).concat(String.valueOf(v)), Level.DEBUG);
    }

    @Override
    public void debug(String v) {
        console(prefixGenerate(Level.DEBUG).concat(v), Level.DEBUG);
    }

    @Override
    public void debug(String v, Object... args) {
        console(prefixGenerate(Level.DEBUG).concat(format(v, args)), Level.DEBUG);
    }

    @Override
    public void debug(String v, Thread t) {
        console(prefixGenerate(Level.DEBUG, t).concat(v), Level.DEBUG);
    }

    @Override
    public void debug(String v, Thread t, Object... args) {
        console(prefixGenerate(Level.DEBUG, t).concat(format(v, args)), Level.DEBUG);
    }

    @Override
    public void error(Object v) {
        console(prefixGenerate(Level.ERROR).concat(String.valueOf(v)), Level.ERROR);
    }

    @Override
    public void error(String v) {
        console(prefixGenerate(Level.ERROR).concat(v), Level.ERROR);
    }

    @Override
    public void error(String v, Object... args) {
        console(prefixGenerate(Level.ERROR).concat(format(v, args)), Level.ERROR);
    }

    @Override
    public void error(String v, Thread t) {
        console(prefixGenerate(Level.ERROR, t).concat(v), Level.ERROR);
    }

    @Override
    public void error(String v, Thread t, Object... args) {
        console(prefixGenerate(Level.ERROR, t).concat(format(v, args)), Level.ERROR);
    }

    @Override
    public void warn(Object v) {
        console(prefixGenerate(Level.WARN).concat(String.valueOf(v)), Level.WARN);
    }

    @Override
    public void warn(String v) {
        console(prefixGenerate(Level.WARN).concat(v), Level.WARN);
    }

    @Override
    public void warn(String v, Object... args) {
        console(prefixGenerate(Level.WARN).concat(format(v, args)), Level.WARN);
    }

    @Override
    public void warn(String v, Thread t) {
        console(prefixGenerate(Level.WARN, t).concat(v), Level.WARN);
    }

    @Override
    public void warn(String v, Thread t, Object... args) {
        console(prefixGenerate(Level.WARN, t).concat(format(v, args)), Level.WARN);
    }

    /**
     * 前缀生成
     *
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    public String prefixGenerate(Level level) {
        return prefixGenerate(level, Thread.currentThread());
    }

    @Override
    public String prefixGenerate(Level level, Thread t) {
        String className = t.getStackTrace()[trace].getClassName();
        String methodName = t.getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(t.getStackTrace()[trace].getLineNumber());
        String v = printTemplate.replace("${line}", lineNumber)
                .replace("${class}", className)
                .replace("${level}", String.valueOf(level))
                .replace("${method}", methodName)
                .replace("${datetime}", sdf.format(new Date()));
        return v;
    }

    /**
     * 打印
     *
     * @param v
     */
    protected synchronized void console(String v, Level level) {
        // 没有被忽略的级别才进入输出
        if (!printIgnore.contains(level)) {
            System.out.println(v);
            Slog4jConfiguration.getInstance().printPlus();
            if (Slog4jConfiguration.getInstance().getLoggerPrintWrite()) {
                if (!writeIgnore.contains(level)) {
                    // 输出到日志文件
                    write.write(v);
                }
            }
        }
    }

    /**
     * 将字符串格式化
     *
     * @param v
     * @param args
     * @return
     */
    private static String format(String v, Object... args) {
        v = v.replaceAll(formatString, "%s");
        v = String.format(v, args);
        return v;
    }

}
