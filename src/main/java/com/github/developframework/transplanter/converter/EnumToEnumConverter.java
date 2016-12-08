package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverterRegistry;

public class EnumToEnumConverter extends AbstractTypeConverter<Enum<?>, Enum<?>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Enum.class.isAssignableFrom(sourceType) && Enum.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enum<?> convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Enum<?>> sourceInformation, TargetInformation<Enum<?>> targetInformation) {
        return sourceInformation.getSource() == null ? null : Enum.valueOf((Class) targetInformation.getTargetType(), sourceInformation.getSource().toString());
    }
}
