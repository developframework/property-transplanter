package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;

public class StringToLongConverter implements TypeConverter<String, Long> {
    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Long.class;
    }

    @Override
    public Long convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<String> sourceInformation, TargetInformation<Long> targetInformation) {
        return sourceInformation.getSource() == null ? null : Long.parseLong(sourceInformation.getSource());
    }
}
