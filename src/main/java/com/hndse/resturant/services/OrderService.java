package com.hndse.resturant.services;

import com.hndse.resturant.dtos.request.OrderItemRequestDto;
import com.hndse.resturant.dtos.request.OrderRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Order;
import com.hndse.resturant.entities.OrderItem;
import com.hndse.resturant.mappers.OrderMapper;
import com.hndse.resturant.repos.OrderItemRepository;
import com.hndse.resturant.repos.OrderRepository;
import com.hndse.resturant.repos.TableRepository;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    ResponseDto responseDto;
    @Autowired
    TableRepository tableRepository;

    @Transactional
    public void createOrder(OrderRequestDto orderRequestDto) {

        try {


            if (orderRequestDto.getOrderItems().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No order items found");
                responseDto.setContent(orderRequestDto);
            } else if (orderRequestDto.getTotalAmount() < 1) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No order amount found or Invalid Total Amount");
                responseDto.setContent(orderRequestDto);
            } else if (!(tableRepository.existsByNumber(orderRequestDto.getTableNumber())) ||
                    !Objects.equals(tableRepository.findTableStatus(orderRequestDto.getTableNumber()), "Available")) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Table Not Available");
                responseDto.setContent(orderRequestDto);
            }else{
                Order order = OrderMapper.mapToOrder(orderRequestDto);
                orderRepository.save(order);
                Integer orderId = order.getId();
                List<OrderItem> orderItems = OrderMapper.toOrderItemList(orderRequestDto.getOrderItems(), orderId);
                orderItemRepository.saveAll(orderItems);
                tableRepository.updateTableStatus(orderRequestDto.getTableNumber(), "Occupied");
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Order Successfully Created");
                responseDto.setContent(orderRequestDto);
            }


        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }


    @Transactional(readOnly = true)
    public void getallOrders(){
        try{
            List<Order> orders = orderRepository.findAll();
            List<OrderRequestDto> orderRequestDtos = new ArrayList<>();
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    orderRequestDtos.add(OrderMapper.toOrderRequestDto(order));
                }
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Orders List successfully");
                responseDto.setContent(orderRequestDtos);
            } else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Orders List is empty");
                responseDto.setContent(null);
            }


        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }

    @Transactional(readOnly = true)
    public void getOrderItemById(OrderRequestDto orderRequestDto){
        try{
            if(orderRequestDto.getId() == null || !orderRepository.existsById(orderRequestDto.getId())){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No order id found");
                responseDto.setContent(orderRequestDto);
            }else{
                List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderRequestDto.getId());
                List<OrderItemRequestDto> orderItemRequestDtos = new ArrayList<>();
                if(!orderItems.isEmpty()){
                    for (OrderItem orderItem : orderItems) {
                        orderItemRequestDtos.add(OrderMapper.toOrderItemRequestDto(orderItem));
                    }
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Order Item List successfully");
                    responseDto.setContent(orderItemRequestDtos);
                }else{
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("No order item id found");
                    responseDto.setContent(null);
                }
            }

        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }
}
