package com.hndse.resturant.services;


import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Cashier;
import com.hndse.resturant.mappers.CashierMapper;
import com.hndse.resturant.repos.CashierRepository;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashierService {
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private ResponseDto responseDto;


    @Transactional
    public void createCashier(CashierRequestDto cashierRequestDto) {
        try {
            if (cashierRequestDto.getName() == null || cashierRequestDto.getName().isEmpty()
                    || cashierRequestDto.getUsername() == null || cashierRequestDto.getUsername().isEmpty()
                    || cashierRequestDto.getPassword() == null) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(cashierRequestDto);
            }else if(10 ==  String.valueOf(cashierRequestDto.getMobile()).length()){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Invalid mobile number");
                responseDto.setContent(cashierRequestDto);
            }else if(cashierRepository.existsByUsername(cashierRequestDto.getUsername())) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Username already exists");
                responseDto.setContent(cashierRequestDto);
            }else{
                cashierRepository.save(CashierMapper.mapToCashier(cashierRequestDto));
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully created cashier");
                responseDto.setContent(cashierRequestDto);

            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }
    }
}
