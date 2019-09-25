package future;

import com.sophon.component.anno.AnnotationScanner;
import future.anno.ListenerMethod;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/26 0:12
 * @since 1.8
 */
public class FutureEvent {

    static Map<String, String> beans = new HashMap<>();

    public static void init() {

        List<Class<?>> classes = AnnotationScanner.scanner(ListenerMethod.class, ElementType.METHOD);

        for (Class<?> target : classes) {
            Method[] methods = target.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ListenerMethod.class)) {
                    beans.put(target.getName(), method.getName());
                }
            }
        }

        try {
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("future.Test");
            CtClass ct = pool.get("future.Test");
            CtMethod method = ct.getDeclaredMethod("test");
            method.insertBefore("com.sophon.logger.source.Logger.info(\"--开始打印\");");
            method.insertAfter("com.sophon.logger.source.Logger.info(\"--打印完成\");");
            ct.toClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
