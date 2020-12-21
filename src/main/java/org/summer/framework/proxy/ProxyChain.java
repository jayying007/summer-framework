package org.summer.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ProxyChain {
    private final Class<?> targetClass; // 目标类
    private final Object targetObject; // 目标对象
    private final Method targetMethod; //目标方法
    private final MethodProxy methodProxy; //方法代理
    private final Object[] methodParams; //方法参数
    private List<Proxy> proxyList = new ArrayList<>(); //代理列表
    private int proxyIndex = 0; // 代理索引

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) { // 根据传进来的代理列表
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            // 执行目标对象的业务逻辑
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
