package com.sophon.component.hot;

import com.keyboard.agent.HotModify;
import com.keyboard.register.ListenerMethodEntity;
import com.keyboard.register.ListenerMethodManager;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.util.List;

/**
 * 该类负责做各个功能的热部署
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/30 2:48
 * @since 1.8
 */
public class ReDefineClass {

    /**
     * 对ListenerMethod注解需要的功能做运行时替换
     *
     * @param entities 注解进行处理后的对象集合（ListenerMethodEntity）
     */
    public void listenerMethodHotReplacement(List<ListenerMethodEntity> entities) {
        HotModify modify = new HotModify();
        try {
            for (ListenerMethodEntity entity : entities) {
                // 从ClassPool获得CtClass对象
                final ClassPool pool = ClassPool.getDefault();
                final CtClass cbtCtClass = pool.get(entity.getClasspath());
                CtMethod method = cbtCtClass.getDeclaredMethod("test");
                // 构建方法
                String before = "new " + entity.getImplpath().concat("().before($class,\"test\",$args);");
                String after = "new " + entity.getImplpath().concat("().after($class,\"test\",$args);");
                method.insertBefore(before);
                method.insertAfter(after);
                // 返回字节码，并且detachCtClass对象
                byte[] byteCode = cbtCtClass.toBytecode();
                //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
                cbtCtClass.detach();
                modify.redefine(entity.getClasspath(),byteCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
