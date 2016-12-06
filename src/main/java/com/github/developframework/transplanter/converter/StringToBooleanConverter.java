package com.github.developframework.transplanter.converter;

import com.github.developframework.transplanter.TypeConverter;

/**
 * Created by Administrator on 2016/12/6.
 */
public class StringToBooleanConverter implements TypeConverter<String, Boolean> {

    @Override
    public boolean matches(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == Boolean.class;
    }

    @Override
    public Boolean convert(String source, Class<Boolean> targetType) {
        return null;
    }
}
