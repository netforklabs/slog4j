package com.sophon.component.security;

import com.sophon.component.anno.Alone;

import java.lang.annotation.Annotation;

/**
 * @Author tiansheng
 * @Date 2019/9/12 23:38
 * @Description TODO
 */
public interface Security {

    /**
     * 是否启用安全管理检查组件
     * @param enable
     */
    void enable(boolean enable);

    /**
     * 注解检查。
     */
    void annoCheck(Class<? extends Object> aClass, Class<? extends Annotation> anno);

}
