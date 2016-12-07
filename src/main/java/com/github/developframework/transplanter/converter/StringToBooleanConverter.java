package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class StringToBooleanConverter extends AbstractTypeConverter<String, Boolean> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Boolean.class;
    }

    @Override
    public Boolean convert(TypeConverterRegistry typeConverterRegistry, String source, Class<Boolean> targetType, AnnotationWrapper annotationWrapper) {
        return source == null? null : Boolean.parseBoolean(source);
    }
}
