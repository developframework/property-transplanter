package com.github.developframework.transplanter;

import java.lang.reflect.Field;
import java.util.Optional;

public class PropertyTransplanter {

    private TypeConverterRegistry typeConverterRegistry;

    public PropertyTransplanter() {
        this.typeConverterRegistry = new DefaultTypeConverterRegistry();
        this.typeConverterRegistry.setPropertyTransplanter(this);
        ((DefaultTypeConverterRegistry)this.typeConverterRegistry).registerDefaultTypeConverter();
    }

    public PropertyTransplanter(TypeConverterRegistry typeConverterRegistry) {
        this.typeConverterRegistry = typeConverterRegistry;
        this.typeConverterRegistry.setPropertyTransplanter(this);
    }

    @SuppressWarnings("unchecked")
    public <S, T> T propertyTransplant(S source, Class<T> targetType) {
        T target = null;
        final Class<S> sourceType = (Class<S>) source.getClass();
        try {
            target = targetType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        Field[] targetFields = targetType.getDeclaredFields();
        for (Field targetField : targetFields) {
            //尝试从源类型查询目标对应属性
            Optional<Field> sourceFieldOptional = trySearchFieldInSourceType(sourceType, targetField.getName());
            if (sourceFieldOptional.isPresent()) {
                //从源实例获取属性值
                Object value = getPropertyValueFromSourceInstance(source, sourceFieldOptional.get(), targetField.getType());
                if (value != null) {
                    //移植值到源实例
                    transplantValueToTargetInstance(target, targetField, value);
                }
            }
        }
        return target;
    }

    /**
     * 尝试从源类型查询目标对应属性
     * @param sourceType
     * @param fieldName
     * @return
     */
    private Optional<Field> trySearchFieldInSourceType(Class<?> sourceType, String fieldName) {
        try {
            Field field = sourceType.getDeclaredField(fieldName);
            field.setAccessible(true);
            return Optional.of(field);
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    /**
     * 从源实例获取属性值
     * @param sourceInstance
     * @param sourceField
     * @param targetType
     * @param <S>
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <S, T> T getPropertyValueFromSourceInstance(Object sourceInstance, Field sourceField, Class<T> targetType) {
        TypeConverter<S, T> typeConverter = (TypeConverter<S, T>) typeConverterRegistry.extractTypeConverter(sourceField.getType(), targetType);
        try {
            S sourceFieldValue = (S) sourceField.get(sourceInstance);
            return typeConverter.convert(sourceFieldValue, targetType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移植值到源实例
     * @param targetInstance
     * @param targetField
     * @param value
     */
    private void transplantValueToTargetInstance(Object targetInstance, Field targetField, Object value) {
        try {
            targetField.setAccessible(true);
            targetField.set(targetInstance, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
