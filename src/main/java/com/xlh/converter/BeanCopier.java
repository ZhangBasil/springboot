package com.xlh.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author zhangbin
 * @Type BeanCopier
 * @Desc
 * @date 2017-12-18
 * @Version V1.0
 */
public class BeanCopier {
    private BeanCopier() {
    }

    public static <DestType> DestType propertiesCopy(Object orig, DestType dest) {
        if (orig == null || dest == null) {
            throw new RuntimeException("参数不能为null");
        }

        Method[] destMethods = dest.getClass().getMethods();
        for (Method destMethod : destMethods) {
            if (needCopy(destMethod)) {
                MethodCopy methodCopy = new MethodCopy(destMethod);
                methodCopy.copy(orig, dest);
            }
        }
        return dest;
    }

    private static boolean needCopy(Method method) {
        PropertyCopy annotation = method.getAnnotation(PropertyCopy.class);
        if (annotation != null && annotation.ignore())
            return false;
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1
                && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers());
    }

    static class MethodCopy {
        private Method method;
        private String methodName;
        private Method getterMethod;
        private Class<?>[] parameterTypes;

        public MethodCopy(Method destMethod) {
            method = destMethod;
            methodName = destMethod.getName();
            parameterTypes = destMethod.getParameterTypes();
        }

        private void copy(Object orig, Object dest) {
            try {
                this.getterMethod = orig.getClass().getMethod("get" + methodName.substring(3));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("对象【" + orig + "】中缺失【" + methodName.replaceFirst("set", "get") + "()】方法！");
            }
            Object propertyValue = invokeMethod(orig, getterMethod);
            if (propertyValue != null) {
                invokeMethod(dest, method, propertyValue);
            }
        }

        private Object invokeMethod(Object targerObject, Method method, Object... args) {
            if (method == null) {
                throw new RuntimeException("被调用方法不能为空！");
            }
            // 验证参数数量
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != args.length) {
                throw new RuntimeException("方法【" + method + "】的参数数量【" + parameterTypes.length + "】和反射调用时的参数数量【"
                        + args.length + "】不相符，无法完成调用！");
            }

            try {
                method.setAccessible(true);
                return method.invoke(targerObject, args);
            } catch (Throwable e) {
                throw new RuntimeException("调用对象【" + targerObject + "】的【" + method + "】方法，参数为【" + args + "】时发生异常：", e);
            }
        }

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
@interface PropertyCopy {
    /**
     * 被标注方法是否被忽略拷贝
     * @return
     */
    boolean ignore() default false;
}

