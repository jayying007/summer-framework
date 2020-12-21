package org.summer.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.framework.annotation.Aspect;
import org.summer.framework.annotation.Controller;
import org.summer.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
@Aspect(value = Controller.class)
public class TimeCostAspect extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(TimeCostAspect.class);
    private long startTime;

    @Override
    public void before(Class<?> clz, Method method, Object[] params) throws Throwable {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> clz, Method method, Object[] params) throws Throwable {
        long endTime = System.currentTimeMillis();

        logger.debug(clz.getName() + "." + method.getName() + " 耗时：" + (endTime - startTime) + "ms");
    }
}
