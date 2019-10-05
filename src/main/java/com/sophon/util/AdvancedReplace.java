package com.sophon.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <h3>slog4j</h3>
 * <p>假装很厉害的Replace工具</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-10-03 12:39
 **/
public class AdvancedReplace {
    public static String replace(String... condition) {
        String text = condition[0];
        for (int i = 1; i <= condition.length - 1; i += 2) {
            text = StringUtils.replace(text, condition[i], condition[i+1]);
        }
        return text;
    }
}
