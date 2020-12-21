package org.summer.framework.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ClassUtilTest {
    @Test
    public void getClassSetTest() {
        Set<Class<?>> set = ClassUtil.getClassSet("org.summer");
        Assert.assertFalse(set.isEmpty());
    }
}
