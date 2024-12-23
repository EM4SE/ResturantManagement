package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.entities.Category;

public class CategoryMapper {
    public static Category mapToCategoty(CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setId(categoryRequestDto.getId());
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        return category;
    }

    public static CategoryRequestDto mapToCategoryRequestDto(Category category){
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setId(category.getId());
        categoryRequestDto.setName(category.getName());
        categoryRequestDto.setDescription(category.getDescription());
        return categoryRequestDto;
    }
}
