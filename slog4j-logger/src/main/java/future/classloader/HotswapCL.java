package future.classloader;

import com.sophon.logger.SophonLogger;

import java.io.*;
import java.util.HashSet;

/**
 * slog4j类加载器
 *
 * @author 2BKeyboard
 * @version 1.0.0
 * @date 2019/9/26 16:00
 * @since 1.8
 */
public class HotswapCL extends ClassLoader {

    private ClassLoader parent;

    public HotswapCL(ClassLoader parent) {
        this.parent = parent;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, false);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = this.findLoadedClass(name);
        if (null != parent)
            clazz = parent.loadClass(name);
        if (null == clazz)
            this.findSystemClass(name);
        if (null == clazz)
            throw new ClassNotFoundException();
        if (resolve)
            this.resolveClass(clazz);
        return clazz;
    }

}
