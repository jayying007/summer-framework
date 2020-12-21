package org.summer.framework;

import org.summer.framework.helper.*;
import org.summer.framework.util.ClassUtil;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class HelperLoader {
    public static void init() {
        // AopHelper要在IocHelper之前加载
        // 因为首先需要通过AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName(), true);
        }
    }
}
