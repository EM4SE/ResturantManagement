package com.hndse.resturant.controllers;


import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;

import com.hndse.resturant.services.CashierService;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/cashiers")
public class CashierController {

    @Autowired
    private CashierService cashierService;
    @Autowired
    private ResponseDto responseDto;

    @PostMapping("/addCashier")
    public ResponseEntity<ResponseDto> addCashier(@RequestBody CashierRequestDto cashierRequestDto) {
        try{
            cashierService.createCashier(cashierRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
