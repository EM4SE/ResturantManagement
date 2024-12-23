package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.CategoryRequestDto;
import com.hndse.resturant.entities.Category;

public class CategoryMapper {
    public static Category mapToCategoty(CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        return category;
    }
}
