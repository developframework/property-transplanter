package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.TypeConverter;

public class StringToIntegerConverter implements TypeConverter<String, Integer>{
    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Integer.class;
    }

    @Override
    public Integer convert(String source, Class<Integer> targetType) {
        return source == null ? null : Integer.parseInt(source);
    }
}
