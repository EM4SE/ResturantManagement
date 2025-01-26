package com.hndse.resturant.controllers;


import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;

import com.hndse.resturant.services.CashierService;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            cashierService.createCashier(cashierRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateCashier")
    public ResponseEntity<ResponseDto> updateCashier(@RequestBody CashierRequestDto cashierRequestDto) {
        try {
            cashierService.updateCashier(cashierRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException ex) {
            responseDto.setCode(VarList.RSP_DUPLICATED);
            responseDto.setMessage("UserName already exists");
            responseDto.setContent(cashierRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody CashierRequestDto cashierRequestDto) {
        try{
            cashierService.updatePassword(cashierRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallcashiers")
    public ResponseEntity<ResponseDto> getAllCashiers() {
        try{
            cashierService.getAllCashiers();
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteCashier")
    public ResponseEntity<ResponseDto> deleteCashier(@RequestBody CashierRequestDto cashierRequestDto) {
        try{
            cashierService.deleteCashier(cashierRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
