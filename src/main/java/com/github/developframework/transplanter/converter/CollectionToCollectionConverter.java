package com.github.developframework.transplanter.converter;


import com.github.developframework.transplanter.SourceInformation;
import com.github.developframework.transplanter.TargetInformation;
import com.github.developframework.transplanter.TypeConverter;
import com.github.developframework.transplanter.TypeConverterRegistry;

import java.util.Collection;
import java.util.Iterator;

public abstract class CollectionToCollectionConverter<S extends Collection<Object>, T extends Collection<Object>> extends AbstractTypeConverter<S, T> {

    @Override
    @SuppressWarnings("unchecked")
    public T convert(TypeConverterRegistry typeConverterRegistry, SourceInformation<S> sourceInformation, TargetInformation<T> targetInformation) {
        Class<Object> sourceComponentType = (Class<Object>) sourceInformation.getSourceItemType();
        Class<Object> targetComponentType = (Class<Object>) targetInformation.getTargetItemType();
        T targetCollection = targetCollection(size(sourceInformation));
        TypeConverter<Object, Object> typeConverter = (TypeConverter<Object, Object>) typeConverterRegistry.extractTypeConverter(sourceComponentType, targetComponentType);
        for(Iterator iterator = iterator(sourceInformation); iterator.hasNext();) {
            Object obj = convertCollectionItem(typeConverterRegistry, typeConverter, sourceComponentType, targetComponentType, iterator.next());
            targetCollection.add(obj);
        }
        return targetCollection;
    }

    /**
     * 获取源集合的迭代器
     * @param sourceInformation
     * @return
     */
    abstract Iterator<Object> iterator(SourceInformation<S> sourceInformation);

    /**
     * 获取源集合的长度
     * @param sourceInformation
     * @return
     */
    abstract int size(SourceInformation<S> sourceInformation);

    /**
     * 申明目标集合
     * @param size
     * @return
     */
    abstract T targetCollection(int size);

}
