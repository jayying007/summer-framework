package org.summer.framework.annotation;

import java.lang.annotation.*;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
@Target(ElementType.TYPE) // 只能用于类上
@Retention(RetentionPolicy.RUNTIME) // 运行时存在，可以通过反射读取
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
