package com.github.developframework.transplanter;

public interface TypeConverter<S, T> {

    boolean matches(Class<?> sourceType, Class<?> targetType);

    T convert(S source, Class<T> targetType);
}
