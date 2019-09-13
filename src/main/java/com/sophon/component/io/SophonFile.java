package com.sophon.component.io;

import com.google.common.collect.Lists;
import com.sophon.component.security.Security;
import com.sophon.component.security.SecurityManager;
import com.sophon.config.ConfigVo;
import com.sophon.logger.SystemLogger;
import com.sophon.util.StringUtils;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 * @Author tiansheng
 * @Date 2019/8/30 14:55
 * @Description file
 */
public class SophonFile extends File {

    /**
     * 调用该构造函数不会在初始化的时候创建文件
     *
     * @param pathname
     * @param isCreate
     */
    public SophonFile(@NotNull String pathname, boolean isCreate) {
        super(pathname);
    }

    public SophonFile(@NotNull String pathname) {
        super(pathname);
        if (!exists() && ConfigVo.getLoggerPrintWrite()) {
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
     * @return
     */
    public String getSuffix() {
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
     * 创建当前文件
     *
     * @return
     */
    public boolean create() {
        return create(this.getName());
    }

    /**
     * 单独开启一个线程创建文件
     *
     * @param filename
     * @return
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
     * @return
     */
    public ArrayList<String> getFileNamesByFolderNoSuffix() {
        ArrayList<String> names = Lists.newArrayList();
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
     * @return
     */
    public ArrayList<String> getFileNamesByFolder() {
        ArrayList<String> names = Lists.newArrayList();
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
     * @return
     */
    public String getNoSuffixName() {
        String name = this.getName();
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * 创建新的文件名
     *
     * @return
     */
    public String getNewFileName() {
        String name = this.getName();
        String var1 = name.substring(0, name.lastIndexOf("_") + 1);
        String var2 = name.substring(name.lastIndexOf("."));
        return var1 + (getNewestFileIndex() + 1) + var2;
    }

    /**
     * 获取最新的文件索引
     *
     * @return
     */
    public int getNewestFileIndex() {
        // 获取当前文件夹下的所有文件名
        ArrayList<String> names = getFileNamesByFolder();
        int endNumber = 0; // 记录创建到了第几个日志文件了
        // 当前file文件名
        String current = getNoSuffixName();
        current = current.substring(0, current.length() - 2);
        for (String filename : names) {
            if (filename.contains(current)) {
                String index = StringUtils.getS2SChars(filename, "_", ".");
                if (StringUtils.isEmpty(index)) index = "0";
                if (StringUtils.isNumber(index)) {
                    int lastStringToInt = Integer.parseInt(index);
                    // 如果是创建的第2个文件的话,那么就命名为xxx2.log
                    // 因为第一个文件的命名为 xxx.log, 第二个的为 xxx1.log
                    if (lastStringToInt > endNumber) {
                        endNumber = lastStringToInt;
                    }
                } else {
                    continue;
                }
            }
            return endNumber;
        }
        return 0;
    }

    /**
     * 获取最新的文件对象
     *
     * @return
     */
    public SophonFile getNewFileObject() {
        return new SophonFile(getParent().concat("/").concat(getNewFileName()));
    }

    public static SophonFile getFile(@NotNull String pathname) {
        String classpath = "classpath:";
        // 如果是以classpath:开头的路劲,使用当前项目目录
        if (classpath.equals(pathname.substring(0, classpath.length()))) {
            pathname = pathname.replaceAll(classpath, "");
            pathname = System.getProperty("user.dir").concat(pathname);
            pathname = pathname.replaceAll("/", "\\\\");
            SystemLogger.debug(pathname);
        }
        // 获取要操作的文件
        SophonFile file = new SophonFile(
                pathname, false);
        return new SophonFile(file.getParent()
                .concat("/")
                .concat(file.getNoSuffixName())
                .concat("_0")
                .concat(file.getSuffix()));
    }

}
