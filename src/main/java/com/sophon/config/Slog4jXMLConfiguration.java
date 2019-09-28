package com.sophon.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 解析XML配置
 * @author     private DateUtils() {
    private DateUtils() {
2BKeyboard
 * @version 1.0.0
 * @date 2019/9/28 15:44
 * @since 1.8
 */
public class Slog4jXMLConfiguration {

    public static void main(String[] args) {
        Document doc = getDocument();
        NodeList nodeList = doc.getElementsByTagName("listener");
        for(int i=0,len=nodeList.getLength(); i<len; i++) {
            System.out.println(nodeList.item(i).getLocalName());
        }
    }

    public static Document getDocument(){
        Document document = null;
        try {
            DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docf.newDocumentBuilder();
            document = builder.parse(new File(System.getProperty("user.dir") + "/src/main/resources/listener.xml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return document;
    }

}
