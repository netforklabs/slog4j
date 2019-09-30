package com.sophon.config;

import com.keyboard.register.ListenerMethodEntity;
import com.sophon.component.SophonInit;
import com.sophon.component.anno.TriggerMethod;
import com.sophon.component.exception.UncheckedExceptionHandler;
import com.sophon.component.hot.ListenerMethodProcessor;
import com.sophon.component.hot.ReDefineClass;
import com.sophon.util.SophonUtils;
import com.sun.tools.attach.VirtualMachine;

import java.util.Iterator;
import java.util.List;

/**
 * slog4j快速加载器，用于初始化
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/28 15:44
 * @since 1.8
 */
public class Slog4jFastLoader implements SophonInit {

    @Override
    public void init() {
        try {
            // 加载AgentJAR
            VirtualMachine vm = VirtualMachine.attach(SophonUtils.getPID());
            vm.loadAgent("lib/slog4j-weaving-1.0.0.CLV.jar");
            vm.detach();
            // 扫描ListenerMethod注解并执行编织操作
            List<ListenerMethodEntity> entities = ListenerMethodProcessor.getLinstenerMethods();
            // 将ERROR类型交给异常捕获处理器处理
            Iterator<ListenerMethodEntity> iterator = entities.iterator();
            while(iterator.hasNext()){
                ListenerMethodEntity entity = iterator.next();
                if(TriggerMethod.ERROR.toString().equals(entity.getTriggerMethod())){
                    UncheckedExceptionHandler.registerListener(entity);
                    iterator.remove();
                }
            }
            ReDefineClass rdc = new ReDefineClass();
            rdc.listenerMethodHotReplacement(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
