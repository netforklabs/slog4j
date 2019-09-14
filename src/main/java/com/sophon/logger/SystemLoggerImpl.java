package com.sophon.logger;

import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;
import com.sophon.io.SophonWrite;
import com.sophon.io.SophonWriteBySize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author tiansheng
 * @Date 2019/9/13 3:43
 * @Description 系统日志
 */
public class SystemLoggerImpl extends SophonLoggerImpl {

    protected int trace = 2;

    private static final String formatString = "\\{\\}";

    // 日期格式化工具
    protected final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected SophonWrite write;

    public SystemLoggerImpl() {
    }

    public SystemLoggerImpl(int trace) {
        this.trace = trace;
        write = new SophonWriteBySize(2048,
                SophonFile.getFile(ConfigVo.getInstance().getLoggerSystemPrintPath()));
    }

    public SystemLoggerImpl(int trace,SophonFile file) {
        this.trace = trace;
        write = new SophonWriteBySize(2048, file);
    }

    @Override
    public String prefixGenerate(Level level, Thread t) {
        String className = t.getStackTrace()[trace].getClassName();
        String methodName = t.getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(t.getStackTrace()[trace].getLineNumber());
        String v = "${datetime} | ${class} ${method}:${line} | [slog4j:${level}] - ";
        return v.replaceAll("\\$\\{line\\}", lineNumber)
                .replaceAll("\\$\\{class\\}", className)
                .replaceAll("\\$\\{level\\}", String.valueOf(level))
                .replaceAll("\\$\\{method\\}", methodName)
                .replaceAll("\\$\\{datetime\\}", sdf.format(new Date()));
    }

    /**
     * 打印
     *
     * @param v
     */
    @Override
    protected synchronized void console(String v, Level level) {
        if (level == Level.ERROR || level == Level.WARN) {
            System.err.println(v);
        } else {
            System.out.println(v);
        }
        if(ConfigVo.getInstance().getLoggerPrintWrite()){
            write.write(v);
        }
    }

}
