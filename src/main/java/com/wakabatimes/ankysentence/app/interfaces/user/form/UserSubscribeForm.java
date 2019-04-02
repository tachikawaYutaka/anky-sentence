package com.wakabatimes.ankysentence.app.interfaces.user.form;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
@Data
public class UserSubscribeForm {
    private String mail;
    private String password;

    public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
