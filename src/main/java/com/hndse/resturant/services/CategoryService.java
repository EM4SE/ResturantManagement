package com.hndse.resturant.services;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.mappers.CategoryMapper;
import com.hndse.resturant.repos.CategoryRepository;
import com.hndse.resturant.utilities.VarList;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ResponseDto responseDto;

    @Transactional
    public void createCategory(CategoryRequestDto categoryRequestDto){

        try{
            if(categoryRequestDto.getName() == null || categoryRequestDto.getName().trim().isEmpty() ||
                    categoryRequestDto.getDescription() == null || categoryRequestDto.getDescription().isEmpty()){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(categoryRequestDto);
            }else{
                if(categoryRepository.existsByName(categoryRequestDto.getName())){
                    responseDto.setCode(VarList.RSP_DUPLICATED);
                    responseDto.setMessage("Category already exists");
                }else{
                    categoryRepository.save(CategoryMapper.mapToCategoty(categoryRequestDto));
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Category created successfully");
                }
            }
        }catch (Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }








    }
}
