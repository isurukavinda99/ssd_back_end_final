package com.ssd.auth;

import com.ssd.auth.dto.SignInDto;
import com.ssd.auth.dto.SignInRespond;
import com.ssd.auth.dto.SignUpDto;
import com.ssd.auth.dto.SignUpRespond;
import com.ssd.auth.util.FieldValidationException;
import com.ssd.auth.util.PasswordValidator;
import com.ssd.auth.util.RoleUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/sign_up")
    public ResponseEntity<SignUpRespond> signUp(@RequestBody SignUpDto request) throws FieldValidationException {

        // check username null
        if(request.getUsername().isBlank()){
            throw new FieldValidationException("Username is required");
        }

        // check first name null
        if(request.getFirstName().isBlank()){
            throw new FieldValidationException("User first name is required");
        }

        // check last name null
        if(request.getLastName().isBlank()){
            throw new FieldValidationException("User last name is required");
        }

        // check email null
        if(request.getEmail().isBlank()){
            throw new FieldValidationException("Email is required");
        }

        // check email is valid
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(request.getEmail()).matches()){
            throw new FieldValidationException("Give proper email address");
        }

        // check password null
        if(request.getPassword().isBlank()){
            throw new FieldValidationException("Password is required");
        }

        if(!request.getPassword().equals(request.getPasswordRe())){
            throw new FieldValidationException("Passwords miss match");
        }

        // password regx check
        if(!PasswordValidator.isValid(request.getPassword())){
            throw new FieldValidationException("Give proper password");
        }

        // check role of user account
        if(!RoleUtil.getRoles().contains(request.getRole())){
            throw new FieldValidationException("please give existing role");
        }

        return new ResponseEntity<>(userService.registerUser(request) , HttpStatus.CREATED);

    }

    @PostMapping("/sign_in")
    public ResponseEntity<SignInRespond> signIn(@RequestBody SignInDto request) throws Exception {
        return new ResponseEntity<>(userService.login(request) , HttpStatus.OK);
    }

}
