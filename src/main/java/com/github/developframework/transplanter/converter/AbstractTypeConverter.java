package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;

import java.lang.reflect.Field;

public abstract class AbstractTypeConverter<S, T> implements TypeConverter<S, T> {


    <SOURCE> SourceInformation<SOURCE> createSourceInformation(SOURCE source, Class<SOURCE> sourceType) {
        SourceInformation<SOURCE> sourceInformation = new SourceInformation<>();
        sourceInformation.setSource(source);
        sourceInformation.setSourceType(sourceType);
        return sourceInformation;
    }

    @SuppressWarnings("unchecked")
    <SOURCE> SourceInformation<SOURCE> createSourceInformation(SOURCE source, Field sourceField) {
        SourceInformation<SOURCE> sourceInformation = new SourceInformation<>();
        sourceInformation.setSource(source);
        sourceInformation.setSourceType((Class<SOURCE>) sourceField.getType());
        if(sourceField.isAnnotationPresent(SourceItemType.class)) {
            sourceInformation.setSourceItemType(sourceField.getDeclaredAnnotation(SourceItemType.class).value());
        }
        return sourceInformation;
    }

    <TARGET> TargetInformation<TARGET> createTargetInformation(Class<TARGET> targetType) {
        TargetInformation<TARGET> targetInformation = new TargetInformation<>();
        targetInformation.setTargetType(targetType);
        return targetInformation;
    }

    @SuppressWarnings("unchecked")
    <TARGET> TargetInformation<TARGET> createTargetInformation(Field targetField) {
        TargetInformation<TARGET> targetInformation = new TargetInformation<>();
        targetInformation.setTargetType((Class<TARGET>) targetField.getType());
        if(targetField.isAnnotationPresent(TargetItemType.class)) {
            targetInformation.setTargetItemType(targetField.getDeclaredAnnotation(TargetItemType.class).value());
        }
        return targetInformation;
    }

    Object convertCollectionItem(TypeConverterRegistry typeConverterRegistry, TypeConverter<Object, Object> typeConverter, Class<Object> sourceComponentType, Class<Object> targetComponentType, Object sourceItem) {
        SourceInformation<Object> childSourceInformation = this.createSourceInformation(sourceItem, sourceComponentType);
        TargetInformation<Object> childTargetInformation = this.createTargetInformation(targetComponentType);
        return typeConverter.convert(typeConverterRegistry, childSourceInformation, childTargetInformation);
    }
}
