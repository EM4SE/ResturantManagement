package com.hndse.resturant.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDto {
    @NotBlank(message = "Name is required.")
    @Size(max = 30,message = "Name must not exceed 30 characters.")
    private String name;

    @Size(max = 500,message = "Description must not exceed 500 characters.")
    private String description;
}
