package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class AnyToAnyConverter extends AbstractTypeConverter<Object, Object>{

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType.isAssignableFrom(sourceType);
    }

    @Override
    public Object convert(TypeConverterRegistry typeConverterRegistry, Object source, Class<Object> targetType, AnnotationWrapper annotationWrapper) {
        return source;
    }
}
