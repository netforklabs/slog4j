package com.sophon.io;

import com.sophon.component.io.SophonFile;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * @Author tiansheng
 * @Date 2019/8/26 1:18
 * @Description 根据大小写出数据
 */
public class SophonWriteBySize implements SophonWrite {

    /**
     * 文件大小，到达这个值重新生成日志文件。
     * 这个大小是按kb来计算的。
     */
    private Integer size;

    /**
     * 操作的文件
     */
    private SophonFile file;

    // private BufferedWriter bw;

    public SophonWriteBySize(@NotNull Integer size, SophonFile file) {
        this.size = size;
        this.file = file;
    }

    @Override
    public void write(String v) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file, true)))) {
            if (file.getSizeByKB() <= size) {
                // 当前文件大小小于size
                bw.write(v.concat("\n"));
                bw.flush();
            } else {
                // 当前文件大小大于size
                setFile(file.getNewFileObject());
                BufferedWriter bw1 =
                        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                bw1.write(v.concat("\n"));
                bw1.flush();
                bw1.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(SophonFile file) {
        this.file = file;
    }
}
