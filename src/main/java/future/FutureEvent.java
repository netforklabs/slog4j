package future;

import com.sophon.logger.source.Logger;
import future.event.MethodEvent;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/26 0:12
 * @since 1.8
 */
public class FutureEvent {

    public static void init() {
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
