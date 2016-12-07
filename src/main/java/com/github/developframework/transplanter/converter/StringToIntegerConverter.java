package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class StringToIntegerConverter implements TypeConverter<String, Integer>{
    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Integer.class;
    }

    @Override
    public Integer convert(TypeConverterRegistry typeConverterRegistry, String source, Class<Integer> targetType, AnnotationWrapper annotationWrapper) {
        return source == null ? null : Integer.parseInt(source);
    }
}
