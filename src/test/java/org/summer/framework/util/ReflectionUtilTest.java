package org.summer.framework.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.controller.UserController;
import org.summer.framework.bean.Param;

import java.lang.reflect.Method;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class ReflectionUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtilTest.class);

    @Test
    public void invokeMethodTest() throws NoSuchMethodException {
        UserController obj = new UserController();
        Method method = obj.getClass().getMethod("index", Param.class);

        Param param = new Param(null);

        Object result = ReflectionUtil.invokeMethod(obj, method, param);
        logger.info("反射结果:" + result.toString());
    }
}
