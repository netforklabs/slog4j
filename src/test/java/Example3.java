import com.sophon.logger.source.ExceptionLogger;
import com.sophon.logger.source.Logger;
import com.sophon.logger.source.SystemLogger;

import java.util.ArrayList;

/**
 * @author tiansheng
 * @Date 2019/9/16 2:38
 * @Description TODO
 */
public class Example3 {

    public static void main(String[] args) {
        Logger.info("Sophon Logger INFO");
        Logger.debug("Sophon Logger DEBUG");
        Logger.error("Sophon Logger ERROR");
        Logger.warn("Sophon Logger WARN");

        SystemLogger.info("Sophon Logger INFO");
        SystemLogger.debug("Sophon Logger DEBUG");
        SystemLogger.error("Sophon Logger ERROR");
        SystemLogger.warn("Sophon Logger WARN");

        new Example3().test();

        ExceptionLogger.info("Sophon Logger INFO");
        ExceptionLogger.debug("Sophon Logger DEBUG");
        ExceptionLogger.error("Sophon Logger ERROR");
        ExceptionLogger.warn("Sophon Logger WARN");

    }

    public void test(){
        ArrayList a = null;
        a.add("aa");
    }

}
