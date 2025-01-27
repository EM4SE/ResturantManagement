package com.hndse.resturant.services;

import com.hndse.resturant.dtos.request.CashierRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Cashier;
import com.hndse.resturant.repos.CashierRepository;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private ResponseDto responseDto;


    @Transactional
    public void posLogin(CashierRequestDto cashierRequestDto) {
        try {
            if (cashierRequestDto.getUsername()==null || cashierRequestDto.getPassword().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Username or Password is empty");
                responseDto.setContent(cashierRequestDto);
            } else {
                Cashier cashier = cashierRepository.findByUsername(cashierRequestDto.getUsername());
                if (cashier == null) {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Username or Password is incorrect");
                    responseDto.setContent(null);
                } else if (passwordEncoder.matches(cashierRequestDto.getPassword(), cashier.getPassword())) {
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Success");
                    responseDto.setContent(cashier);
                } else {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Username or Password is incorrect");
                    responseDto.setContent(null);
                }
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }

    }

}
