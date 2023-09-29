package ru.karyeragame.paymentsystem.mailsender.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public record EmailServiceImpl(JavaMailSender sender, @Value("${email.from}") String emailFrom)
        implements EmailService {

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        sender.send(message);
        log.info("Отправлено письмо: {}", message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String path)
            throws MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailFrom);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        ClassPathResource file = new ClassPathResource(path);
        helper.addAttachment("Money", file, "image/jpeg");

        sender.send(message);
        log.info("Отправлено письмо: {}", message);
    }
}
