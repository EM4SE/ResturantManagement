package com.hndse.resturant.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashierResponseDto {
    private Integer id;
    private String name;
    private String username;
    private int mobile;
    private String Status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

