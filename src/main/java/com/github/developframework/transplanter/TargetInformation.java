package com.github.developframework.transplanter;

import com.github.developframework.transplanter.exception.UndefinedTargetItemTypeException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TargetInformation<T> {

    private Class<T> targetType;

    private Class<?> targetItemType;

    public Class<T> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<T> targetType) {
        this.targetType = targetType;
    }

    public Class<?> getTargetItemType() {
        if(targetType.isArray()) {
            return targetType.getComponentType();
        } else if(List.class.isAssignableFrom(targetType) || Set.class.isAssignableFrom(targetType) || Map.class.isAssignableFrom(targetType)) {
            return targetItemType;
        }
        throw new UndefinedTargetItemTypeException();
    }

    public void setTargetItemType(Class<?> targetItemType) {
        this.targetItemType = targetItemType;
    }
}
