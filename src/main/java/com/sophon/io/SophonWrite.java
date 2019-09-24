package com.sophon.io;

import com.sophon.component.io.SophonFile;

import java.io.FileNotFoundException;

/**
 * @author tiansheng
 * @Date 2019/8/26 1:15
 * @Description 数据写出接口
 */
public interface SophonWrite {

    /**
     * 根据大小生成文件
     */
    String BYSIZE = "size";

    /**
     * 写出数据
     * @param v 传入需要写出的数据
     */
    void write(String v);

    /**
     * 更换日志文件
     * @param file 传入最新的文件对象
     */
    void setFile(SophonFile file);

}
