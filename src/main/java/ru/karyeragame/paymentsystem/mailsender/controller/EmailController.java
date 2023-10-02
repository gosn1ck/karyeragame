package ru.karyeragame.paymentsystem.mailsender.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.mailsender.resource.EmailMessage;
import ru.karyeragame.paymentsystem.mailsender.service.EmailService;

/*
 * Тестовый контроллер
 */

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendSimpleMessage(emailMessage.to(), emailMessage.subject(), emailMessage.message());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/send-email/attach")
    public ResponseEntity sendEmailWithAttach(@RequestBody EmailMessage emailMessage) throws MessagingException {
        emailService.sendMessageWithAttachment(emailMessage.to(), emailMessage.subject(),
                emailMessage.message(), emailMessage.path());
        return ResponseEntity.ok("Success");
    }
}
