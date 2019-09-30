package example;

import com.sophon.component.anno.ListenerMethod;
import com.sophon.component.anno.TriggerMethod;

import java.util.ArrayList;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/10/1 4:12
 * @since 1.8
 */
public class Experiment {

    @ListenerMethod(
            trigger = TriggerMethod.DEBUG,
            process = DebugProcess.class
    )
    public void debug(){
        System.out.println("debug........");
    }

    @ListenerMethod(
            trigger = TriggerMethod.ERROR,
            process = ErrorProcess.class
    )
    public void error(){
        ArrayList s = null;
        s.add("xxxx");
    }

    @ListenerMethod(
            trigger = TriggerMethod.ERROR,
            process = ErrorProcess.class
    )
    public void error(String v){
    }

}
