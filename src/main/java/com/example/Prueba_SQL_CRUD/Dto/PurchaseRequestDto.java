package com.example.Prueba_SQL_CRUD.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequestDto {
    private Long userId;
    private List<PurchaseItemDto> items;
}
