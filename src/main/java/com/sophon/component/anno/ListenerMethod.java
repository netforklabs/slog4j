package com.sophon.component.anno;

import java.lang.annotation.*;

/**
 * 方法监听注解
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/25 14:36
 * @since 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenerMethod {

    /**
     * 监听到的信息输出到哪个文件
     * @return
     */
    String value() default "";

    /**
     * 在什么情况下触发监听事件
     * @return 指定操作
     */
    String operation() default "";

}
