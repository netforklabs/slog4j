import com.sophon.component.anno.Separation;
import com.sophon.component.logger.LoggerFactory;
import com.sophon.logger.SeparationLogger;
import com.sophon.logger.SophonLogger;
import com.sophon.logger.SophonLoggerImpl;
import com.sophon.logger.source.ExceptionLogger;
import com.sophon.logger.source.Logger;
import com.sophon.logger.source.SystemLogger;

import java.util.ArrayList;

/**
 * @author 2BKeyboard
 * @Date 2019/9/16 2:38
 * @Description TODO
 */
public class Example3 {

    @Separation("classpath:/example/example.log")
    private static SeparationLogger log;

    static{
        LoggerFactory.injection();
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            Logger.info("are you ok?");
        }
        long stop = System.nanoTime();
        System.out.println(start-stop);
        /*
        SystemLogger.info("are you ok?");
        ExceptionLogger.info("are you ok?");
        log.info("are you ok?");
        */
    }

}
