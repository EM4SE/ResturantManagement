package com.hndse.resturant.services;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Category;
import com.hndse.resturant.mappers.CategoryMapper;
import com.hndse.resturant.repos.CategoryRepository;
import com.hndse.resturant.utilities.VarList;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ResponseDto responseDto;

    @Transactional
    public void createCategory(CategoryRequestDto categoryRequestDto) {

        try {
            if (categoryRequestDto.getName() == null || categoryRequestDto.getName().trim().isEmpty() || categoryRequestDto.getDescription() == null || categoryRequestDto.getDescription().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(categoryRequestDto);
            } else {
                if (categoryRepository.existsByName(categoryRequestDto.getName())) {
                    responseDto.setCode(VarList.RSP_DUPLICATED);
                    responseDto.setMessage("Category already exists");
                    responseDto.setContent(categoryRequestDto);
                } else {
                    categoryRepository.save(CategoryMapper.mapToCategoty(categoryRequestDto));
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Category created successfully");
                    responseDto.setContent(categoryRequestDto);
                }
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
        }
    }

    @Transactional
    public void updateCategory(CategoryRequestDto categoryRequestDto) {
        try {
            if (categoryRequestDto.getName() == null || categoryRequestDto.getName().trim().isEmpty() || categoryRequestDto.getDescription() == null || categoryRequestDto.getDescription().isEmpty() || categoryRequestDto.getId() == null || categoryRequestDto.getId() == 0) {

                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Fields cannot be empty");
                responseDto.setContent(categoryRequestDto);
            } else {
                if (categoryRepository.existsById(categoryRequestDto.getId())) {
                    categoryRepository.save(CategoryMapper.mapToCategoty(categoryRequestDto));
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Category Edited successfully");
                    responseDto.setContent(categoryRequestDto);
                } else {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Category does not exist");
                    responseDto.setContent(categoryRequestDto);
                }
            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(categoryRequestDto);

        }
    }

    @Transactional(readOnly = true)
    public void getAllCategories() {

        try {
            List<Category> categories = categoryRepository.findAll();
            List<CategoryRequestDto> categoriesDto = new ArrayList<>();

            if (!categories.isEmpty()) {
                for (Category category : categories) {
                    categoriesDto.add(CategoryMapper.mapToCategoryRequestDto(category));
                }
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Category List successfully");
                responseDto.setContent(categoriesDto);
            } else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Category List is empty");
                responseDto.setContent(null);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }

    }

    @Transactional
    public void deleteCategory(CategoryRequestDto categoryRequestDto) {
        try {
            if (categoryRequestDto.getId() == null) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Category id not recognized");
                responseDto.setContent(null);
            } else {
                if (categoryRepository.existsById(categoryRequestDto.getId())) {
                    categoryRepository.deleteById(categoryRequestDto.getId());
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Category deleted successfully");
                    responseDto.setContent(categoryRequestDto);
                } else {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Category does not exist");
                    responseDto.setContent(null);
                }
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }
}
