package com.sophon.io;

import com.sophon.logger.SophonLogger;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 1:18
 * @Description 根据大小写出数据
 */
public class SophonWriteBySize implements SophonWrite {

    @Override
    public void write(String v) {
        if(SophonLoggerIO.getSize() >= 1024){
            SophonLoggerIO.createFile();
        }else{
            SophonLoggerIO.write(v);
        }
    }

}
