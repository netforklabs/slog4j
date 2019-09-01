package com.sophon.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

/**
 * @Author Mr.luo
 * @Date 2019/8/30 14:55
 * @Description file
 */
public class SophonFiles extends File {

    public SophonFiles(String pathname) {
        super(pathname);
    }

    public SophonFiles(String parent, String child) {
        super(parent, child);
    }

    public SophonFiles(File parent, String child) {
        super(parent, child);
    }

    public SophonFiles(URI uri) {
        super(uri);
    }

    /**
     * 获取文件后缀
     * @return
     */
    public String getSuffix(){
        String suffix = this.getName();
        return suffix.substring(suffix.lastIndexOf("."));
    }

    /**
     * 获取当前文件有多少kb
     *
     * @return
     */
    public long getSizeByKB() {
        return this.length() / 1024;
    }

    /**
     * 在当前文件夹下创建文件
     * @param filename
     * @return
     */
    public boolean create(String filename){
        File file = new File(this.getParent(),filename);
        try{
            return file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建当前文件
     * @return
     */
    public boolean create(){
        return create(this.getName());
    }

    /**
     * 获取当前文件夹下所有文件名(不带后缀)
     * @return
     */
    public ArrayList<String> getFileNamesByFolder(){
        ArrayList<String> names = Lists.newArrayList();
        File[] files = this.getParentFile().listFiles();
        for(File file : files){
            String name = file.getName();
            names.add(name.substring(0,name.lastIndexOf(".")));
        }
        return names;
    }

    /**
     * 获取当前文件夹下所有文件名(不带后缀)
     * @return
     */
    public String getNoSuffixName(){
        String name = this.getName();
        return name.substring(0,name.lastIndexOf("."));
    }

}
