package com.xlh.converter;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.sun.xml.internal.ws.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author zhangbin
 * @Type BeanUtils
 * @Desc
 * @date 2017-12-18
 * @Version V1.0
 */
public class BeanUtils {
    private static final Map<Class, MethodAccess> methodMap = new ConcurrentHashMap<>();

    private static final Map<String, Integer> methodIndexMap = new ConcurrentHashMap<>();

    private static final Map<Class, List<String>> fieldMap = new ConcurrentHashMap<>();

    public static void copyProperties(Object desc, Object orig) {
        MethodAccess descMethodAccess = methodMap.get(desc.getClass());
        if (descMethodAccess == null) {
            descMethodAccess = cache(desc);
        }
        MethodAccess orgiMethodAccess = methodMap.get(orig.getClass());
        if (orgiMethodAccess == null) {
            orgiMethodAccess = cache(orig);
        }

        List<String> fieldList = fieldMap.get(orig.getClass());
        for (String field : fieldList) {
            String getKey = orig.getClass().getName() + "." + "get" + field;
            String setKey = desc.getClass().getName() + "." + "set" + field;
            Integer setIndex = methodIndexMap.get(setKey);
            if (setIndex != null) {
                int getIndex = methodIndexMap.get(getKey);
                descMethodAccess.invoke(desc, setIndex, orgiMethodAccess.invoke(orig, getIndex));
            }
        }

    }

    private static MethodAccess cache(Object orgi) {
        MethodAccess methodAccess = MethodAccess.get(orgi.getClass());
        Field[] fields = orgi.getClass().getDeclaredFields();
        List<String> fieldList = new LinkedList<>();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                // 非公共私有变量
                String fieldName = StringUtils.capitalize(field.getName());
                int getIndex = methodAccess.getIndex("get" + fieldName);
                int setIndex = methodAccess.getIndex("set" + fieldName);
                methodIndexMap.put(orgi.getClass().getName() + "." + "get" + fieldName, getIndex);
                methodIndexMap.put(orgi.getClass().getName() + "." + "set" + fieldName, setIndex);
                fieldList.add(fieldName);
            }
        }
        fieldMap.put(orgi.getClass(), fieldList);
        methodMap.put(orgi.getClass(), methodAccess);
        return methodAccess;
    }
}
