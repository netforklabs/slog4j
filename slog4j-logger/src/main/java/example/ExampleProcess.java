package example;

import com.sophon.component.hot.SophonListener;

import java.lang.reflect.Method;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/10/2 2:05
 * @since 1.8
 */
public class ExampleProcess implements SophonListener {

    @Override
    public void before(Class<?> target, String method, Object[] args) {
        System.err.println("example process before............");
    }

    @Override
    public void after(Class<?> target, String method, Object[] args) {
        System.err.println("example process after............");
    }

    @Override
    public void error(Class<?> target, Method method, Throwable e) {

    }

}
