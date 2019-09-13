package com.sophon.logger;

import com.sophon.component.io.SophonFile;
import com.sophon.logger.source.SystemLogger;

/**
 * @Author tiansheng
 * @Date 2019/9/14 7:00
 * @Description TODO
 */
public class ExceptionLoggerImpl extends SystemLoggerImpl {

    public ExceptionLoggerImpl(int trace, SophonFile file) {
        super(trace, file);
    }

    @Override
    public String prefixGenerate(String level) {
        return super.prefixGenerate(level);
    }
}
