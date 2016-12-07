package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.util.HashMap;
import java.util.Map;

public class MapToMapConverter extends CollectionToCollectionConverter<Map<?, ?>, Map<?, ?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Map.class.isAssignableFrom(sourceType) && Map.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<?, ?> convert(TypeConverterRegistry typeConverterRegistry, Map<?, ?> source, Class<Map<?, ?>> targetType, AnnotationWrapper annotationWrapper) {
        SourceItemType sourceItemTypeAnnotation = super.getSourceItemTypeAnnotation(annotationWrapper);
        TargetItemType targetItemTypeAnnotation = super.getTargetItemTypeAnnotation(annotationWrapper);
        Map target = new HashMap(source.size());
        for (Map.Entry<?, ?> entry : source.entrySet()) {
            TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>)typeConverterRegistry.extractTypeConverter(sourceItemTypeAnnotation.value(), targetItemTypeAnnotation.value());
            Object obj = source.get(entry.getKey());
            if (obj != null) {
                target.put(entry.getKey(), typeConverter.convert(typeConverterRegistry, obj, (Class<Object>) targetItemTypeAnnotation.value(), new AnnotationWrapper()));
            }
        }
        return target;
    }
}
