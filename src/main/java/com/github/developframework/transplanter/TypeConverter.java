package com.github.developframework.transplanter;

/**
 * 类型转换器
 * @param <S>
 * @param <T>
 */
public interface TypeConverter<S, T> {

    /**
     * 确定是否可以用当前的转换器
     * @param sourceType 源类型
     * @param targetType 目标类型
     * @return 是否匹配
     */
    boolean matches(Class<?> sourceType, Class<?> targetType);

    /**
     * 转换方法
     * @param typeConverterRegistry 注册器
     * @param sourceInformation 源信息
     * @param targetInformation 目标信息
     * @return 目标实例
     */
    T convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<S> sourceInformation, TargetInformation<T> targetInformation);
}
