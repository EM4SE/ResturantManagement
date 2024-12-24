package com.hndse.resturant.controllers;

import com.hndse.resturant.dtos.request.ProductRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Product;
import com.hndse.resturant.services.ProductService;
import com.hndse.resturant.utilities.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ResponseDto responseDto;

    @PostMapping("addproduct")
    public ResponseEntity<ResponseDto> addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        try{
            productService.addProduct(productRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editproduct")
    public ResponseEntity<ResponseDto> editProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        try{
            productService.updateProduct(productRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
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

    @GetMapping("getallproducts")
    public ResponseEntity<ResponseDto> getAllProduct() {
        try{
            productService.getallProducts();
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteproduct")
    public ResponseEntity<ResponseDto> deleteProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        try{
            productService.deleteProduct(productRequestDto);
            if(responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
