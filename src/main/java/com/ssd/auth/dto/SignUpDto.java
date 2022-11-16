package com.ssd.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordRe;
    private String role;

}
