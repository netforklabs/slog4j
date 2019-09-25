package com.sophon.component.logger;

import com.sophon.component.anno.Separation;
import com.sophon.logger.SeparationLogger;
import com.sophon.logger.SophonLogger;
import com.sophon.util.StringUtils;

import java.lang.reflect.Field;

/**
 * 独立Logger对象生成
 * @author tiansheng
 * @date 2019/9/10 15:54
 * @version 1.0.0
 * @since 1.8
 */
public class LoggerFactory {

    /**
     * 对类中的 SophonLogger 对象实现注入。
     * 需要注意的是，对象必须是static的，否则注入不了。
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
                        SophonLogger sophonLogger = new SeparationLogger(pathname, 3);
                        field.set(instance, sophonLogger);
                    } else {
                        String classname = target.getName();
                        String pathname = "classpath:/loggers/"
                                .concat(classname)
                                .concat("/")
                                .concat(classname)
                                .concat(".log");
                        SophonLogger sophonLogger = new SeparationLogger(pathname, 3);
                        field.set(instance, sophonLogger);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
