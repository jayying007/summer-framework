package org.summer.framework.proxy;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public interface Proxy {
    // 执行链式代理
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
