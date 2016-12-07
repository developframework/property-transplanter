package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.util.ArrayList;
import java.util.List;

public class ArrayToListConverter extends CollectionToCollectionConverter<Object[], List<?>>{


    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType.isArray() && List.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<?> convert(TypeConverterRegistry typeConverterRegistry, Object[] source, Class<List<?>> targetType, AnnotationWrapper annotationWrapper) {
        TargetItemType targetItemTypeAnnotation = super.getTargetItemTypeAnnotation(annotationWrapper);
        Class<Object> sourceComponentType = (Class<Object>)source.getClass().getComponentType();
        List list = new ArrayList(source.length);
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetItemTypeAnnotation.value());
        for (Object item : source) {
            Object obj = typeConverter.convert(typeConverterRegistry, item, (Class<Object>) targetItemTypeAnnotation.value(), new AnnotationWrapper());
            list.add(obj);
        }
        return list;
    }
}
