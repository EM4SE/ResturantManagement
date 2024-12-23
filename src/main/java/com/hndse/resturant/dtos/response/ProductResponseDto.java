package com.hndse.resturant.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {
    @Size(max = 30,message = "Name must not exceed 30 characters.")
    private String name;
    @Size(max = 500,message = "Description must not exceed 500 characters.")
    private String description;
    private Double price;
    private String category;
    private String imagePath;
}
