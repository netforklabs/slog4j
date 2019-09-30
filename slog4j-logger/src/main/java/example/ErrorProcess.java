package example;

import com.sophon.component.hot.SophonListener;

import java.lang.reflect.Method;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/10/1 4:14
 * @since 1.8
 */
public class ErrorProcess implements SophonListener {

    @Override
    public void before(Class<?> target, String method, Object[] args) {

    }

    @Override
    public void after(Class<?> target, String method, Object[] args) {

    }

    @Override
    public void error(Class<?> target, Method method, Throwable e) {
        System.out.println("出现异常了");
        System.out.println("当前类：" + target.getName());
        System.out.println("当前方法：" + method.getName());
    }

}
