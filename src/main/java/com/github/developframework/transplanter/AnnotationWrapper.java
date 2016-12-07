package com.github.developframework.transplanter;

import com.github.developframework.transplanter.annotation.SourceItemType;
import com.github.developframework.transplanter.annotation.TargetItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnotationWrapper {

    private SourceItemType sourceItemType;

    private TargetItemType targetItemType;
}
