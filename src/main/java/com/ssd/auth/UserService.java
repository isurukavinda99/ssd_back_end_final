package com.ssd.auth;


import com.ssd.auth.dto.SignInDto;
import com.ssd.auth.dto.SignInRespond;
import com.ssd.auth.dto.SignUpDto;
import com.ssd.auth.dto.SignUpRespond;
import com.ssd.auth.jwt.JwtUtil;
import com.ssd.auth.util.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public SignUpRespond registerUser(SignUpDto user){

        Role userRole = roleRepository.findByRole(user.getRole().toUpperCase()).orElseThrow( () -> new ObjectNotFoundException("User role not found"));

        User saveToBe = User.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role( new HashSet<>(Arrays.asList(userRole)))
                .build();

        saveToBe = userRepository.save(saveToBe);

        return SignUpRespond.builder()
                .userName(saveToBe.getUsername())
                .firstName(saveToBe.getFirstName())
                .lastName(saveToBe.getLastName())
                .role(userRole.getRole())
                .build();

    }

    public SignInRespond login(SignInDto request) throws Exception {
        String userName = request.getUserName();
        String password = request.getPassword();

        authenticate(userName , password);

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        String token = jwtUtil.generateToken(userDetails);

        return SignInRespond.builder()
                .userName(userName)
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .jwt(token)
                .build();

    }

    private void authenticate(String userName , String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        }catch (DisabledException e){
            // TODO : make proper exceptions
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials");
        }
    }

}
