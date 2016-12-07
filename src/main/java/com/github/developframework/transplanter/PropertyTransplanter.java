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

    @SuppressWarnings("unchecked")
    public <S, T> T propertyTransplant(S source, Class<T> targetType) {
        TypeConverter<S, T> typeConverter = (TypeConverter<S, T>) typeConverterRegistry.extractTypeConverter(source.getClass(), targetType);
        return typeConverter.convert(typeConverterRegistry, source, targetType, new AnnotationWrapper());
    }
}
