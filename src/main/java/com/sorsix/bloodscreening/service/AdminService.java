package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.repository.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public AdminService(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public Optional<List<User>> getDoctors(){
        return getUsersByRole("DOCTOR");
    }

    public Optional<List<User>> getLabs(){
        return getUsersByRole("LAB");
    }

    private Optional<List<User>> getUsersByRole(String role){
       return Optional.of(usersRepository.findAllByRole(role));
    }

    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return usersRepository.save(user);
    }

    public boolean existsByUsername(String name){
        return usersRepository.existsByUsername(name);
    }

    public void deleteUser(String username) {
        usersRepository.deleteByUsername(username);
    }

    public Optional<List<User>> searchDoctorsByFullName(String query) {
        return this.extractOnlySearched(getDoctors().get(),query);
    }

    public Optional<List<User>> searchLabsByFullName(String query) {
        return this.extractOnlySearched(getLabs().get(),query);
    }

    private Optional<List<User>> extractOnlySearched(List<User> list, String query){
        if(list.isEmpty())
            return Optional.empty();
        return Optional.of(list
                .stream()
                .filter(u -> u.getFullName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList()));
    }

    public User changePasswordForUser(String newPassword, String username) {
        User user = getCurrentUser(username);
        return usersRepository.findById(user.getId())
                .map(u -> {
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return usersRepository.save(u);
                }).orElseThrow(() -> new SecurityException("Password failed to change."));
    }

    public User getCurrentUser(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exist."));
    }

    public boolean checkPasswordValidityForUser(String password, String username) {
        return passwordEncoder.matches(password, getCurrentUser(username).getPassword());
    }
}
