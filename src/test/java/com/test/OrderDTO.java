package com.test;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private Integer id;

    private String orderSn;

    private List<OrderItemDTO> items;
}
