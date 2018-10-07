package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.controller.emailModel.TokenInfo;
import com.sorsix.bloodscreening.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;

@RequestMapping("/secured/email")
@RestController
public class EmailController {

    private final EmailService service;
    private final JavaMailSender sender;
    private final static Logger logger = LoggerFactory.getLogger(EmailController.class);

    public EmailController(EmailService service, JavaMailSender sender) {
        this.service = service;
        this.sender = sender;
    }

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping("/results/{id}")
    public boolean tryToSendResultsDoneToPatient(@PathVariable Integer id) {
        String email = service.getUsersEmail(id);
        try {
            logger.info("Sending results mail to [{}]", email);
            sendBloodTestDoneEmail(email);
            return true;
        } catch (Exception e) {
            logger.error("Mail failed to send to [{}]", email, e);
            return false;
        }
    }

    @PreAuthorize("hasAnyRole('LAB')")
    @PostMapping("/token/{email}")
    public boolean tryToSendTokenToPatient(@PathVariable String email, @RequestBody TokenInfo tokenInfo) {
        try {
            logger.info("Sending token mail to [{}]", email);
            sendTokenCreatedEmail(email, tokenInfo);
            return true;
        } catch (Exception e) {
            logger.error("Mail failed to send to [{}]", email, e);
            return false;
        }
    }

    private void sendBloodTestDoneEmail(String email) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setSubject("Крвна Слика");
        helper.setText("Вашата крвна слика е прегледана. "
                        + "Можете да се најавите на системот за да ги видите резултатите.");
        sender.send(message);
    }

    private void sendTokenCreatedEmail(String email, TokenInfo tokenInfo) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setSubject("Крвна Слика");
        helper.setText("Вашиот токен е креиран. Можете да се најавите на системот"
                        + " за да ги видите резултатите со "
                        + "username: " + tokenInfo.username + " и password: " + tokenInfo.password +"."
                        + " Кога ќе бидат готови ќе бидете известени.");
        sender.send(message);
    }

}