package com.sophon.logger;

import com.sophon.config.Slog4jConfiguration;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;
import com.sophon.logger.cache.Pool;
import com.sophon.logger.cache.statics.Store;
import com.sophon.logger.cache.taskmgr.LogAnalyze;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 普通日志打印的实现类
 *
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/8/24 23:56
 * @since 1.8
 */
public class SophonLoggerImpl implements SophonLogger {

    /**
     * 忽略打印
     */
    protected final Set<Level> printIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelConsole();
    /**
     * 忽略写出
     */
    protected final Set<Level> writeIgnore = Slog4jConfiguration.getInstance().getLoggerProhibitLevelFile();
    /**
     * 数据写出接口
     */
    public static final SophonWrite write = SophonIO.getWrite();

    public SophonLoggerImpl() {
    }

    /**
     * 设置追踪哪个线程
     *
     * @param trace
     */
    public SophonLoggerImpl(int trace) {
        Store.trace = trace;
    }

    @Override
    public void info(Object v) {
        autoGenerator(new Object[]{
                v,
                null,
                null,
                null,
                Level.INFO
        });
    }

    @Override
    public void info(String v) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                null,
                Level.INFO
        });
    }

    @Override
    public void info(String v, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                args,
                Level.INFO
        });
    }

    @Override
    public void info(String v, Thread t) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                null,
                Level.INFO
        });
    }

    @Override
    public void info(String v, Thread t, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                args,
                Level.INFO
        });
    }

    @Override
    public void debug(Object v) {
        autoGenerator(new Object[]{
                v,
                null,
                null,
                null,
                Level.DEBUG
        });
    }

    @Override
    public void debug(String v) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                null,
                Level.DEBUG
        });
    }

    @Override
    public void debug(String v, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                args,
                Level.DEBUG
        });
    }

    @Override
    public void debug(String v, Thread t) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                null,
                Level.DEBUG
        });
    }

    @Override
    public void debug(String v, Thread t, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                args,
                Level.DEBUG
        });
    }

    @Override
    public void error(Object v) {
        autoGenerator(new Object[]{
                v,
                null,
                null,
                null,
                Level.ERROR
        });
    }

    @Override
    public void error(String v) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                null,
                Level.ERROR
        });
    }

    @Override
    public void error(String v, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                args,
                Level.ERROR
        });
    }

    @Override
    public void error(String v, Thread t) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                null,
                Level.ERROR
        });
    }

    @Override
    public void error(String v, Thread t, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                args,
                Level.ERROR
        });
    }

    @Override
    public void warn(Object v) {
        autoGenerator(new Object[]{
                v,
                null,
                null,
                null,
                Level.WARN
        });
    }

    @Override
    public void warn(String v) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                null,
                Level.WARN
        });
    }

    @Override
    public void warn(String v, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                null,
                args,
                Level.WARN
        });
    }

    @Override
    public void warn(String v, Thread t) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                null,
                Level.WARN
        });
    }

    @Override
    public void warn(String v, Thread t, Object... args) {
        autoGenerator(new Object[]{
                null,
                v,
                t,
                args,
                Level.WARN
        });
    }

    /**
     * 集合方法，根据参数丢入不同的缓存池。
     *
     * @param params 参数
     */
    public void autoGenerator(Object[] params) {
        new LogAnalyze(params, Thread.currentThread()).run();
    }

    /**
     * 打印
     *
     * @param v     日志
     * @param level 日志级别
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

    @Override
    public String prefixGenerate(Level level, Thread t) {
        return null;
    }
}
