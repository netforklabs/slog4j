package com.sophon.component.logger;

import com.sophon.Example;
import com.sophon.component.anno.Alone;
import com.sophon.component.security.Security;
import com.sophon.component.security.SecurityManager;
import com.sophon.logger.SingleLogger;
import com.sophon.logger.SophonLogger;
import com.sophon.util.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * @Author tiansheng
 * @Date 2019/9/10 15:54
 * @Description 独立日志生成
 */
public class LoggerFactory {

    public static final SophonLogger getLogger(Class<?> target,String fieldname) {
        String classname = target.getName();
        classname = classname.substring(classname.lastIndexOf(".") + 1);
        String pathname = "/loggers/".concat(classname).concat("/").concat(classname).concat(".log");
        try {
            Field field = target.getDeclaredField(fieldname);
            // 判断注解是否存在
            if (field.isAnnotationPresent(Alone.class)) {
                // 获取注解信息
                Alone alone = field.getDeclaredAnnotation(Alone.class);
                if (!StringUtils.isEmpty(alone.value())) {
                    pathname = alone.value();
                }
            } else {
                throw new NullPointerException(fieldname.concat("对象不存在Alone注解"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SingleLogger(pathname,3);
    }

}
