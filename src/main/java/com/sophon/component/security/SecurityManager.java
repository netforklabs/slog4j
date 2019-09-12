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

    public static Security getSecurity() {
        return security;
    }

    @Override
    public void enable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void annoCheck(Class<? extends Object> aClass, Class<? extends Annotation> anno) {
        if (enable) {
            try {
                Logger.debug("anno name:{}",anno.getName());
                /** @link com.sophon.component.anno.Alone **/
                checkAnnoAlone(aClass);
            } catch (ParamException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查Alone注解使用是否合法
     *
     * @param aClass
     */
    private void checkAnnoAlone(Class<?> aClass) throws ParamException {
        ArrayList<Alone> alones = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Alone.class)) {
                alones.add(field.getDeclaredAnnotation(Alone.class));
            }
        }
        if (alones.size() >= 2) {
            for (Alone alone : alones) {
                boolean v1 = StringUtils.isEmpty(alone.name());
                boolean v2 = StringUtils.isEmpty(alone.value());
                if (v1 && v2) {
                    String message = aClass.getName()+"name && value 不可为空";
                    throw new ParamException(message);
                } else if (v1) {
                    String message = aClass.getName()+"在多个注解的场景下,name属性不可为空";
                    throw new ParamException(message);
                } else if (v2) {
                    Annotation anno = alone;
                    String message = aClass.getName()+"在多个注解的场景下,value属性不可为空";
                    throw new ParamException(message);
                }
            }
        }
    }

}
