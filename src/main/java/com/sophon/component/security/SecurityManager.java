package com.sophon.component.security;

import com.sophon.component.anno.Alone;
import com.sophon.exception.ParamException;
import com.sophon.logger.Logger;
import com.sophon.util.StringUtils;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Author tiansheng
 * @Date 2019/9/12 23:37
 * @Description 安全管理检查组件
 */
public class SecurityManager implements Security {

    //
    // 是否启用安全检查
    //
    private boolean enable = false;
    private static Security security;

    static {
        security = new SecurityManager();
        security.enable(true);
    }

    public static Security getSecurityManager() {
        return security;
    }

    @Override
    public void enable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void annoCheck(Class<? extends Object> aClass, Class<? extends Annotation> anno) {
        if (enable) {
        }
    }

}
