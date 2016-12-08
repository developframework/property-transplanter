package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

import java.lang.reflect.Array;
import java.util.List;

public class ListToArrayConverter extends AbstractTypeConverter<List<?>, Object[]> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return List.class.isAssignableFrom(sourceType) && targetType.isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<List<?>> sourceInformation, TargetInformation<Object[]> targetInformation) {
        Class<Object> sourceComponentType = (Class<Object>) sourceInformation.getSourceItemType();
        Class<Object> targetComponentType = (Class<Object>) targetInformation.getTargetItemType();

        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        int size = sourceInformation.getSource().size();
        Object[] objs = (Object[]) Array.newInstance(targetComponentType, size);
        for (int i = 0; i < size; i++) {
            SourceInformation<Object> childSourceInformation = super.createSourceInformation(sourceInformation.getSource().get(i), sourceComponentType);
            TargetInformation<Object> childTargetInformation = super.createTargetInformation(targetComponentType);
            objs[i] = typeConverter.convert(typeConverterRegistry, childSourceInformation, childTargetInformation);
        }
        return objs;
    }
}
