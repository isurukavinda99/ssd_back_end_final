package com.ssd.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRespond {
    private String userName;
    private String firstName;
    private String lastName;
    private String role;
}
