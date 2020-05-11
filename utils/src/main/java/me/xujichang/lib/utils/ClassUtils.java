package me.xujichang.lib.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * me.xujichang.lib.activities.util in Activities
 * description:
 * <p>
 *
 * @author xujichang at 2020/5/7 5:26 PM
 */
public class ClassUtils {

    public static <C> Class<C> getVMClass(Class<?> pClass, int index) {
        ParameterizedType type = (ParameterizedType) pClass.getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            Class<C> tClass = (Class<C>) actualTypeArguments[index];
            return tClass;
        }
        return null;
    }
}
