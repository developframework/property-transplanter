package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.SourceItemType;

import java.lang.reflect.Array;
import java.util.List;

public class ListToArrayConverter extends CollectionToCollectionConverter<List<?>, Object[]>{

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return List.class.isAssignableFrom(sourceType) && targetType.isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] convert(TypeConverterRegistry typeConverterRegistry, List<?> source, Class<Object[]> targetType, AnnotationWrapper annotationWrapper) {
        SourceItemType sourceItemTypeAnnotation = super.getSourceItemTypeAnnotation(annotationWrapper);
        Class<Object> targetComponentType = (Class<Object>)targetType.getComponentType();
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceItemTypeAnnotation.value(), targetComponentType);
        Object[] objs = (Object[]) Array.newInstance(targetComponentType, source.size());
        for (int i = 0; i < source.size(); i++) {
            objs[i] = typeConverter.convert(typeConverterRegistry, source.get(i), targetComponentType, new AnnotationWrapper());
        }
        return objs;
    }
}
