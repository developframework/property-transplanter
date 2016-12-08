package com.github.developframework.transplanter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyTransplanter {

    private static final Logger log = LoggerFactory.getLogger(PropertyTransplanter.class);

    private TypeConverterRegistry typeConverterRegistry;

    public PropertyTransplanter() {
        this(new DefaultTypeConverterRegistry());
        ((DefaultTypeConverterRegistry) this.typeConverterRegistry).registerDefaultTypeConverter();
    }

    public PropertyTransplanter(TypeConverterRegistry typeConverterRegistry) {
        this.typeConverterRegistry = typeConverterRegistry;
    }

    public <S, T> T propertyTransplant(S source, Class<T> targetType) {
        return propertyTransplant(source, null, targetType, null);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T propertyTransplant(S source, Class<?> sourceItemType, Class<T> targetType, Class<?> targetItemType) {
        TypeConverter<S, T> typeConverter = (TypeConverter<S, T>) typeConverterRegistry.extractTypeConverter(source.getClass(), targetType);
        SourceInformation<S> sourceInformation = new SourceInformation<>();
        sourceInformation.setSource(source);
        sourceInformation.setSourceType((Class<S>) source.getClass());
        sourceInformation.setSourceItemType(sourceItemType);
        TargetInformation<T> targetInformation = new TargetInformation<>();
        targetInformation.setTargetType(targetType);
        targetInformation.setTargetItemType(targetItemType);
        return typeConverter.convert(typeConverterRegistry, sourceInformation, targetInformation);
    }
}
