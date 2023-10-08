package ru.karyeragame.paymentsystem.mailsender.service;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.Locale;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String path) throws MessagingException, MessagingException;

}
