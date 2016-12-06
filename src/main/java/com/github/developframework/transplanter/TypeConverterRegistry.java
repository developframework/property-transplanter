package com.github.developframework.transplanter;

import com.github.developframework.transplanter.exception.NoTypeConverterAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class TypeConverterRegistry {

    private static final Logger log = LoggerFactory.getLogger(TypeConverterRegistry.class);

    private List<TypeConverter<?, ?>> typeConverters = new ArrayList<>();

    protected PropertyTransplanter propertyTransplanter;

    public void setPropertyTransplanter(PropertyTransplanter propertyTransplanter) {
        this.propertyTransplanter = propertyTransplanter;
    }

    /**
     * 注册TypeConverter
     * @param typeConverter
     */
    protected void registerTypeConverter(TypeConverter<?, ?> typeConverter) {
        typeConverters.add(typeConverter);
        log.debug("TypeConverterRegistry register TypeConverter \"{}\".", typeConverter.getClass());
    }

    /**
     * 注册自定义TypeConverter
     * @param typeConverter
     */
    public void registerCustomTypeConverter(TypeConverter<?, ?> typeConverter) {
        typeConverters.add(0, typeConverter);
        log.debug("TypeConverterRegistry register TypeConverter: \"{}\".", typeConverter.getClass());
    }

    /**
     * 提取TypeConverter
     * @param sourceType
     * @param targetType
     * @return
     */
    public TypeConverter<?, ?> extractTypeConverter(final Class<?> sourceType, final Class<?> targetType) {
        Optional<TypeConverter<?, ?>> typeConverterOptional = tryGetInTypeConverters(sourceType, targetType);
        return typeConverterOptional.orElseThrow(() -> new NoTypeConverterAvailableException(sourceType, targetType));
    }

    /**
     * 尝试从TypeConverters集合中获取
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    private Optional<TypeConverter<?, ?>> tryGetInTypeConverters(Class<?> sourceType, Class<?> targetType) {
        for(TypeConverter<?, ?> typeConverter : typeConverters) {
            if(typeConverter.matches(sourceType, targetType)) {
                log.debug("TypeConverterRegistry matches \"{}\" for class cast \"{}\" to \"{}\"", typeConverter.getClass().getName(), sourceType.getName(), targetType.getName());
                return Optional.of(typeConverter);
            }
        }
        return Optional.empty();
    }
}
