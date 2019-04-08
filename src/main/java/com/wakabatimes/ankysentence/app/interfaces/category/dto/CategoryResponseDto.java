package com.wakabatimes.ankysentence.app.interfaces.category.dto;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private String id;
    private String name;
    private String status;
    private String message;
}
