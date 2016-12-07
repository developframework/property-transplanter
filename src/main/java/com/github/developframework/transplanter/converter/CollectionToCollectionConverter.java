package com.github.developframework.transplanter.converter;


import com.github.developframework.transplanter.AnnotationWrapper;
import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;
import com.github.developframework.transplanter.exception.UndefinedSourceItemTypeException;
import com.github.developframework.transplanter.exception.UndefinedTargetItemTypeException;

public abstract class CollectionToCollectionConverter<S, T> extends AbstractTypeConverter<S, T>{


    protected SourceItemType getSourceItemTypeAnnotation(AnnotationWrapper annotationWrapper) {
        SourceItemType sourceItemTypeAnnotation = annotationWrapper.getSourceItemType();
        if (sourceItemTypeAnnotation == null) {
            throw new UndefinedSourceItemTypeException();
        }
        return sourceItemTypeAnnotation;
    }

    protected TargetItemType getTargetItemTypeAnnotation(AnnotationWrapper annotationWrapper) {
        TargetItemType targetItemTypeAnnotation = annotationWrapper.getTargetItemType();
        if (targetItemTypeAnnotation == null) {
            throw new UndefinedTargetItemTypeException();
        }
        return targetItemTypeAnnotation;
    }
}
