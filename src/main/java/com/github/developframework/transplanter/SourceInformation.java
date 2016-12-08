package com.github.developframework.transplanter;

import com.github.developframework.transplanter.exception.UndefinedSourceItemTypeException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SourceInformation<S> {

    private Class<S> sourceType;

    private Class<?> sourceItemType;

    private S source;

    public void setSourceType(Class<S> sourceType) {
        this.sourceType = sourceType;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public S getSource() {
        return source;
    }

    public Class<S> getSourceType() {
        return sourceType;
    }

    public Class<?> getSourceItemType() {
        if(sourceType.isArray()) {
            return sourceType.getComponentType();
        } else if(List.class.isAssignableFrom(sourceType) || Set.class.isAssignableFrom(sourceType) || Map.class.isAssignableFrom(sourceType)) {
            return sourceItemType;
        }
        throw new UndefinedSourceItemTypeException();
    }

    public void setSourceItemType(Class<?> sourceItemType) {
        this.sourceItemType = sourceItemType;
    }
}
