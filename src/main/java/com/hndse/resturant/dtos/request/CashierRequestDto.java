package com.hndse.resturant.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashierRequestDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private int mobile;
    private String Status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
