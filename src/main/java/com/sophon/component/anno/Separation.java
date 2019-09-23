package com.sophon.component.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加此注解在类上，那么那个类的所有日志将会单独输出到一个文件中。
 * @author tiansheng
 * @Date 2019/9/10 15:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Separation {

    /**
     * 选择输出到哪个路径。
     * 默认路径是当前slog4j.properties配置文件配置的文件输出路径。
     * log文件名默认为：类名.log。
     * @return
     */
    String value() default "";

}
