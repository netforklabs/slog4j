package com.sophon.component.cache.taskmgr;

import com.sophon.config.Slog4jConfiguration;
import com.sophon.logger.SophonLogger;
import com.sophon.component.cache.statics.Store;
import com.sophon.util.AdvancedReplace;
import com.sophon.util.TimeUtils;

/**
 * <h3>slog4j</h3>
 * <p>分析应该咋输出</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-10-01 00:04
 **/
public class LogAnalyze extends Thread {
    Object[] params = null;
    Thread t = null;

    public LogAnalyze(Object[] params, Thread t) {
        this.params = params;
        this.t = t;
    }

    private static String format(String v, Object... args) {
        v = v.replaceAll(Store.formatString, "%s");
        v = String.format(v, args);
        return v;
    }

    /**
     * 集合方法，根据参数执行不同的日志操作。
     * 固定参数数组大小：5
     *
     * <h2>Object v</h2>
     * <p>传入一个未知类，转换为字符串，输出</p>
     * <h2>String v</h2>
     * <p>传入字符串，输出</p>
     * <h2>String v, Object... args</h2>
     * <p>传入字符串和自定义参数，输出</p>
     * <h2>String v, Thread t</h2>
     * <p>传入字符串和线程信息，输出</p>
     * <h2>String v, Thread t, Object... args</h2>
     * <p>传入字符串、线程信息和自定义参数，输出</p>
     */

    /**
     * 每个参数对应一个下标：
     * | --- | -------------- |
     * | 下标 | 参数           |
     * | 0   | Object v       |
     * | 1   | String v       |
     * | 2   | Thread t       |
     * | 3   | Object... args |
     * | 4   | Level          |
     * | --- | -------------- |
     */
    @Override
    public void run() {
        // !!! 顺序不得更改，死的很惨 !!!
        if (params[1] != null && params[2] != null && params[3] != null) {
            // 传入字符串、线程信息和自定义参数，输出
            String v = (String) params[1];
            Thread t = (Thread) params[2];
            Object[] args = (Object[]) params[3];
            console(prefixGenerate((SophonLogger.Level) params[4], t).concat(format(v, args)), (SophonLogger.Level) params[4]);
        } else if (params[1] != null && params[3] != null) {
            // 传入字符串和自定义参数，输出
            String v = (String) params[1];
            Object[] args = (Object[]) params[3];
            console(prefixGenerate((SophonLogger.Level) params[4]).concat(format(v, args)), (SophonLogger.Level) params[4]);
        } else if (params[1] != null && params[2] != null) {
            // 传入字符串和线程信息，输出
            String v = (String) params[1];
            Thread t = (Thread) params[2];
            console(prefixGenerate((SophonLogger.Level) params[4], t).concat(v), (SophonLogger.Level) params[4]);
        } else if (params[1] != null) {
            // 传入字符串，输出
            String v = (String) params[1];
            console(prefixGenerate((SophonLogger.Level) params[4]).concat(v), (SophonLogger.Level) params[4]);
        } else if (params[0] != null) {
            // 传入一个未知类，转换为字符串，输出
            Object v = params[0];
            console(prefixGenerate((SophonLogger.Level) params[4]).concat(String.valueOf(v)), (SophonLogger.Level) params[4]);
        }
    }

    /**
     * 获得线程信息（缓存系统）
     * 缓存每10000毫秒（10秒钟）刷新一次
     * 用于应对高并发的情况
     *
     * @param threadName
     * @param threadGroup
     * @return
     */
    private String[] getThreadFeature(String threadName, ThreadGroup threadGroup, Thread t) {
        String feature = (threadName + "&" + threadGroup);
        String[] featureVal = Store.featureCache.get(feature);
        String[] params = null;
        if (Store.disableCache || featureVal == null || ((System.currentTimeMillis() - Long.valueOf(featureVal[3])) > 10000)) {
            /**
             * 基于线程特征的过期缓存机制：
             * | --- | ----------------- |
             * | 下标 | 参数              |
             * | 0   | Class Name        |
             * | 1   | Method Name       |
             * | 2   | Line Number       |
             * | 3   | Cached Time Stamp |
             * | --- | ----------------- |
             */
            params = new String[]{
                    t.getStackTrace()[Store.trace].getClassName(),
                    t.getStackTrace()[Store.trace].getMethodName(),
                    null,
                    String.valueOf(System.currentTimeMillis())
            };
            // 因HashMap原理，缓存过期的键值会直接被覆盖，无需手动删除。
            Store.featureCache.put(feature, params);
            // 为确保追踪精确，行号不可缓存！
            params[2] = String.valueOf(t.getStackTrace()[Store.trace].getLineNumber());
        } else {
            params = featureVal;
            params[2] = String.valueOf(t.getStackTrace()[Store.trace].getLineNumber());
        }
        return params;
    }

