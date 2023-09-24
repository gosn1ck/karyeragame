package ru.karyeragame.paymentsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.resource.EmailMessage;
import ru.karyeragame.paymentsystem.service.EmailService;

import javax.mail.MessagingException;

/*
 * Тестовый контроллер
 */

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendSimpleMessage(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/send-email/attach")
    public ResponseEntity sendEmailWithAttach(@RequestBody EmailMessage emailMessage) throws MessagingException {
        emailService.sendMessageWithAttachment(emailMessage.getTo(), emailMessage.getSubject(),
                emailMessage.getMessage(), emailMessage.getPath());
        return ResponseEntity.ok("Success");
    }
}
