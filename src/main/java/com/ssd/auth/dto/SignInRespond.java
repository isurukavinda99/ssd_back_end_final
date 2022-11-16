package com.ssd.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class SignInRespond {

    private String userName;
    private List<String> roles;
    private String jwt;

}
