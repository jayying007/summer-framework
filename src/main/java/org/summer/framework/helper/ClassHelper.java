package org.summer.framework.helper;

import org.summer.framework.annotation.Controller;
import org.summer.framework.annotation.Service;
import org.summer.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ClassHelper {
    /**
     * 定义类集合（用于存放所加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下的所有 Service 类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(Service.class)) {
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下的所有 Controller 类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(Controller.class)) {
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下的所有 Bean 类（包括 Service、Controller 等）
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }
}
