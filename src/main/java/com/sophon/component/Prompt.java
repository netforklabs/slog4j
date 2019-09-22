package com.sophon.component;

import com.alibaba.fastjson.JSONObject;
import com.sophon.config.Slog4jConfiguration;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author tiansheng
 * @Date 2019/9/13 2:24
 * @Description 语言
 */
public class Prompt {

    private static JSONObject ali;

    static {
        try {
            InputStream is = new FileInputStream(Slog4jConfiguration.getInstance().getSlog4jSystemPromptLanguage().concat(".JSON"));
            String json = IOUtils.toString(is,"utf8");
            ali = JSONObject.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 SecurityManager 类中第46行提示
     * @return
     */
    public static String securityManagerCheckSophonFileUnknownError(){
        return ali.getString("SecurityManager.checkSophonFile.UnknownError");
    }

}
