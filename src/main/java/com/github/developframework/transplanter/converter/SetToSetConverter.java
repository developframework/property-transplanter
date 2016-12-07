package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.util.HashSet;
import java.util.Set;

public class SetToSetConverter extends CollectionToCollectionConverter<Set<?>, Set<?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Set.class.isAssignableFrom(sourceType) && Set.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<?> convert(TypeConverterRegistry typeConverterRegistry, Set<?> source, Class<Set<?>> targetType, AnnotationWrapper annotationWrapper) {
        SourceItemType sourceItemTypeAnnotation = super.getSourceItemTypeAnnotation(annotationWrapper);
        TargetItemType targetItemTypeAnnotation = super.getTargetItemTypeAnnotation(annotationWrapper);
        Set set = new HashSet(source.size());
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceItemTypeAnnotation.value(), targetItemTypeAnnotation.value());
        for (Object item : source) {
            Object obj = typeConverter.convert(typeConverterRegistry, item, (Class<Object>) targetItemTypeAnnotation.value(), new AnnotationWrapper());
            set.add(obj);
        }
        return set;
    }
}
