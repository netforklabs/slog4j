package com.sophon.config;

import com.keyboard.register.ListenerMethodEntity;
import com.sophon.component.SophonInit;
import com.sophon.component.hot.ListenerMethodProcessor;
import com.sophon.component.hot.ReDefineClass;
import com.sophon.util.SophonUtils;
import com.sun.tools.attach.VirtualMachine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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
            vm.loadAgent("lib/slog4j-scanner-1.0.0.CLV.jar");
            vm.detach();
            // 扫描ListenerMethod注解并执行编织操作
            List<ListenerMethodEntity> entitys = ListenerMethodProcessor.getLinstenerMethods();
            ReDefineClass rdc = new ReDefineClass();
            rdc.listenerMethodHotReplacement(entitys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
