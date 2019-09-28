package future.ctclass;

import com.sophon.component.anno.AnnotationScanner;
import future.anno.ListenerMethod;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 0:12
 * @since 1.8
 */
public class FutureEvent {

    public static void init() {
        try {
            List<Class<?>> classes = AnnotationScanner.scanner(ListenerMethod.class, ElementType.METHOD);
            for (Class<?> target : classes) {
                Method[] methods = target.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(ListenerMethod.class)) {
                        ListenerMethod listenerMethod = method.getDeclaredAnnotation(ListenerMethod.class);
                        Class<?> interceptor = listenerMethod.process();
                        StringBuffer fullcode = new StringBuffer();
                        fullcode.append(interceptor.getName()) // 获取类路径
                                .append("." + method.getName().concat("()")); // 拼接方法
                    }
                }
            }
            CtClassExample.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
