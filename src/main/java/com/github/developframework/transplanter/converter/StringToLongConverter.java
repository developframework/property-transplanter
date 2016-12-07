package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class StringToLongConverter implements TypeConverter<String, Long> {
    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Long.class;
    }

    @Override
    public Long convert(TypeConverterRegistry typeConverterRegistry, String source, Class<Long> targetType, AnnotationWrapper annotationWrapper) {
        return source == null ? null : Long.parseLong(source);
    }
}
