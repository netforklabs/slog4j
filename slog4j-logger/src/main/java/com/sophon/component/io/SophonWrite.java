package com.sophon.component.io;

import com.sophon.component.io.SophonFile;

import java.io.FileNotFoundException;

/**
 * 数据写出接口
 * @author 2BKeyboard
 * @date 2019/8/26 1:15
 * @version 1.0.0
 * @since 1.8
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
