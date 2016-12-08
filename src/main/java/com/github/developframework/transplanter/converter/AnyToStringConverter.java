package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class AnyToStringConverter extends AbstractTypeConverter<Object, String> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return targetType == String.class;
    }

    @Override
    public String convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Object> sourceInformation, TargetInformation<String> targetInformation) {
        return sourceInformation.getSource() == null ? null : sourceInformation.getSource().toString();
    }

}
