package com.github.developframework.transplanter;

import com.github.developframework.transplanter.converter.*;

public class DefaultTypeConverterRegistry extends TypeConverterRegistry{

    /**
     * 注册默认的类型转换器
     */
    public void registerDefaultTypeConverter() {
        registerTypeConverter(new ArrayToArrayConverter());
        registerTypeConverter(new ArrayToListConverter());
        registerTypeConverter(new ListToListConverter());
        registerTypeConverter(new ListToArrayConverter());
        registerTypeConverter(new MapToMapConverter());
        registerTypeConverter(new EnumToEnumConverter());
        registerTypeConverter(new AnyToAnyConverter());
        registerTypeConverter(new AnyToOtherConverter());
        registerTypeConverter(new AnyToStringConverter());
        registerTypeConverter(new StringToIntegerConverter());
        registerTypeConverter(new StringToLongConverter());
        registerTypeConverter(new StringToBooleanConverter());
    }
}
