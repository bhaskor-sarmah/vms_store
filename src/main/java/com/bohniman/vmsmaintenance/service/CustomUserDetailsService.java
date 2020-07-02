package com.bohniman.vmsmaintenance.service;

import java.util.Optional;

import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.payload.UserDetailsPayload;
import com.bohniman.vmsmaintenance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
        return user.map(UserDetailsPayload::new).get();
    }

}