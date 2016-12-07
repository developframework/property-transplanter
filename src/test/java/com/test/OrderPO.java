package com.test;

import com.github.developframework.transplanter.annotation.TargetItemType;
import lombok.Data;

import java.util.List;

@Data
public class OrderPO {

    private Integer id;

    private String orderSn;

    @TargetItemType(OrderItemPO.class)
    private List<OrderItemPO> items;
}
