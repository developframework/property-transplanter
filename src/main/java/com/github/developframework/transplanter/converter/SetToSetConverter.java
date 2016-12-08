package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetToSetConverter extends CollectionToCollectionConverter<Set<Object>, Set<Object>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return Set.class.isAssignableFrom(sourceType) && Set.class.isAssignableFrom(targetType);
    }

    @Override
    Iterator<Object> iterator(SourceInformation<Set<Object>> sourceInformation) {
        return sourceInformation.getSource().iterator();
    }

    @Override
    int size(SourceInformation<Set<Object>> sourceInformation) {
        return sourceInformation.getSource().size();
    }

    @Override
    Set<Object> targetCollection(int size) {
        return new HashSet<>(size);
    }
}
