package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.util.HashMap;
import java.util.Map;

public class MapToMapConverter extends AbstractTypeConverter<Map<?, ?>, Map<?, ?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Map.class.isAssignableFrom(sourceType) && Map.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<?, ?> convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Map<?, ?>> sourceInformation, TargetInformation<Map<?, ?>> targetInformation) {
        Class<Object> sourceComponentType = (Class<Object>) sourceInformation.getSourceItemType();
        Class<Object> targetComponentType = (Class<Object>) targetInformation.getTargetItemType();

        Map target = new HashMap(sourceInformation.getSource().size());
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>)typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        for (Map.Entry<?, ?> entry : sourceInformation.getSource().entrySet()) {
            Object obj = sourceInformation.getSource().get(entry.getKey());
            if (obj != null) {
                target.put(entry.getKey(), convertCollectionItem(typeConverterRegistry, typeConverter, sourceComponentType, targetComponentType, obj));
            }
        }
        return target;
    }
}
