package org.summer.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
@Target(ElementType.METHOD) // 该注解只能用于方法级别
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {
}
