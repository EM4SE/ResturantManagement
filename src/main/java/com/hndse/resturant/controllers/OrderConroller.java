package com.hndse.resturant.controllers;

import com.hndse.resturant.dtos.request.OrderRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.services.OrderService;
import com.hndse.resturant.utilities.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderConroller {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping("addorder")
    public ResponseEntity<ResponseDto> addOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        try{
            orderService.createOrder(orderRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
