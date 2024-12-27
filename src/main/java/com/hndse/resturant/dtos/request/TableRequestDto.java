package com.hndse.resturant.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableRequestDto {
    private Integer id;
    @NotBlank(message = "Table Number Required")
    private Integer number;
    private Integer seats;
    private String status ="Available";
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
