package com.sophon.io;

/**
 * @Author tiansheng
 * @Date 2019/8/26 1:15
 * @Description 数据写出接口
 */
public interface SophonWrite {

    /**
     * 根据大小生成文件
     */
    String BYSIZE = "size";

    /**
     * 根据天数生成文件
     */
    String BYDAYS = "days";

    /**
     * 写出数据
     * @param v
     */
    void write(String v);

}
