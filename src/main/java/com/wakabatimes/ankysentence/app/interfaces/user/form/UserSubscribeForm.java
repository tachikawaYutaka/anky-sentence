package com.wakabatimes.ankysentence.app.interfaces.user.form;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserSubscribeForm {
    private String userMailAddress;
    private String userPassword;

    public void encrypt(PasswordEncoder encoder){
        this.userPassword = encoder.encode(userPassword);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                ", userMailaddress='" + userMailAddress + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
