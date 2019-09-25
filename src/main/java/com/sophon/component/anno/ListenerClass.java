package com.sophon.component.anno;

import java.lang.annotation.*;

/**
 * 类的监听注解
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/25 14:36
 * @since 1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenerClass {

    /**
     * 监听到的信息输出到哪个文件
     * @return
     */
    String value();

}
