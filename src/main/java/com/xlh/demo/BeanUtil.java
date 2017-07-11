package com.xlh.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangbin
 * @Type BeanUtil
 * @Desc
 * @date 2017-07-11
 * @Version V1.0
 */
public class BeanUtil {

    public static Map<String,Object> bean2Map(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return null;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            PropertyIgnore annotation = field.getAnnotation(PropertyIgnore.class);
            if (annotation != null && annotation.ignore()) {
                continue;
            }
            String fieldName = field.getName();
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            map.put(fieldName, fieldValue);
        }
        return map;
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.FIELD })
    @Documented
    @interface PropertyIgnore {
        boolean ignore() default false;
    }


    public static void main(String[] args) throws IllegalAccessException {
        User user = new User(1L, "张三", 18);
        Map<String, Object> map = bean2Map(user);
        System.out.println("map = " + map);
    }
}
