package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.ProductRequestDto;
import com.hndse.resturant.dtos.response.ProductResponseDto;
import com.hndse.resturant.entities.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(productRequestDto.getId());
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        product.setImagePath(productRequestDto.getImagePath());
        product.setAvailability(productRequestDto.getAvailability());
        product.setCreatedAt(productRequestDto.getCreatedAt());
        product.setUpdatedAt(productRequestDto.getUpdatedAt());

        return product;

    }

    public static ProductRequestDto mapToProductRequestDto(Product product) {
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setId(product.getId());
        productRequestDto.setName(product.getName());
        productRequestDto.setDescription(product.getDescription());
        productRequestDto.setPrice(product.getPrice());
        productRequestDto.setCategory(product.getCategory());
        productRequestDto.setImagePath(product.getImagePath());
        productRequestDto.setAvailability(product.getAvailability());
        productRequestDto.setCreatedAt(product.getCreatedAt());
        productRequestDto.setUpdatedAt(product.getUpdatedAt());
        return productRequestDto;
    }


    public static ProductResponseDto mapToProductResponseDto(ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(productRequestDto.getName());
        productResponseDto.setDescription(productRequestDto.getDescription());
        productResponseDto.setPrice(productRequestDto.getPrice());
        productResponseDto.setCategory(productRequestDto.getCategory());
        productResponseDto.setImagePath(productRequestDto.getImagePath());
        return productResponseDto;
    }
}
