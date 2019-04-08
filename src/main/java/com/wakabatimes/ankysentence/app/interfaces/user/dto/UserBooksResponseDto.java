package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class UserBooksResponseDto {
    private String id;
    private String mail;
    private List<UserBookResponseDto> books;
    private String status;
    private String message;

    public UserBooksResponseDto(User user){
        this.id = user.getUserId().getValue();
        this.mail = user.getUserMailAddress().getValue();
    }
}
