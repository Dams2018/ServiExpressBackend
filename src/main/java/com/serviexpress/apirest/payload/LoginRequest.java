package com.serviexpress.apirest.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LoginRequest implements Serializable{
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean active(Boolean active) {
        return active;
    }

    
}