package com.sophon.logger;

import com.sophon.config.ConfigVo;
import com.sophon.io.SophonIO;
import com.sophon.io.SophonWrite;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author tiansheng
 * @Date 2019/9/13 3:43
 * @Description 系统日志
 */
public class SystemLoggerImpl implements SophonLogger {

    protected int trace = 2;

    private static final String formatString = "\\{\\}";

    // 日期格式化工具
    protected final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 日期打印模板
    protected final String printTemplate = ConfigVo.getLoggerPrintTemplate();

    public SystemLoggerImpl() {
    }

    public SystemLoggerImpl(int trace) {
        this.trace = trace;
    }

    @Override
    public void info(String v) {
        v = prefixGenerate("INFO").concat(v);
        console(v, Level.INFO);
    }

    @Override
    public void info(String v, Object... args) {
        info(format(v, args));
    }

    @Override
    public void debug(String v) {
        v = prefixGenerate("DEBUG").concat(v);
        console(v, Level.DEBUG);
    }

    @Override
    public void debug(String v, Object... args) {
        debug(format(v, args));
    }

    @Override
    public void error(String v) {
        v = prefixGenerate("ERROR").concat(v);
        console(v, Level.ERROR);
    }

    @Override
    public void error(String v, Object... args) {
        error(format(v, args));
    }

    @Override
    public void warn(String v) {
        v = prefixGenerate("WARN").concat(v);
        console(v, Level.WARN);
    }

    @Override
    public void warn(String v, Object... args) {
        warn(format(v, args));
    }

    @Override
    public void exception(String s, Throwable e) {
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
     *
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    public String prefixGenerate(String level) {
        String className = Thread.currentThread().getStackTrace()[trace].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(Thread.currentThread().getStackTrace()[trace].getLineNumber());
        String v = "${datetime} | ${class} ${method}:${line} | [slog4j:${level}] - ";
        return v.replaceAll("\\$\\{line\\}", lineNumber)
                .replaceAll("\\$\\{class\\}", className)
                .replaceAll("\\$\\{level\\}", level)
                .replaceAll("\\$\\{method\\}", methodName)
                .replaceAll("\\$\\{datetime\\}", sdf.format(new Date()));
    }

    /**
     * 打印
     *
     * @param v
     */
    protected synchronized void console(String v, Level level) {
        if (level == Level.ERROR || level == Level.WARN) {
            System.err.println(v);
        } else {
            System.out.println(v);
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
