package com.test;

import com.github.developframework.transplanter.PropertyTransplanter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        OrderDTO orderDTO = new OrderDTO();
        OrderItemDTO items1 = new OrderItemDTO(1, "milk", 10, 1);
        OrderItemDTO items2 = new OrderItemDTO(2, "cake", 5, 2);
        orderDTO.setId(1);
        orderDTO.setOrderSn("xxxx");
        orderDTO.setItems(Arrays.asList(items1, items2));
        orderDTO.setOrderStatus(OrderDTO.Status.WAIT_PAY);
        System.out.println(orderDTO);

        Map<String, OrderDTO> mapDTO = new HashMap<>();
        mapDTO.put("order", orderDTO);

        PropertyTransplanter propertyTransplanter = new PropertyTransplanter();
        Map<String, OrderPO> mapPO =  propertyTransplanter.propertyTransplant(mapDTO, OrderPO.class, Map.class, OrderPO.class);
        System.out.println(mapPO.get("order"));
    }

//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) {
//        PropertyTransplanter propertyTransplanter = new PropertyTransplanter();
//        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
//        List<String> list = propertyTransplanter.propertyTransplant(array, null, List.class, String.class);
//        list.forEach(item -> System.out.printf("%s   ->   %s\n", item, item.getClass().getName()));
//    }

}
