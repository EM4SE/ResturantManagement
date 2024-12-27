package com.hndse.resturant.mappers;

import com.hndse.resturant.dtos.request.TableRequestDto;
import com.hndse.resturant.entities.Tables;

public class TableMapper {
    public static Tables mapToTable(TableRequestDto tableRequestDto) {
        Tables tables = new Tables();
        tables.setId(tableRequestDto.getId());
        tables.setNumber((tableRequestDto.getNumber()));
        tables.setSeats(tableRequestDto.getSeats());
        tables.setStatus(tableRequestDto.getStatus());
        tables.setCreatedAt(tableRequestDto.getCreatedAt());
        tables.setUpdatedAt(tableRequestDto.getUpdatedAt());
        return tables;
    }

    public static TableRequestDto mapToTableRequestDto(Tables tables) {
        TableRequestDto tableRequestDto = new TableRequestDto();
        tableRequestDto.setId(tables.getId());
        tableRequestDto.setNumber(tables.getNumber());
        tableRequestDto.setSeats(tables.getSeats());
        tableRequestDto.setStatus(tables.getStatus());
        tableRequestDto.setCreatedAt(tables.getCreatedAt());
        tableRequestDto.setUpdatedAt(tables.getUpdatedAt());
        return tableRequestDto;
    }
}
