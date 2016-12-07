package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.util.ArrayList;
import java.util.List;

public class ListToListConverter extends CollectionToCollectionConverter<List<?>, List<?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return List.class.isAssignableFrom(sourceType) && List.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<?> convert(TypeConverterRegistry typeConverterRegistry, List<?> source, Class<List<?>> targetType, AnnotationWrapper annotationWrapper) {
        SourceItemType sourceItemTypeAnnotation = super.getSourceItemTypeAnnotation(annotationWrapper);
        TargetItemType targetItemTypeAnnotation = super.getTargetItemTypeAnnotation(annotationWrapper);
        List list = new ArrayList(source.size());
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceItemTypeAnnotation.value(), targetItemTypeAnnotation.value());
        for (Object item : source) {
            Object obj = typeConverter.convert(typeConverterRegistry, item, (Class<Object>) targetItemTypeAnnotation.value(), new AnnotationWrapper());
            list.add(obj);
        }
        return list;
    }
}
