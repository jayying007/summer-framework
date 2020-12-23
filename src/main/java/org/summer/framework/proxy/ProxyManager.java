package org.summer.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ProxyManager {
    /**
     * 创建代理
     * @param targetClass
     * @param proxyList
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<T> targetClass, final List<Proxy> proxyList) {
        // 使用CGLib提供的Enhance#create方法来创建代理对象
        // 将intercept的参数传入ProxyChain的构造器中
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObj, method, methodProxy, objects, proxyList).doProxyChain();
            }
        });
    }
}
