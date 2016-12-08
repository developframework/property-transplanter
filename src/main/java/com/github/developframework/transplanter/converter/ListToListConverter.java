package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.SourceInformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListToListConverter extends CollectionToCollectionConverter<List<Object>, List<Object>> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return List.class.isAssignableFrom(sourceType) && List.class.isAssignableFrom(targetType);
    }

    @Override
    Iterator<Object> iterator(SourceInformation<List<Object>> sourceInformation) {
        return sourceInformation.getSource().iterator();
    }

    @Override
    int size(SourceInformation<List<Object>> sourceInformation) {
        return sourceInformation.getSource().size();
    }

    @Override
    List<Object> targetCollection(int size) {
        return new ArrayList<>(size);
    }
}
