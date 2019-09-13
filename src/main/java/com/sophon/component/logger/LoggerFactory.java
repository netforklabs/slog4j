package com.sophon.component.logger;

import com.sophon.component.anno.Separation;
import com.sophon.logger.SeparationLogger;
import com.sophon.logger.SophonLogger;
import com.sophon.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @Author tiansheng
 * @Date 2019/9/10 15:54
 * @Description 独立日志生成
 */
public class LoggerFactory {

    /**
     * 对类中的 SophonLogger 对象实现注入
     */
    public static void injection() {
        try {
            Class<?> target = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            Object instance = target.newInstance();
            // 获取类中的成员
            Field[] fields = target.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Separation.class)) {
                    Separation separation = field.getDeclaredAnnotation(Separation.class);
                    if (!StringUtils.isEmpty(separation.value())) {
                        field.setAccessible(true);
                        // 创建 SeparationLogger 对象实例
                        String pathname = separation.value();
                        SophonLogger sophonLogger = new SeparationLogger(pathname,3);
                        field.set(instance, sophonLogger);
                    }else{
                        String classname = target.getName();
                        String pathname = "/loggers/"
                                .concat(classname)
                                .concat("/")
                                .concat(classname)
                                .concat(".log");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final SophonLogger getLogger(String fieldname) {
        String pathname = "";
        try {
            // 加载类信息
            Class<?> target = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            String classname = target.getName();
            classname = classname.substring(classname.lastIndexOf(".") + 1);
            pathname = "/loggers/".concat(classname).concat("/").concat(classname).concat(".log");
            Field field = target.getDeclaredField(fieldname);
            // 判断注解是否存在
            if (field.isAnnotationPresent(Separation.class)) {
                // 获取注解信息
                Separation alone = field.getDeclaredAnnotation(Separation.class);
                if (!StringUtils.isEmpty(alone.value())) {
                    pathname = alone.value();
                }
            } else {
                throw new NullPointerException(fieldname.concat("对象不存在Alone注解"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SeparationLogger(pathname, 3);
    }

}
