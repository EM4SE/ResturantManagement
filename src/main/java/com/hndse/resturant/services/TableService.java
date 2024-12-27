package com.hndse.resturant.services;

import com.hndse.resturant.dtos.request.TableRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Tables;
import com.hndse.resturant.mappers.TableMapper;
import com.hndse.resturant.repos.TableRepository;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ResponseDto responseDto;


    @Transactional
    public void addTable(TableRequestDto tableRequestDto) {
        try{
            if(tableRequestDto.getNumber()==null || tableRequestDto.getSeats()==null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields Cannot Be Empty");
                responseDto.setContent(tableRequestDto);
            }else if(tableRequestDto.getNumber()<0 || tableRequestDto.getSeats()<0){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Invalid Number or Seats");
                responseDto.setContent(tableRequestDto);
            }else if(tableRepository.existsByNumber(tableRequestDto.getNumber())){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("This Table Already Exists");
                responseDto.setContent(tableRequestDto);
            }
            else{
                tableRepository.save(TableMapper.mapToTable(tableRequestDto));
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully Added Table");
                responseDto.setContent(tableRequestDto);
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }

    @Transactional
    public void updateTable(TableRequestDto tableRequestDto) {
        try{
            if(tableRequestDto.getId()==null || tableRequestDto.getNumber()==null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Table Id not found");
                responseDto.setContent(tableRequestDto);
            }else if(tableRequestDto.getNumber()<0 || tableRequestDto.getSeats()<0){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Invalid Number or Seats");
                responseDto.setContent(tableRequestDto);
            }else{
                if(tableRepository.existsById(tableRequestDto.getId())){
                      tableRepository.save(TableMapper.mapToTable(tableRequestDto));
                      responseDto.setCode(VarList.RSP_SUCCESS);
                      responseDto.setMessage("Successfully Updated Table");
                      responseDto.setContent(tableRequestDto);
                }else{
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Table not found");
                    responseDto.setContent(tableRequestDto);
                }
            }
        }
        catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }
    @Transactional(readOnly = true)
    public void getAllTables() {

        try {
            List<Tables> tables = tableRepository.findAll();
            List<TableRequestDto> tableRequestDtos = new ArrayList<>();

            if (!tables.isEmpty()) {
                for (Tables table : tables) {
                    tableRequestDtos.add(TableMapper.mapToTableRequestDto(table));
                }
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Table List successfully");
                responseDto.setContent(tableRequestDtos);
            } else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Table List is empty");
                responseDto.setContent(null);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }

    }

    @Transactional
    public void deleteTable(TableRequestDto tableRequestDto) {
        try{
            if(tableRequestDto.getId()==null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Table Id not found");
                responseDto.setContent(tableRequestDto);
            }else if(tableRepository.existsById(tableRequestDto.getId())){
                tableRepository.deleteById(tableRequestDto.getId());
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully Deleted Table");
                responseDto.setContent(tableRequestDto);
            }else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Table not found");
                responseDto.setContent(tableRequestDto);
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }



}
