package com.sophon.component.hot;

import com.keyboard.register.ListenerMethodEntity;
import com.sophon.component.anno.AnnotationScanner;
import com.sophon.component.anno.ListenerMethod;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListenerMethod注解处理器
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/29 17:07
 * @since 1.8
 */
public class ListenerMethodProcessor {

    /**
     * 获取所有ListenerMethod注解实例（已经被new出来了的）
     *
     * @return ListenerMethodEntity List
     */
    public static List<ListenerMethodEntity> getLinstenerMethods() {
        ArrayList<ListenerMethodEntity> lms = new ArrayList<>();
        try {
            List<Class<?>> classes = AnnotationScanner.scanner(ListenerMethod.class, ElementType.METHOD);
            for (Class<?> target : classes) {
                Method[] methods = target.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(ListenerMethod.class)) {
                        ListenerMethod listenerMethod = method.getDeclaredAnnotation(ListenerMethod.class);
                        ListenerMethodEntity lme = new ListenerMethodEntity();
                        // 监听器日志输出路径
                        lme.setPathname(listenerMethod.value());
                        lme.setClasspath(target.getName());
                        lme.setImplpath(listenerMethod.process().getName());
                        lme.setMethodname(method.getName());
                        lme.setTriggerMethod(listenerMethod.trigger().toString());
                        // 设置方法参数
                        Class<?>[] parameters1 = method.getParameterTypes();
                        List<String> parameters2 = new ArrayList<>(parameters1.length);
                        for(Class<?> param : parameters1){
                            parameters2.add(param.getName());
                        }
                        String[] parameters3 = parameters2.toArray(new String[parameters1.length]);
                        lme.setParameters(parameters3);
                        lms.add(lme);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lms;
    }

}
