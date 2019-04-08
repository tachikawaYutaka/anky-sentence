package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import lombok.Data;

@Data
public class UserUpdateResponseDto {
    private String mail;
    private String status;
    private String message;
}
