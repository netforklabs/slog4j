package example;

import com.sophon.logger.source.Logger;
import com.sophon.util.SophonUtils;
import com.sun.tools.attach.VirtualMachine;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    public static void main(String[] args) throws Throwable {
        Logger.info("你好");
        // VirtualMachine vm = VirtualMachine.attach(SophonUtils.getPID());
        // vm.loadAgent("lib/agent-1.0-SNAPSHOT.jar");
        // vm.detach();
    }

}
