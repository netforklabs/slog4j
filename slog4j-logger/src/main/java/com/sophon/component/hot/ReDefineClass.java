package com.sophon.component.hot;

import com.keyboard.agent.HotModify;
import com.keyboard.register.ListenerMethodEntity;
import com.keyboard.register.ListenerMethodManager;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import java.util.List;
import java.util.UUID;

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
     * 对ListenerMethod注解需要的功能做运行时的类做编织
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
                // 将参数列表转换为CtClass
                int len = entity.getParameters().length;
                CtClass[] ctParameters = new CtClass[len];
                Class<?>[] classParam = entity.getParameters();
                for(int i=0; i<len; i++){
                    ctParameters[i] = pool.get(classParam[i].getName());
                }
                CtMethod method = cbtCtClass.getDeclaredMethod(entity.getMethodname(),ctParameters);
                // 创建属性：由于Java8不支持添加或删除成员，但说在后面可能会取消限制，这段代码就不删除了。
                // String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // uuid保证属性名不重复
                // String fieldName = "sophon$" + uuid;
                // String fieldStatement = "private static ".concat(entity.getImplpath())
                //         .concat(" " + fieldName + " = new ")
                //         .concat(entity.getImplpath() + "();");
                // CtField field = CtField.make(fieldStatement, cbtCtClass);
                // cbtCtClass.addField(field);
                // 构建方法
                String before = "new " + entity.getImplpath()
                        .concat("().before($class,").concat("\"")
                        .concat(entity.getMethodname()).concat("\"")
                        .concat(",$args);");
                String after = "new " + entity.getImplpath()
                        .concat("().after($class,").concat("\"")
                        .concat(entity.getMethodname()).concat("\"")
                        .concat(",$args);");
                method.insertBefore(before);
                method.insertAfter(after);
                // 返回字节码，并且detachCtClass对象
                byte[] byteCode = cbtCtClass.toBytecode();
                // 解冻
                cbtCtClass.defrost();
                modify.redefine(entity.getClasspath(), byteCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
