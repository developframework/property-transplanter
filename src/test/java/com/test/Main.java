package com.test;

import com.github.developframework.transplanter.PropertyTransplanter;

import java.util.List;

public class Main {

//    public static void main(String[] args) {
//        OrderDTO orderDTO = new OrderDTO();
//        OrderItemDTO items1 = new OrderItemDTO(1, "milk", 10, 1);
//        OrderItemDTO items2 = new OrderItemDTO(2, "cake", 5, 2);
//        orderDTO.setId(1);
//        orderDTO.setOrderSn("xxxx");
//        orderDTO.setItems(Arrays.asList(items1, items2));
//        orderDTO.setOrderStatus(OrderDTO.Status.WAIT_PAY);
//        System.out.println(orderDTO);

//        PropertyTransplanter propertyTransplanter = new PropertyTransplanter();
//        OrderPO orderPO = propertyTransplanter.propertyTransplant(orderDTO, OrderPO.class);
//        System.out.println(orderPO);
//    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        PropertyTransplanter propertyTransplanter = new PropertyTransplanter();
        boolean f = propertyTransplanter.propertyTransplant("true", Boolean.class);
        System.out.println(f);

        int[] array = new int[]{1, 2, 3, 4, 5};
        List<String> list = propertyTransplanter.propertyTransplant(array, List.class);
        System.out.println(list);
    }
}
