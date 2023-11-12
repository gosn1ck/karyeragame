package ru.karyeragame.paymentsystem.mailsender.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.mailsender.resource.EmailMessage;
import ru.karyeragame.paymentsystem.mailsender.service.EmailService;

/*
 * Тестовый контроллер
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/send-email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendSimpleMessage(emailMessage.to(), emailMessage.subject(), emailMessage.message());
    }

    @PostMapping("/attach")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailWithAttach(@RequestBody EmailMessage emailMessage) throws MessagingException {
        emailService.sendMessageWithAttachment(emailMessage.to(), emailMessage.subject(),
                emailMessage.message(), emailMessage.path());
    }
}
