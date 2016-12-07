package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;

public class EnumToEnumConverter implements TypeConverter<Enum<?>, Enum<?>>{

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public Enum<?> convert(Enum<?> source, Class<Enum<?>> targetType, AnnotationWrapper annotationWrapper) {
        return null;
    }
}
