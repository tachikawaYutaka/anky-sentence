package com.wakabatimes.ankysentence.app.infrastructure.user.dto;

import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import lombok.Data;

@Data
public class RelateUserHashToUserDto {
    private String id;
    private String hash;

    public RelateUserHashToUserDto() {

    }

    public RelateUserHashToUserDto(UserId userId, String hash) {
        this.id = userId.getValue();
        this.hash = hash;
    }
}
