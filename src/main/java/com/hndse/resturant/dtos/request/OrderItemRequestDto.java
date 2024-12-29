package com.hndse.resturant.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequestDto {
    private Integer id;
    private String itemName;
    private Integer orderId;
    private Integer quantity;
    private double unitPrice;
    private double totalPrice;
}
