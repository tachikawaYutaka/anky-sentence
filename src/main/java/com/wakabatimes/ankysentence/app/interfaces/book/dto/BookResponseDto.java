package com.wakabatimes.ankysentence.app.interfaces.book.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private String id;
    private String name;
    private String status;
    private String message;
}
