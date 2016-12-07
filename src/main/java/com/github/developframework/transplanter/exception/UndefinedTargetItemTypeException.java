package com.github.developframework.transplanter.exception;

public class UndefinedTargetItemTypeException extends TransplanterException{

    public UndefinedTargetItemTypeException() {
        super("undefined target item type for collection property, please use \"com.github.developframework.transplanter.annotation.TargetItemType\".");
    }
}
