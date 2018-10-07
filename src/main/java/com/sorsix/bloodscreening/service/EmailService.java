package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final UsersRepository usersRepository;
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String getUsersEmail(Integer userId) {
        logger.info("Fetching email for user with id [{}]", userId);
        return usersRepository.findById(userId)
                .map(User::getEmail)
                .orElse(null);
    }
}
