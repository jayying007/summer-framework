package org.summer.framework.bean;

import org.summer.framework.util.CastUtil;
import org.summer.framework.util.CollectionUtil;

import java.util.Map;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }


    /**
     * 校验参数为空
     * @return
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(this.paramMap);
    }
}
