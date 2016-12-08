package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;

public class StringToIntegerConverter implements TypeConverter<String, Integer>{
    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Integer.class;
    }

    @Override
    public Integer convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<String> sourceInformation, TargetInformation<Integer> targetInformation) {
        return sourceInformation.getSource() == null ? null : Integer.parseInt(sourceInformation.getSource());
    }
}
