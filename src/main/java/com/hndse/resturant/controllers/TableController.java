package com.hndse.resturant.controllers;


import com.hndse.resturant.dtos.request.TableRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.repos.TableRepository;
import com.hndse.resturant.services.TableService;
import com.hndse.resturant.utilities.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("api/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @Autowired
    private ResponseDto responseDto;
    @Autowired
    private TableRepository tableRepository;

    @PostMapping("addtable")
    public ResponseEntity<ResponseDto> createTable(@Valid @RequestBody TableRequestDto tableRequestDto) {
        try{
            tableService.addTable(tableRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)){
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

    @PutMapping("updatetable")
    public ResponseEntity<ResponseDto> updateTable(@Valid @RequestBody TableRequestDto tableRequestDto) {
        try{
            tableService.updateTable(tableRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (DataIntegrityViolationException ex){
            responseDto.setCode(VarList.RSP_DUPLICATED);
            responseDto.setMessage("Table Number already exists");
            responseDto.setContent(tableRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

        } catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(null);
            responseDto.setContent(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getalltables")
    public ResponseEntity<ResponseDto> getAllTables() {
        try{
            tableService.getAllTables();
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setMessage(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deletetable")
    public ResponseEntity<ResponseDto> deleteTable(@Valid @RequestBody TableRequestDto tableRequestDto) {
        try{
            tableService.deleteTable(tableRequestDto);
            if (responseDto.getCode().equals(VarList.RSP_SUCCESS)){
                return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
            }
            else {
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
