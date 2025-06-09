
package com.cognizant.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String password;
    private String email;

    public UpdateProfileRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
