package com.hndse.resturant.controllers;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.services.CategoryService;
import com.hndse.resturant.utilities.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ResponseDto responseDto;

    @PostMapping("/addcategory")
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        try {
            categoryService.createCategory(categoryRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatecategory")
    public ResponseEntity<ResponseDto> updateCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        try {
            categoryService.updateCategory(categoryRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (DataIntegrityViolationException ex){
            responseDto.setCode(VarList.RSP_DUPLICATED);
            responseDto.setMessage("Category Name already exists");
            responseDto.setContent(categoryRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        }  catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallcategories")
    public ResponseEntity<ResponseDto> getAllCategories() {
        try {
            categoryService.getAllCategories();
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletecategory")
    public ResponseEntity<ResponseDto> deleteCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        try{
            categoryService.deleteCategory(categoryRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)) {
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
