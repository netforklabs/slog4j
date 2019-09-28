package future;

import javassist.*;
import javassist.util.HotSwapper;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * @author     private DateUtils() {
    private DateUtils() {
2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 15:09
 * @since 1.8
 */
public class CtClassExample {

    public static void create() throws Exception {
        ClassPool pool = new ClassPool(true);
        String className = "future.Test";
        CtClass cbtCtClass = pool.get(className);
        CtMethod method = cbtCtClass.getDeclaredMethod("test");
        // method.insertBefore("com.sophon.logger.source.Logger.info(\"--开始打印\");");
        // method.insertAfter("com.sophon.logger.source.Logger.info(\"--打印完成\");");
        // 拷贝一个方法
        String currentMethodName = method.getName() + "$Sophon";
        CtMethod ctnMethod = CtNewMethod.copy(method, currentMethodName, cbtCtClass, null);
        cbtCtClass.addMethod(ctnMethod); // 添加方法
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("com.sophon.logger.source.Logger.info(\"--开始打印\");")
                .append(currentMethodName + "($$);")
                .append("com.sophon.logger.source.Logger.info(\"--开始打印\");")
                .append("}");
        cbtCtClass.toClass();

    }

}
