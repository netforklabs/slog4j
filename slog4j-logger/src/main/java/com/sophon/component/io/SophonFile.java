package com.sophon.component.io;

import com.sophon.component.security.SecurityManager;
import com.sophon.config.Slog4jConfiguration;
import com.sophon.util.DateUtils;
import com.sophon.util.StringUtils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sophon Logger文件对象
 * @author 2BKeyboard
 * @date 2019/8/30 14:55
 * @version 1.0.0
 * @since 1.8
 */
public class SophonFile extends File {

    /**
     * 调用该构造函数不会在初始化的时候创建文件
     *
     * @param pathname 文件路径
     * @param isCreate 是否在创建对象时创建文件
     */
    public SophonFile(String pathname, boolean isCreate) {
        super(pathname);
    }

    /**
     * 调用此构造函数会在对象初始化时创建文件
     *
     * @param pathname 文件路径
     */
    public SophonFile(String pathname) {
        super(pathname.replaceAll("\\\\", "/"));
        if (!exists() && Slog4jConfiguration.getInstance().getLoggerPrintWrite()) {
            create();
        }
    }

    public SophonFile(String parent, String child) {
        super(parent, child);

    }

    public SophonFile(File parent, String child) {
        super(parent, child);
    }

    public SophonFile(URI uri) {
        super(uri);
    }

    /**
     * 获取文件后缀
     *
     * @return 文件后缀
     */
    public String getSuffix() {
        String suffix = this.getName();
        return suffix.substring(suffix.lastIndexOf("."));
    }

    /**
     * 获取当前文件有多少kb
     *
     * @return 返回大小，以kb为单位
     */
    public long getSizeByKB() {
        return this.length() / 1024;
    }

    /**
     * 创建当前文件
     *
     * @return 返回boolean，表示是否创建成功
     */
    public boolean create() {
        return create(this.getName());
    }

    /**
     * 单独开启一个线程创建文件
     *
     * @param filename 文件名
     * @return 返回boolean，表示是否创建成功
     */
    public boolean create(String filename) {
        File file = new File(this.getParent(), filename);
        try {
            if (!file.getParentFile().exists()) {
                new File(file.getParent()).mkdirs();
            }
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取当前文件夹下所有文件名(不带后缀)
     *
     * @return 返回文件夹下的所有文件名List(文件名不带后缀)
     */
    public ArrayList<String> getFileNamesByFolderNoSuffix() {
        ArrayList<String> names = new ArrayList();
        File[] files = this.getParentFile().listFiles();
        for (File file : files) {
            String name = file.getName();
            names.add(name.substring(0, name.lastIndexOf(".")));
        }
        return names;
    }

    /**
     * 获取当前文件夹下所有文件名(带后缀)
     *
     * @return 返回文件夹下的所有文件名List(文件名带后缀)
     */
    public ArrayList<String> getFileNamesByFolder() {
        ArrayList<String> names = new ArrayList();
        File[] files = this.getParentFile().listFiles();
        for (File file : files) {
            String name = file.getName();
            names.add(name);
        }
        return names;
    }

    /**
     * 获取没有后缀的文件名
     *
     * @return 返回文件名，除去后缀
     */
    public String getNoSuffixName() {
        String name = this.getName();
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * 创建新的文件名
     *
     * @return 返回新的文件名
     */
    public String getNewFileName() {
        String name = this.getName();
        String var1 = name.substring(0, name.lastIndexOf("_") + 1);
        String var2 = name.substring(name.lastIndexOf("."));
        return var1 + getNewestFileIndex() + var2;
    }

    /**
     * 获取当前文件的最新索引
     *
     * @return 返回日志文件当前最新索引
     */
    public int getNewestFileIndex() {
        // 索引
        int index = 0;
        // 当前文件的索引列表
        ArrayList<Integer> indexs = new ArrayList();
        // 当前文件名
        String currentName = getNoSuffixName().substring(0, getNoSuffixName().lastIndexOf("_"));
        SecurityManager security = SecurityManager.getSecurityManager();
        for (String filename : getFileNamesByFolderNoSuffix()) {
            // 获取没有索引的文件名
            if(!security.isLoggerFile(filename)) continue;
            String filenameNoIndex = filename.substring(0, filename.lastIndexOf("_"));
            if (filenameNoIndex.equals(currentName)) {
                String indexstr = filename.substring(filename.lastIndexOf("_") + 1);
                if (StringUtils.isNumber(indexstr)) {
                    indexs.add(Integer.parseInt(indexstr));
                }
            }
        }
        if (!indexs.isEmpty() && indexs.size() >= 1) {
            Collections.sort(indexs, Collections.reverseOrder());
            return (indexs.get(0) + 1);
        }
        return index;
    }

    /**
     * 获取最新的文件对象
     *
     * @return 返回一个最新索引的文件对象
     */
    public SophonFile getNewFileObject() {
        return new SophonFile(getParent().concat("/").concat(getNewFileName()));
    }

    /**
     * 获取一个  SophoFile 对象
     * 该方法的主要作用就是对 pathname 进行操作。
     * 例如 classpath: 关键字就是在这个方法中做解析的。
     *
     * @param pathname 文件路径
     * @return 返回文件对象
     */
    public static SophonFile getFile(String pathname) {
        String classpath = Slog4jConfiguration.pathPrefix;
        // 如果是以classpath:开头的路劲,使用当前项目目录
        if (classpath.equals(pathname.substring(0, classpath.length()))) {
            pathname = pathname.replaceAll(classpath, "");
            pathname = System.getProperty("user.dir").concat(pathname);
            pathname = pathname.replaceAll("\\\\", "/");
        }
        // 获取要操作的文件
        SophonFile file = new SophonFile(
                pathname, false);
        String path = file.getParent()
                .concat("/")
                .concat(file.getNoSuffixName())
                .concat("_0")
                .concat(file.getSuffix());
        return new SophonFile(path);
    }

}
