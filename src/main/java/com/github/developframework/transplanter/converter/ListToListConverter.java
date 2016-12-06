package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ListToListConverter implements TypeConverter<List<?>, List<?>>{

    private TypeConverterRegistry typeConverterRegistry;

    public ListToListConverter(TypeConverterRegistry typeConverterRegistry) {
        this.typeConverterRegistry = typeConverterRegistry;
    }

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public List<?> convert(List<?> source, Class<List<?>> targetType) {
        return null;
    }
}
