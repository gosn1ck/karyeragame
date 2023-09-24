package ru.karyeragame.paymentsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public record EmailServiceImpl(JavaMailSender javaMailSender) implements EmailService {

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("careerA.game@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
        log.info("Отправлено письмо: {}", message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String path)
            throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("careerA.game@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        ClassPathResource file = new ClassPathResource(path);
        helper.addAttachment("Money", file, "image/jpeg");

        javaMailSender.send(message);
        log.info("Отправлено письмо: {}", message);
    }


}
