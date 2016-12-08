package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;

public class StringToBooleanConverter extends AbstractTypeConverter<String, Boolean> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Boolean.class;
    }

    @Override
    public Boolean convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<String> sourceInformation, TargetInformation<Boolean> targetInformation) {
        return sourceInformation.getSource() == null? null : Boolean.parseBoolean(sourceInformation.getSource());
    }
}
