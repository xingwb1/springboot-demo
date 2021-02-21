package com.mygit.util.jsonutil;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * JsonUtils
 * <p>
 *
 * @author Ruihong, Han
 * @version 1.0
 * @date 2020/5/18
 */
public class JsonUtils {

    /**
     * A 转换为 B
     *
     * @param object A
     * @param clazz  B.class
     * @return B
     */
    public static <T> T parseObject(Object object, Class<T> clazz) {
        if (Objects.isNull(object)) {
            return null;
        }
        if (object instanceof String) {
            return JSON.parseObject((String) object, clazz);
        } else {
            return JSON.parseObject(JSON.toJSONString(object), clazz);
        }
    }

    /**
     * List<A> 转换为 List<B>
     *
     * @param collection List<A>
     * @param clazz      B.class
     * @return List<B>
     */
    public static <T> List<T> parseList(Collection<?> collection, Class<T> clazz) {
        if (CollectionUtils.isEmpty(collection)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(JSON.toJSONString(collection), clazz);
    }

}
