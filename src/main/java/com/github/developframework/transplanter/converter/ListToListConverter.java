package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.PropertyTransplanter;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.annotation.TargetItemType;
import com.github.developframework.transplanter.exception.UndefinedTargetItemTypeException;

import java.util.ArrayList;
import java.util.List;

public class ListToListConverter implements TypeConverter<List<?>, List<?>>{

    private PropertyTransplanter propertyTransplanter;

    public ListToListConverter(PropertyTransplanter propertyTransplanter) {
        this.propertyTransplanter = propertyTransplanter;
    }

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return List.class.isAssignableFrom(sourceType) && List.class.isAssignableFrom(targetType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<?> convert(List<?> source, Class<List<?>> targetType, AnnotationWrapper annotationWrapper) {
        TargetItemType targetItemTypeAnnotation = annotationWrapper.getTargetItemType();
        if(targetItemTypeAnnotation == null) {
            throw new UndefinedTargetItemTypeException();
        }
        List list = new ArrayList(source.size());
        for (Object item : source) {
            Object obj = propertyTransplanter.propertyTransplant(item, targetItemTypeAnnotation.value());
            list.add(obj);
        }
        return list;
    }
}
