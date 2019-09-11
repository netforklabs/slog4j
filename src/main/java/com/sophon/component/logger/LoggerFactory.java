package com.sophon.component.logger;

import com.sophon.component.anno.Alone;
import com.sophon.component.io.SophonFile;
import com.sophon.config.ConfigVo;
import com.sophon.logger.SingleLogger;
import com.sophon.logger.SophonLogger;
import com.sophon.util.StringUtils;

/**
 * @Author tiansheng
 * @Date 2019/9/10 15:54
 * @Description 独立日志生成
 */
public class LoggerFactory {

    public static SophonLogger getLogger(Class<?> target) {
        SophonFile file = SophonFile.getFile(ConfigVo.getLoggerPrintPath());
        String classname = target.getName();
        classname = classname.substring(classname.lastIndexOf(".") + 1);
        String pathname = "/loggers/".concat(classname).concat("/").concat(classname).concat(".log");
        System.out.println(pathname);
        if(target.isAnnotationPresent(Alone.class)){
            Alone alone = target.getDeclaredAnnotation(Alone.class);
            if(!StringUtils.isEmpty(alone.value())){
                pathname = alone.value();
            }
        }
        return new SingleLogger(pathname);
    }

}
