package com.ssd.authTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ssd.auth.AuthController;
import com.ssd.auth.UserService;
import com.ssd.auth.dto.SignInDto;
import com.ssd.auth.dto.SignInRespond;
import com.ssd.auth.dto.SignUpDto;
import com.ssd.auth.dto.SignUpRespond;
import jdk.net.SocketFlow;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.SelectorResolutionResult;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    private SignUpDto signUpDto = SignUpDto.builder()
                    .username("kavinda")
                    .firstName("isuru")
                    .lastName("kavinda")
                    .email("kavinda@gmail.com")
                    .password("Hy3@nai81n")
                    .passwordRe("Hy3@nai81n")
                    .role("USER")
                    .build();
    private SignUpRespond signUpRespond = SignUpRespond.builder()
                    .userName("kavinda")
                    .firstName("isuru")
                    .lastName("kavinda")
                    .role("ROLE_USER")
                    .build();

    private SignInDto signInDto = SignInDto.builder().userName("admin").password("12345").build();

    private SignInRespond signInRespond;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthController authController;

    @Test
    public void keyCheck() throws Exception {

        when(authController.signUp(signUpDto)).thenReturn(new ResponseEntity<>(signUpRespond , HttpStatus.CREATED));

        this.mockMvc.perform(
                post("/api/v1/auth/sign_up"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(signUpRespond.toString()));
    }

    @Test
    public void tetsLlogin() throws Exception {
        mockMvc.perform(
                post("/api/v1/auth/sign_in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(signInDto))
                )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
