package com.hndse.resturant.controllers;


import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.services.AuthService;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/login")
public class AuthenticatingController {

    @Autowired
    private ResponseDto responseDto;
    @Autowired
    private AuthService authService;

    @PostMapping("/posLogin")
    public ResponseEntity<ResponseDto> posLogin(@RequestBody CashierRequestDto cashierRequestDto) {
        try{

            authService.posLogin(cashierRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

}
