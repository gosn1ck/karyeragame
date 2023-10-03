package ru.karyeragame.paymentsystem.mailsender.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String path) throws MessagingException, MessagingException;

}
