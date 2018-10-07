package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.CustomUserDetails;
import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opUser = usersRepository.findByUsername(username);
        opUser.orElseThrow(() ->
                new UsernameNotFoundException("User with username [" + username + "] does not exist."));
        return opUser.map(CustomUserDetails::new).get();
    }

    public Optional<User> getCurrentUser(String username) {
        return usersRepository.findByUsername(username);
    }
}
