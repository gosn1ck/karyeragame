package ru.karyeragame.paymentsystem.security.resetPassword.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.security.resetPassword.dto.GenericResponse;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.Locale;
import java.util.UUID;

@Validated
@RestController
@Slf4j
@AllArgsConstructor
public class resetController {
    UserService userService;
    MailSender mailSender;

    @PostMapping("/user/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        log.info("Find user by email {} started", userEmail);
        User user = userService.findUserByEmail(userEmail);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        log.info("User Id {} created token for reset password", user.getId());

        mailSender.send(constructResetTokenEmail(getAppUrl(request),
            request.getLocale(), token, user));
        return new GenericResponse(
            messages.getMessage("message.resetPasswordEmail", null,
                request.getLocale()));
    }

    // этого не должно быть в контроллере, но где?
    private SimpleMailMessage constructResetTokenEmail(
        String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?token=" + token;
        String message = messages.getMessage("message.resetPassword",
            null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }


}
