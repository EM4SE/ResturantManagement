package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.OrderItemRequestDto;
import com.hndse.resturant.dtos.request.OrderRequestDto;
import com.hndse.resturant.entities.Order;
import com.hndse.resturant.entities.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

public static OrderItemRequestDto toOrderItemRequestDto(OrderItem orderItem) {

    OrderItemRequestDto orderItemRequestDto = new OrderItemRequestDto();
    orderItemRequestDto.setId(orderItem.getId());
    orderItemRequestDto.setOrderId(orderItem.getOrderId());
    orderItemRequestDto.setItemName(orderItem.getItemName());
    orderItemRequestDto.setQuantity(orderItem.getQuantity());
    orderItemRequestDto.setUnitPrice(orderItem.getUnitPrice());
    orderItemRequestDto.setTotalPrice(orderItem.getTotalPrice());

    return orderItemRequestDto;

}

public static List<OrderItemRequestDto> toOrderItemRequestDtoList(List<OrderItem> orderItems) {
    return  orderItems.stream()
            .map(OrderMapper::toOrderItemRequestDto)
            .collect(Collectors.toList());
}

public static Order mapToOrder(OrderRequestDto orderRequestDto) {
    Order order = new Order();
    order.setId(orderRequestDto.getId());
    order.setTableNumber(orderRequestDto.getTableNumber());
    order.setTotalAmount(orderRequestDto.getTotalAmount());
    order.setOrderStatus(orderRequestDto.getOrderStatus());
    order.setOrderType(orderRequestDto.getOrderType());
    order.setTableAssistant(orderRequestDto.getTableAssistant());
    order.setNumberOfCustomers(orderRequestDto.getNumberOfCustomers());
    return order;
}
public static List<OrderItem> toOrderItemList(List<OrderItemRequestDto> orderItemRequestDtoList,Integer orderId) {
    return orderItemRequestDtoList.stream().map(orderItemRequestDto -> {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemRequestDto.getId());
        orderItem.setOrderId(orderId);
        orderItem.setItemName(orderItemRequestDto.getItemName());
        orderItem.setQuantity(orderItemRequestDto.getQuantity());
        orderItem.setUnitPrice(orderItemRequestDto.getUnitPrice());
        orderItem.setTotalPrice(orderItemRequestDto.getTotalPrice());
        return orderItem;
    }).collect(Collectors.toList());

}
public static OrderRequestDto toOrderRequestDto(Order order) {
    OrderRequestDto orderRequestDto = new OrderRequestDto();
    orderRequestDto.setId(order.getId());
    orderRequestDto.setTableNumber(order.getTableNumber());
    orderRequestDto.setTotalAmount(order.getTotalAmount());
    orderRequestDto.setOrderStatus(order.getOrderStatus());
    orderRequestDto.setOrderedAt(order.getOrderedAt());
    orderRequestDto.setOrderType(order.getOrderType());
    orderRequestDto.setTableAssistant(order.getTableAssistant());
    orderRequestDto.setNumberOfCustomers(order.getNumberOfCustomers());
    return orderRequestDto;
}

}
