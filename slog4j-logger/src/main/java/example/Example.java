package example;

import com.keyboard.register.ListenerMethodEntity;
import com.keyboard.register.ListenerMethodManager;
import com.sophon.component.hot.ListenerMethodProcessor;
import com.sophon.util.SophonUtils;
import com.sun.tools.attach.VirtualMachine;
import future.Test;

import java.util.List;

/**
 * @author tiansheng
 * @version 1.0.0
 * @date 2019/9/28 20:10
 * @since 1.8
 */
public class Example {

    static {
        try {
            System.out.println("static-===============");
            VirtualMachine vm = VirtualMachine.attach(SophonUtils.getPID());
            vm.loadAgent("lib/slog4j-scanner-1.0.0.CLV.jar");
            vm.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Throwable {
        System.out.println("---------------");
        List<ListenerMethodEntity> entitys = ListenerMethodProcessor.getLinstenerMethods();
        for (ListenerMethodEntity entity : entitys) {
            ListenerMethodManager.put(entity.getClasspath(),entity);
        }
        Test.test("this is a",12,true);
        System.out.println();
    }

}
