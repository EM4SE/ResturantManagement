package com.hndse.resturant.controllers;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.services.CategoryService;
import com.hndse.resturant.utilities.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ResponseDto responseDto;

    @PostMapping
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
       try{
           categoryService.createCategory(categoryRequestDto);
           if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
               return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
           } else{
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
