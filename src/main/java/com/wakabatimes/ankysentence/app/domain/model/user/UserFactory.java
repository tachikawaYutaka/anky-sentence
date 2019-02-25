package com.wakabatimes.ankysentence.app.domain.model.user;

import java.util.UUID;

public class UserFactory {
    public static User create(UserMailAddress userMailAddress, UserPassword userPassword){
        UserId userId = new UserId(UUID.randomUUID().toString());
        UserStatus userStatus = UserStatus.INACTIVATE;
        UserRole userRole = UserRole.ROLE_USER;

        return new User(userId, userMailAddress, userPassword, userStatus, userRole);
    }
}
