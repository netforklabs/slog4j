package com.sophon.logger;

import com.sophon.config.ConfigVo;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author tiansheng
 * @Date 2019/8/24 23:56
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

    // 已被忽略的级别
    protected final Set<Level> printIgnore = new HashSet<>(4); // 忽略打印
    protected final Set<Level> writeIgnore = new HashSet<>(4); // 忽略写出

    // 日期格式化工具
    protected final DateFormat sdf = ConfigVo.getSimpleDateFormat();

    // 日期打印模板
    protected final String printTemplate = ConfigVo.getLoggerPrintTemplate();

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
        v = prefixGenerate("INFO").concat(v);
        console(v,Level.INFO);
    }

    @Override
    public void info(String v, Object... args) {
        info(format(v,args));
    }

    @Override
    public void debug(String v) {
        v = prefixGenerate("DEBUG").concat(v);
        console(v,Level.DEBUG);
    }

    @Override
    public void debug(String v, Object... args) {
        debug(format(v,args));
    }

    @Override
    public void error(String v) {
        v = prefixGenerate("ERROR").concat(v);
        console(v,Level.ERROR);
    }

    @Override
    public void error(String v, Object... args) {
        error(format(v,args));
    }

    @Override
    public void warn(String v) {
        v = prefixGenerate("WARN").concat(v);
        console(v,Level.WARN);
    }

    @Override
    public void warn(String v, Object... args) {
        warn(format(v,args));
    }

    @Override
    public void exception(String s,Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String exceptionInfo = stringWriter.toString();
        System.out.println(s);
        System.out.println(exceptionInfo);
        // 关闭流
        try {
            printWriter.close();
            stringWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 前缀生成
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    public String prefixGenerate(String level) {
        String className = Thread.currentThread().getStackTrace()[trace].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(Thread.currentThread().getStackTrace()[trace].getLineNumber());
        String v = printTemplate;
        return v.replaceAll("\\$\\{line\\}", lineNumber)
                .replaceAll("\\$\\{class\\}", className)
                .replaceAll("\\$\\{level\\}", level)
                .replaceAll("\\$\\{method\\}", methodName)
                .replaceAll("\\$\\{datetime\\}", sdf.format(new Date()));
    }

    /**
     * 打印
     * @param v
     */
    protected synchronized void console(String v,Level level){
        // 没有被忽略的级别才进入输出
        if(!printIgnore.contains(level)) {
            System.out.println(v);
            ConfigVo.printPlus();
            if(ConfigVo.getLoggerPrintWrite()){
                if(!writeIgnore.contains(level)){
                    // 输出到日志文件
                    write.write(v);
                }
            }
        }
    }

    /**
     * 将字符串格式化
     * @param v
     * @param args
     * @return
     */
    private static String format(String v,Object... args) {
        v = v.replaceAll(formatString,"%s");
        v = String.format(v,args);
        return v;
    }

}
