package com.test;

import lombok.Data;

@Data
public class OrderPO {

    private Integer id;

    private String orderSn;

    private OrderItemPO[] items;
}
