package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class EnumToEnumConverter extends AbstractTypeConverter<Enum<?>, Enum<?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Enum.class.isAssignableFrom(sourceType) && Enum.class.isAssignableFrom(targetType);
    }

    @Override
    public Enum<?> convert(TypeConverterRegistry typeConverterRegistry, Enum<?> source, Class<Enum<?>> targetType, AnnotationWrapper annotationWrapper) {
        return source == null ? null : Enum.valueOf((Class) targetType, source.toString());
    }
}