    public String prefixGenerate(SophonLogger.Level level) {
        return prefixGenerate(level, t);
    }

    /**
     * 性能基准测试
     * 测试1：禁用栈缓存 && 使用replace && 无时间缓存 && 无文件缓存
     * 测试2：启用栈缓存 && 使用replace && 无时间缓存 && 无文件缓存
     * 测试3：启用栈缓存 && 使用AdvancedReplace && 无时间缓存 && 无文件缓存
     * 测试4：启用栈缓存 && 使用AdvancedReplace && 启用时间缓存 && 无文件缓存
     * 测试5：启用栈缓存 && 使用AdvancedReplace && 启用时间缓存 && 启用文件缓存，值为256
     * 测试6：启用栈缓存 && 使用AdvancedReplace && 启用时间缓存 && 启用文件缓存，值为1024
     * 测试7：启用栈缓存 && 使用AdvancedReplace && 启用时间缓存 && 启用文件缓存，值为8192
     * | ------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
     * | 测试次数 | 测试1 | 测试2 | 测试3 | 测试4 | 测试5 | 测试6 | 测试7 |
     * | ------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
     * | 1       | 5495 | 3644 | 3228 | 2815 | 2428 | 2236 | 2274 |
     * | 2       | 5378 | 3470 | 3116 | 2744 | 2432 | 2371 | 2141 |
     * | 3       | 5127 | 3380 | 3055 | 3148 | 2300 | 2487 | 2194 |
     * | 4       | 5256 | 3432 | 3157 | 2794 | 2277 | 2333 | 2561 |
     * | 5       | 5171 | 3459 | 3159 | 3220 | 2391 | 2364 | 2285 |
     * | 6       | 4981 | 3438 | 3135 | 2845 | 2559 | 2204 | 2249 |
     * | 7       | 4900 | 3428 | 3310 | 2875 | 2546 | 2268 | 2214 |
     * | 8       | 5073 | 3464 | 3228 | 2724 | 2372 | 2299 | 2223 |
     * | 9       | 4769 | 3372 | 3172 | 2819 | 2371 | 2302 | 2227 |
     * | 10      | 4680 | 3574 | 3118 | 2821 | 2418 | 2288 | 2232 |
     * | ------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
     * | 平均成绩 | 5083 | 3467 | 3168 | 2881 | 2410 | 2316 | 2260 |
     *
     * @param level
     * @param t
     * @return
     */
    public String prefixGenerate(SophonLogger.Level level, Thread t) {
        String[] feature = getThreadFeature(t.getName(), t.getThreadGroup(), t);
        return AdvancedReplace.replace(
                Store.printTemplate,
                "${line}", feature[2],
                "${class}", feature[0],
                "${level}", String.valueOf(level),
                "${method}", feature[1],
                "${datetime}", TimeUtils.getTime()
        );
        /* return Store.printTemplate.replace("${line}", feature[2])
                .replace("${class}", feature[0])
                .replace("${level}", String.valueOf(level))
                .replace("${method}", feature[1])
                .replace("${datetime}", new SimpleDateFormat("yyyyMMdd").format(new Date())); */
    }

    protected synchronized void console(String v, SophonLogger.Level level) {
        // 没有被忽略的级别才进入输出
        if (!Store.printIgnore.contains(level)) {
            if (Slog4jConfiguration.getInstance().PERMIT_CONSOLE.isEmpty() || Slog4jConfiguration.getInstance().PERMIT_CONSOLE.equals("yes")) {
                System.out.println(v);
            }
            Slog4jConfiguration.getInstance().printPlus();
            if (Slog4jConfiguration.getInstance().getLoggerPrintWrite()) {
                if (!Store.writeIgnore.contains(level)) {
                    // 输出到日志文件
                    Store.write.write(v);
                }
            }
        }
    }
}
