package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.CashierResponseDto;
import com.hndse.resturant.entities.Cashier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class CashierMapper {


    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Cashier mapToCashier(CashierRequestDto cashierRequestDto){

        Cashier cashier = new Cashier();
        cashier.setId(cashierRequestDto.getId());
        cashier.setName(cashierRequestDto.getName());
        cashier.setUsername(cashierRequestDto.getUsername());
        cashier.setPassword(passwordEncoder.encode(cashierRequestDto.getPassword()));
        cashier.setMobile(cashierRequestDto.getMobile());
        cashier.setStatus(cashierRequestDto.getStatus());
        return cashier;
    }

    private static CashierRequestDto mapToCashierRequestDto(Cashier cashier){
        CashierRequestDto cashierRequestDto = new CashierRequestDto();
        cashierRequestDto.setId(cashier.getId());
        cashierRequestDto.setName(cashier.getName());
        cashierRequestDto.setUsername(cashier.getUsername());
        cashierRequestDto.setPassword(passwordEncoder.encode(cashier.getPassword()));
        cashierRequestDto.setMobile(cashier.getMobile());
        cashierRequestDto.setStatus(cashier.getStatus());
        return cashierRequestDto;
    }

    public static CashierResponseDto mapToCashierResponseDto(Cashier cashier){
        CashierResponseDto cashierResponseDto = new CashierResponseDto();
        cashierResponseDto.setId(cashier.getId());
        cashierResponseDto.setName(cashier.getName());
        cashierResponseDto.setUsername(cashier.getUsername());
        cashierResponseDto.setMobile(cashier.getMobile());
        cashierResponseDto.setStatus(cashier.getStatus());
        cashierResponseDto.setCreatedAt(cashier.getCreatedAt());
        cashierResponseDto.setUpdatedAt(cashier.getUpdatedAt());
        return cashierResponseDto;
    }
}
