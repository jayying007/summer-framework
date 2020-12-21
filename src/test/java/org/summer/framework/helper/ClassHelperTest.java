package org.summer.framework.helper;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ClassHelperTest {
    @Test
    public void getClassSetTest() {
        Set<Class<?>> set = ClassHelper.getClassSet();
        Assert.assertFalse(set.isEmpty());
    }
    @Test
    public void getControllerClassSet() {
        Set<Class<?>> set = ClassHelper.getControllerClassSet();
        Assert.assertEquals(set.size(), 1);
    }
}
