package future.event;

import java.lang.reflect.Method;

/**
 *
 * @author     private DateUtils() {
    private DateUtils() {
2BKeyboard
 * @version 1.0.0
 * @date 2019/9/25 23:59
 * @since 1.8
 */
public interface MethodInterceptor {

    /**
     * 当方法被调用时
     * @param target 目标对象
     * @param method 被调用的方法
     * @param args 被调用方法的参数
     */
    void before(Object target, Method method, Object[] args);

    /**
     * 当方法结束时
     * @param target 目标对象
     * @param method 被调用的方法
     * @param args 被调用方法的参数
     */
    void after(Object target, Method method, Object[] args);

}
