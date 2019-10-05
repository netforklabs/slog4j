package com.sophon.logger;

import com.sophon.component.io.SophonFile;

/**
 * 独立的日志文件
 * @author tiansheng
 * @date 2019/9/11 13:40
 * @version 1.0
 * @since 1.8
 */
public class SeparationLogger extends SophonLoggerImpl implements SophonLogger {

    public SeparationLogger(String pathname){
        write.setFile(SophonFile.getFile(pathname));
    }

    public SeparationLogger(String pathname, int trace){
        write.setFile(SophonFile.getFile(pathname));
    }

}
