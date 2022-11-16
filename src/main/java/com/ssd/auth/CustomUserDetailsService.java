package com.ssd.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                                    .orElseThrow(()-> new UsernameNotFoundException("User not fount with user name : '"+username+"'"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user){

        Set<SimpleGrantedAuthority> authorities = new HashSet();

        user.getRole().forEach( role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });

        return authorities;
    }
}
