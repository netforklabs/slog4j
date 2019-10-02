package example;

import com.sophon.config.Slog4jFastLoader;
import com.sophon.logger.source.Logger;
import com.sophon.util.TimeCalc;

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
        Experiment e = new Experiment();
        e.debug();
        e.error();
        e.error1("XXX");
    }

    public void test(){
        for(int i=0; i<500000; i++){
            Logger.info("2019-09-29 14:29:57 example.Example | main:16 - [INFO]:Hello{}",i);
        }
    }

}
