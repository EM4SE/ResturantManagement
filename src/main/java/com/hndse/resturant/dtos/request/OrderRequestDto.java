package com.hndse.resturant.dtos.request;

import com.hndse.resturant.entities.OrderItem;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Integer id;
    @NotBlank(message = "Table id is required.")
    private Integer tableNumber;
    private List<OrderItemRequestDto> orderItems;
    private LocalDateTime orderedAt;
    private double totalAmount;
    private String orderStatus;
}
