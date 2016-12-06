package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.PropertyTransplanter;
import com.github.developframework.transplanter.TypeConverter;

import java.lang.reflect.Array;

public class ArrayToArrayConverter implements TypeConverter<Object[], Object[]> {

    private PropertyTransplanter propertyTransplanter;

    public ArrayToArrayConverter(PropertyTransplanter propertyTransplanter) {
        this.propertyTransplanter = propertyTransplanter;
    }

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType.isArray() && targetType.isArray();
    }

    @Override
    public Object[] convert(Object[] source, Class<Object[]> targetType) {
        Class<?> targetComponentType = targetType.getComponentType();

        Object[] objs = (Object[]) Array.newInstance(targetComponentType, source.length);
        for (int i = 0; i < source.length; i++) {
            objs[i] = propertyTransplanter.propertyTransplant(source[i], targetComponentType);
        }
        return objs;
    }
}
