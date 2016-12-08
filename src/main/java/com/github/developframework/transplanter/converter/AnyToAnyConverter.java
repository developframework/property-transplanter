package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;

public class AnyToAnyConverter extends AbstractTypeConverter<Object, Object>{

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType.isAssignableFrom(sourceType);
    }

    @Override
    public Object convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Object> sourceInformation, TargetInformation<Object> targetInformation) {
        return sourceInformation.getSource();
    }

}
