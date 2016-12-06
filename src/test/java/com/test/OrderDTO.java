package com.test;

import lombok.Data;

@Data
public class OrderDTO {

    private Integer id;

    private String orderSn;

    private OrderItemDTO[] items;
}
