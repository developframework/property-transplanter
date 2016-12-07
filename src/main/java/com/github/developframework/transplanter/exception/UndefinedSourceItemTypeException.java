package com.github.developframework.transplanter.exception;

public class UndefinedSourceItemTypeException extends TransplanterException{

    public UndefinedSourceItemTypeException() {
        super("undefined source item type for collection property, please use \"com.github.developframework.transplanter.annotation.SourceItemType\".");
    }
}
