package com.wakabatimes.ankysentence.app.infrastructure.user.dto;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String mailAddress;
    private String password;
    private Integer status;
    private Integer role;

    public UserDto (){

    }

    public UserDto(User user) {
        this.id = user.getUserId().getValue();
        this.mailAddress = user.getUserMailAddress().getValue();
        this.password = user.getUserPassword().getValue();
        this.status = user.getUserStatus().getId();
        this.role = user.getUserRole().getId();
    }
}
