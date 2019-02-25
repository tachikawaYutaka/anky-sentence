package com.wakabatimes.ankysentence.app.domain.model.user;

import lombok.Getter;

public enum UserStatus {
    INACTIVATE(0),
    ACTIVATE(1);

    @Getter
    private Integer id;

    private UserStatus(Integer id) {
        this.id = id;
    }

    public static UserStatus getById(Integer id) {
        for(UserStatus userStatus : UserStatus.values()){
            if(userStatus.id == id){
                return userStatus;
            }
        }
        return UserStatus.INACTIVATE;
    }


}
