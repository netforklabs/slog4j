package example;

import com.sophon.config.Slog4jFastLoader;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    static {
        new Slog4jFastLoader().init();
    }

    public static void main(String[] args) throws Throwable {

    }

}
