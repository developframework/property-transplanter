package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.TypeConverter;

public class AnyToAnyConverter implements TypeConverter<Object, Object>{

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType.isAssignableFrom(sourceType);
    }

    @Override
    public Object convert(Object source, Class<Object> targetType) {
        return source;
    }
}
