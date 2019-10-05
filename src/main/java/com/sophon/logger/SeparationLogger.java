package com.sophon.logger;

import com.sophon.component.io.SophonFile;
import com.sophon.component.cache.statics.Store;

/**
 * 独立的日志文件
 * @author 2BKeyboard
 * @date 2019/9/11 13:40
 * @version 1.0.0
 * @since 1.8
 */
public class SeparationLogger extends SophonLoggerImpl implements SophonLogger {

    public SeparationLogger(String pathname){
        Store.trace = 4;
        write.setFile(SophonFile.getFile(pathname));
    }

    public SeparationLogger(String pathname, int trace){
        Store.trace = trace;
        write.setFile(SophonFile.getFile(pathname));
    }

}
