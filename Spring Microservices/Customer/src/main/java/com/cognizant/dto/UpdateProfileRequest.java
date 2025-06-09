
package com.cognizant.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String password;
    private String email;

}
