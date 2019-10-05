package com.sophon.component.io;

import com.sophon.component.cache.statics.Store;
import com.sophon.util.SophonUtils;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 根据大小写出数据
 * @author 2BKeyboard
 * @date 2019/8/26 1:18
 * @version 1.0.0
 * @since 1.8
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

    private BufferedOutputStream bufferedOutputStream;

    public SophonWriteBySize(Integer size, SophonFile file) {
        this.size = size;
        this.file = file;
    }

    @Override
    public void write(String v) {
        v += "\n";
        try {
            if (bufferedOutputStream == null) {
                bufferedOutputStream = SophonUtils.newBufferedWriter(file);
            }
            if (file.getSizeByKB() <= size) {
                // 当前文件大小小于size
                bufferedOutputStream.write(v.getBytes());
            } else {
                // 当前文件大小大于size
                flush();
                setFile(file.getNewFileObject());
                bufferedOutputStream = SophonUtils.newBufferedWriter(file);
                bufferedOutputStream.write(v.getBytes());
            }
        } catch (FileNotFoundException FNFE) {
            retry(v);
        } catch (IOException IOE) {
            retry(v);
        }
    }

    @Override
    public void setFile(SophonFile file) {
        this.file = file;
    }

    @Override
    public void flush() {
        try {
            bufferedOutputStream.flush();
        } catch (IOException IOE) {}
    }

    @Override
    public void retry(String v) {
        System.out.println("slog4j :: IO :: File output failed! retrying...");
        while (true) {
            try {
                flush();
                setFile(file.getNewFileObject());
                bufferedOutputStream = SophonUtils.newBufferedWriter(file);
                bufferedOutputStream.write(v.getBytes());
                break;
            } catch (FileNotFoundException FNFE2) {
                FNFE2.printStackTrace();
            } catch (IOException IOE2) {
                IOE2.printStackTrace();
            }
        }
        System.out.println("slog4j :: IO :: File output retried successful.");
    }
}
