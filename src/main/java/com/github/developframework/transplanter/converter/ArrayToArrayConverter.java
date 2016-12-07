package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

import java.lang.reflect.Array;

public class ArrayToArrayConverter extends CollectionToCollectionConverter<Object[], Object[]> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType.isArray() && targetType.isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] convert(TypeConverterRegistry typeConverterRegistry, Object[] source, Class<Object[]> targetType, AnnotationWrapper annotationWrapper) {

        Class<Object> sourceComponentType = (Class<Object>)source.getClass().getComponentType();
        Class<Object> targetComponentType = (Class<Object>)targetType.getComponentType();

        Object[] objs = (Object[]) Array.newInstance(targetComponentType, source.length);
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        for (int i = 0; i < source.length; i++) {
            objs[i] = typeConverter.convert(typeConverterRegistry, source[i], targetComponentType, new AnnotationWrapper());
        }
        return objs;
    }
}
