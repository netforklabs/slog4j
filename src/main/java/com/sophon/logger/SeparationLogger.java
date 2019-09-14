package com.sophon.logger;

import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;
import com.sophon.io.SophonIO;
import com.sophon.logger.SophonLogger;
import com.sophon.logger.SophonLoggerImpl;

/**
 * @Author tiansheng
 * @Date 2019/9/11 13:40
 * @Description 独立的日志文件
 */
public class SeparationLogger extends SophonLoggerImpl implements SophonLogger {

    public SeparationLogger(String pathname){
        this.trace = 4;
        write.setFile(SophonFile.getFile(pathname));
    }

    public SeparationLogger(String pathname, int trace){
        this.trace = trace;
        write.setFile(SophonFile.getFile(pathname));
    }

}