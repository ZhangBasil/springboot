package com.xlh.converter;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangbin
 * @Type ReflectAsmUtils
 * @Desc
 * @date 2017-12-19
 * @Version V1.0
 */
public class ReflectAsmUtils {
    private static final Map<Class<?>, MethodAccess> methodAccessCache = new ConcurrentHashMap<>();

    private static final Map<Class<?>, Set<String>> methodNameCache = new ConcurrentHashMap<>();






    public static void copyProperties(Object desc, Object orig) {
        if (Objects.isNull(orig)) {
            return;
        }
        MethodAccess descMethodAccess = getMethodAccess(desc);
        MethodAccess origMethodAccess = getMethodAccess(orig);
//        String[] methodNames = descMethodAccess.getMethodNames();
//        descMethodAccess.invoke(desc, "setName", origMethodAccess.invoke(orig, "getName"));
//        for (String methodName : methodNames) {
//            if (methodName.startsWith("set")) {
//                String substring = methodName.substring(3);
//                System.out.println("substring = " + substring);
//                origMethodAccess.invoke(orig, methodName.replaceFirst("s", "g"));
//                descMethodAccess.invoke(desc, methodName, null);
//                descMethodAccess.invoke(desc, methodName, origMethodAccess.invoke(orig, methodName.replaceFirst("s", "g")));
//            }
//        }




        Set<String> strings = methodNameCache.get(descMethodAccess.getClass());
        strings.forEach(s -> descMethodAccess.invoke(desc, "set" + s, origMethodAccess.invoke(orig, "get" + s)));

//        Method[] methods = desc.getClass().getMethods();
//        for (Method method : methods) {
//            String methodName = method.getName();
//            if (methodName.startsWith("set")) {
//                descMethodAccess.invoke(desc, methodName, origMethodAccess.invoke(orig, methodName.replaceFirst("set","get")));
//            }
//        }
    }


    private static MethodAccess getMethodAccess(Object obj) {
        Class<?> clazz = obj.getClass();

        MethodAccess methodAccess = methodAccessCache.get(clazz);
        if (methodAccess != null) {
            return methodAccess;
        }

        methodAccess = MethodAccess.get(clazz);
        methodAccessCache.put(clazz, methodAccess);
        setMethodNameCache(methodAccess);
        return methodAccess;
    }

    private static void setMethodNameCache(MethodAccess methodAccess) {
        String[] methodNames = methodAccess.getMethodNames();
        Class<?> clazz = methodAccess.getClass();
        Set<String> methodNameList = Stream.of(methodNames).filter(s -> s.startsWith("set")).map(s -> s.substring(3))
                .collect(Collectors.toSet());

        methodNameCache.put(clazz, methodNameList);

    }



}
