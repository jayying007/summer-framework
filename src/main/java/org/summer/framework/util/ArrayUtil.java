package org.summer.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ArrayUtil {
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
