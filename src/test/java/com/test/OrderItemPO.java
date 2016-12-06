package com.test;

import lombok.Data;

@Data
public class OrderItemPO {

    private Integer id;

    private String goodsName;

    private Integer price;

    private Integer quantity;
}
