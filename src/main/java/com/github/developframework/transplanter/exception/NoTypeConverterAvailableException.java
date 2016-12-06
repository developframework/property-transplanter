package com.github.developframework.transplanter.exception;

/**
 * 没有可用的TypeConverter异常
 */
public class NoTypeConverterAvailableException extends TransplanterException {

    public NoTypeConverterAvailableException(Class<?> sourceType, Class<?> targetType) {
        super(String.format("No TypeConverter available for class \"%s\" and class \"%s\" copy.", sourceType.getName(), targetType.getName()));
    }
}
