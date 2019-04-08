package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import lombok.Data;

@Data
public class UserSubscribeResponseDto {
    private String id;
    private String mail;
    private String status;
    private String message;

    public UserSubscribeResponseDto(User user){
        this.id = user.getUserId().getValue();
        this.mail = user.getUserMailAddress().getValue();
    }
}
