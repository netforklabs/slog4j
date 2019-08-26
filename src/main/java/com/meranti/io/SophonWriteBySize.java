package com.meranti.io;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 1:18
 * @Description 根据大小写出数据
 */
public class SophonWriteBySize implements AbsSophonWrite {

    @Override
    public void write(String v) {
        if(SophonLoggerIO.getSizeByKB() >= 1024){

        }
        SophonLoggerIO.write(v);
    }

}
