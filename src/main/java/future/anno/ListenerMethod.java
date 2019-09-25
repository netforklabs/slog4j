package future.anno;

import future.event.MethodInterceptor;

import java.lang.annotation.*;

/**
 * 方法监听器注解
 *
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/25 23:51
 * @since 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenerMethod {

    /**
     * 默认属性，该属性代表当前监听到的信息输出目录
     *
     * @return 文件输出路径
     */
    String value() default "";

    /**
     * trigger代表当前监听事件的操作
     *
     * @return 返回事件触发方式
     */
    TriggerMethod trigger() default TriggerMethod.DEBUG;

    /**
     * process代表当前注解监听到的事件交给开发人员自己处理。
     * <p>
     * 传入事件的类路径
     * </p>
     *
     * @return 返回事件处理类
     */
    Class<? extends MethodInterceptor> process() default MethodInterceptor.class;

}
