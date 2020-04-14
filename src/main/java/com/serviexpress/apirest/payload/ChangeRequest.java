//Solicitud de registro

package com.porta.porta.payload;

import javax.validation.constraints.*;

public class ChangeRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    
}