package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.TypeConverter;

public class AnyToStringConverter implements TypeConverter<Object, String> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType == String.class;
    }

    @Override
    public String convert(Object source, Class<String> targetType) {
        return source == null ? null : source.toString();
    }
}
