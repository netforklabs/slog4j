package com.meranti.io;

/**
 * @Author Mr.luo
 * @Date 2019/8/26 1:17
 * @Description 根据天数写出数据
 */
public class SophonWriteByDays implements AbsSophonWrite {

    @Override
    public void write(String v) {
        SophonLoggerIO.write(v);
    }

}
