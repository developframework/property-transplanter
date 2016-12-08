package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;

import java.lang.reflect.Array;

public class ArrayToArrayConverter extends AbstractTypeConverter<Object[], Object[]> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType.isArray() && targetType.isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Object[]> sourceInformation, TargetInformation<Object[]> targetInformation) {
        Class<Object> sourceComponentType = (Class<Object>) sourceInformation.getSourceItemType();
        Class<Object> targetComponentType = (Class<Object>) targetInformation.getTargetItemType();

        Object[] source = sourceInformation.getSource();
        int size = sourceInformation.getSource().length;
        Object[] objs = (Object[]) Array.newInstance(targetComponentType, size);
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        for (int i = 0; i < size; i++) {
            objs[i] = convertCollectionItem(typeConverterRegistry, typeConverter, sourceComponentType, targetComponentType, source[i]);
        }
        return objs;
    }
}
