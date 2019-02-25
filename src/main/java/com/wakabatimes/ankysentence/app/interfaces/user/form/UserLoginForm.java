package com.wakabatimes.ankysentence.app.interfaces.user.form;

import lombok.Data;

@Data
public class UserLoginForm {
    private String userMailAddress;
    private String userPassword;
}
