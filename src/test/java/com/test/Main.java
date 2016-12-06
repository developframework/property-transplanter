package com.test;

import com.github.developframework.transplanter.PropertyTransplanter;

public class Main {

    public static void main(String[] args) {
        OrderDTO orderDTO = new OrderDTO();
        OrderItemDTO items1 = new OrderItemDTO(1, "milk", 10, 1);
        OrderItemDTO items2 = new OrderItemDTO(2, "cake", 5, 2);
        orderDTO.setId(1);
        orderDTO.setOrderSn("xxxx");
        orderDTO.setItems(new OrderItemDTO[]{items1, items2});
        System.out.println(orderDTO);

        PropertyTransplanter propertyTransplanter = new PropertyTransplanter();
        OrderPO orderPO = propertyTransplanter.propertyTransplant(orderDTO, OrderPO.class);
        System.out.println(orderPO);
    }
}
