package com.hndse.resturant.services;


import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.CashierResponseDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Cashier;
import com.hndse.resturant.mappers.CashierMapper;
import com.hndse.resturant.repos.CashierRepository;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


    @Transactional
    public void updateCashier(CashierRequestDto cashierRequestDto) {
        try {
            if(cashierRequestDto.getId() == null|| !cashierRepository.existsById(cashierRequestDto.getId())) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No such cashier");
                responseDto.setContent(cashierRequestDto);
            }
            else if (cashierRequestDto.getName() == null || cashierRequestDto.getName().isEmpty()
                    || cashierRequestDto.getUsername() == null || cashierRequestDto.getUsername().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(cashierRequestDto);
            }else if(10 ==  String.valueOf(cashierRequestDto.getMobile()).length()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Invalid mobile number");
                responseDto.setContent(cashierRequestDto);
            }else{
                Cashier cashier = cashierRepository.findById(cashierRequestDto.getId()).get();
                cashierRepository.save(CashierMapper.mapToCashierForUpdate(cashierRequestDto,cashier));
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully Updated cashier");
                responseDto.setContent(cashierRequestDto);

            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }
    }


    @Transactional
    public void updatePassword(CashierRequestDto cashierRequestDto) {
        try {
            if(cashierRequestDto.getId() == null|| !cashierRepository.existsById(cashierRequestDto.getId())) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No such cashier");
                responseDto.setContent(cashierRequestDto);
            }
            if (cashierRequestDto.getPassword() == null) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(cashierRequestDto);
            }else{
                Cashier cashier = cashierRepository.findById(cashierRequestDto.getId()).get();
                cashierRepository.save(CashierMapper.mapForPassword(cashierRequestDto,cashier));
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully Updated cashier");
                responseDto.setContent(cashierRequestDto);

            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }
    }



    @Transactional(readOnly = true)
    public void getAllCashiers() {

        try {
            List<Cashier> cashiers = cashierRepository.findAll();
            List<CashierResponseDto> cashiersDto = new ArrayList<>();

            if (!cashiers.isEmpty()) {
                for (Cashier cashier : cashiers) {
                    cashiersDto.add(CashierMapper.mapToCashierResponseDto(cashier));
                }
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Cashier List successfully");
                responseDto.setContent(cashiersDto);
            } else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Cashier List is empty");
                responseDto.setContent(null);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }

    }

    @Transactional
    public void deleteCashier(CashierRequestDto cashierRequestDto) {
        try{
            if(cashierRequestDto.getId() == null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No such cashier");
                responseDto.setContent(cashierRequestDto);
            }else if(!cashierRepository.existsById(cashierRequestDto.getId())) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Cashier Not exists");
                responseDto.setContent(cashierRequestDto);
            }else{
                cashierRepository.deleteById(cashierRequestDto.getId());
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully deleted");
                responseDto.setContent(cashierRequestDto);
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }
}
