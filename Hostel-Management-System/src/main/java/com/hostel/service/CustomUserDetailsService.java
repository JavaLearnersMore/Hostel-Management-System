package com.hostel.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.hostel.entity.User;
import com.hostel.exception.ResourceNotFoundException;
import com.hostel.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
        		.orElseThrow(() -> new ResourceNotFoundException(
  	                    "User not found with this id: " + username)); 

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority(user.getRole()))
        );
    }
}

