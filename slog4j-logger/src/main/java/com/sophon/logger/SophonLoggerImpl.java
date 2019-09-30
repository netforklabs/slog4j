package com.sophon.logger;

import com.sophon.config.Slog4jConfiguration;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;

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

    /**
     * <h2>Object v</h2>
     * <p>传入一个未知类，转换为字符串，输出</p>
     * <h2>String v</h2>
     * <p>传入字符串，输出</p>
     * <h2>String v, Object... args</h2>
     * <p>传入字符串和自定义参数，输出</p>
     * <h2>String v, Thread t</h2>
     * <p>传入字符串和线程信息，输出</p>
     * <h2>String v, Thread t, Object... args</h2>
     * <p>传入字符串、线程信息和自定义参数，输出</p>
     */

    /**
     * 每个参数对应一个下标：
     * | --- | -------------- |
     * | 下标 | 参数           |
     * | 0   | Object v       |
     * | 1   | String v       |
     * | 2   | Thread t       |
     * | 3   | Object... args |
     * | 4   | Level          |
     * | --- | -------------- |
     */

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
     * 集合方法，根据参数执行不同的日志操作。
     * 固定参数数组大小：5
     *
     * @param params 参数
     */
    public void autoGenerator(Object[] params) {
        // !!! 顺序不得更改，死的很惨 !!!
        if (params[1] != null && params[2] != null && params[3] != null) {
            // 传入字符串、线程信息和自定义参数，输出
            String v = (String) params[1];
            Thread t = (Thread) params[2];
            Object[] args = (Object[]) params[3];
            console(prefixGenerate((Level) params[4], t).concat(format(v, args)), (Level) params[4]);
        } else if (params[1] != null && params[3] != null) {
            // 传入字符串和自定义参数，输出
            String v = (String) params[1];
            Object[] args = (Object[]) params[3];
            console(prefixGenerate((Level) params[4]).concat(format(v, args)), (Level) params[4]);
        } else if (params[1] != null && params[2] != null) {
            // 传入字符串和线程信息，输出
            String v = (String) params[1];
            Thread t = (Thread) params[2];
            console(prefixGenerate((Level) params[4], t).concat(v), (Level) params[4]);
        } else if (params[1] != null) {
            // 传入字符串，输出
            String v = (String) params[1];
            console(prefixGenerate((Level) params[4]).concat(v), (Level) params[4]);
        } else if (params[0] != null) {
            // 传入一个未知类，转换为字符串，输出
            Object v = params[0];
            console(prefixGenerate((Level) params[4]).concat(String.valueOf(v)), (Level) params[4]);
        }
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

}
