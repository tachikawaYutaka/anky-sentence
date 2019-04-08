package com.wakabatimes.ankysentence.app.interfaces.user.form;

import lombok.Data;

@Data
public class UserPasswordResetForm {
    private String hash;
    private String password;
}
