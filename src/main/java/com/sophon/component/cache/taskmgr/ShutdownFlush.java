package com.sophon.component.cache.taskmgr;

import com.sophon.component.cache.statics.Store;

/**
 * <h3>slog4j</h3>
 * <p>关闭时刷新缓存</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-10-03 14:40
 **/
public class ShutdownFlush{
    private static boolean built = false;

    public static void build() {
        if (!built) {
            built = true;
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("slog4j :: IO :: Programme stopped. flushing logs cache...");
                    Store.write.flush();
                    System.out.println("slog4j :: SYS :: Good bye!");
                }
            });
        }
    }
}
