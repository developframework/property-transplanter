package com.test;

import com.github.developframework.transplanter.annotation.SourceItemType;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    public enum Status {
        WAIT_PAY
    }

    private Integer id;

    private String orderSn;

    @SourceItemType(OrderItemDTO.class)
    private List<OrderItemDTO> items;

    private Status orderStatus;
}
