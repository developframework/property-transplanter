package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.*;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

public class AnyToOtherConverter extends AbstractTypeConverter<Object, Object>{

    private static final Logger log = LoggerFactory.getLogger(AnyToOtherConverter.class);

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return !sourceType.isArray() && !Collection.class.isAssignableFrom(sourceType) && !targetType.isArray() && !Collection.class.isAssignableFrom(targetType);
    }

    @Override
    public Object convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<Object> sourceInformation, TargetInformation<Object> targetInformation) {
        Object target;
        log.debug("-------------Begin transplant \"{}\" to class \"{}\".", sourceInformation.getSourceType().getName(), targetInformation.getTargetType().getName());
        try {
            target = targetInformation.getTargetType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        Field[] targetFields = targetInformation.getTargetType().getDeclaredFields();
        for (Field targetField : targetFields) {
            //尝试从源类型查询目标对应属性
            Optional<Field> sourceFieldOptional = trySearchFieldInSourceType(sourceInformation.getSourceType(), targetField.getName());
            if (sourceFieldOptional.isPresent()) {
                //从源实例获取属性值
                Object value = getPropertyValueFromSourceInstance(typeConverterRegistry, sourceInformation.getSource(), sourceFieldOptional.get(), targetField);
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
     *
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
     * @param typeConverterRegistry
     * @param sourceInstance
     * @param sourceField
     * @param targetField
     * @param <S>
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <S, T> T getPropertyValueFromSourceInstance(TypeConverterRegistry typeConverterRegistry, Object sourceInstance, Field sourceField, Field targetField) {
        Class<T> targetType = (Class<T>) targetField.getType();
        TypeConverter<S, T> typeConverter = (TypeConverter<S, T>) typeConverterRegistry.extractTypeConverter(sourceField.getType(), targetType);
        if (log.isDebugEnabled()) {
            log.debug("Try to transplant property \"{}\", TypeConverterRegistry matches \"{}\" for class cast \"{}\" to \"{}\"", sourceField.getName(), typeConverter.getClass().getSimpleName(), sourceField.getType().getName(), targetType.getName());
        }
        try {
            S sourceFieldValue = (S) sourceField.get(sourceInstance);
            SourceInformation<S> sourceInformation = super.createSourceInformation(sourceFieldValue, sourceField);
            TargetInformation<T> targetInformation = super.createTargetInformation(targetField);
            return typeConverter.convert(typeConverterRegistry, sourceInformation, targetInformation);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移植值到源实例
     *
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
