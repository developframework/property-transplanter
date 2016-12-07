package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class AnyToStringConverter extends AbstractTypeConverter<Object, String> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType == String.class;
    }

    @Override
    public String convert(TypeConverterRegistry typeConverterRegistry, Object source, Class<String> targetType, AnnotationWrapper annotationWrapper) {
        return source == null ? null : source.toString();
    }
}
