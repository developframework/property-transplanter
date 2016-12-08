package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

import java.util.ArrayList;
import java.util.List;

public class ArrayToListConverter extends AbstractTypeConverter<Object[], List<?>>{


    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType.isArray() && List.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<?> convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Object[]> sourceInformation, TargetInformation<List<?>> targetInformation) {
        Class<Object> sourceComponentType = (Class<Object>) sourceInformation.getSourceItemType();
        Class<Object> targetComponentType = (Class<Object>) targetInformation.getTargetItemType();

        List list = new ArrayList(sourceInformation.getSource().length);
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        for (Object item : sourceInformation.getSource()) {
            SourceInformation<Object> childSourceInformation = createSourceInformation(item, sourceComponentType);
            TargetInformation<Object> childTargetInformation = createTargetInformation(targetComponentType);
            Object obj = typeConverter.convert(typeConverterRegistry, childSourceInformation, childTargetInformation);
            list.add(obj);
        }
        return list;
    }
}
