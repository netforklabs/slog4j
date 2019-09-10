package com.sophon.io;

import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;

/**
 * @Author tiansheng
 * @Date 2019/9/11 2:13
 * @Description TODO
 */
public class SophonIO {

    /**
     * 输出写出接口
     */
    private static SophonWrite write;

    static {
        // 获取要操作的文件
        SophonFile file = new SophonFile(
                System.getProperty("user.dir") + ConfigVo.getLoggerPrintPath(),true);
        file = new SophonFile(file.getParent()
                .concat("/")
                .concat(file.getNoSuffixName())
                .concat("_0")
                .concat(file.getSuffix()));
        // 获取数据写出接口实例
        String[] rule = ConfigVo.getLoggerGenerateRule();
        switch (rule[0]){
            case SophonWrite.BYSIZE:
                write = new SophonWriteBySize(Integer.parseInt(rule[1]),file);
                break;
            case SophonWrite.BYDAYS:
                write = new SophonWriteByDays();
                break;
            default:
                write = new SophonWriteBySize(1024,file);
                break;
        }
    }

    public static SophonWrite getWrite(){
        return write;
    }

}
