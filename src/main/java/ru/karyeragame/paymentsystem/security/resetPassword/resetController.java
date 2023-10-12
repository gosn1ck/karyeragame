package ru.karyeragame.paymentsystem.security.resetPassword;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.mailsender.service.EmailService;
import ru.karyeragame.paymentsystem.security.resetPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.security.service.SecurityService;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.UUID;

@Validated
@RestController
@Slf4j
@AllArgsConstructor
public class resetController {
    UserService userService;
    EmailService emailService;
    SecurityService securityService;
    String contextPath;


    @PostMapping("/user/resetPassword")
    public void resetPassword(HttpServletRequest request,
                                          @RequestParam("email") String userEmail) {
        log.info("Find user by email {} started", userEmail);
        User user = userService.findUserByEmail(userEmail);

        // создаём токен
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = userService.createPasswordResetTokenForUser(user, token);
        log.info("User Id {} created token for reset password", user.getId());

        String linkForResetPassword = contextPath + "\"/user/changePassword?token=\"" + token;

        emailService.sendSimpleMessage(userEmail, "Восстановление пароля в платежной системе Игры «КарьерА»",
            "Это письмо платежной системы Игры «КарьерА». " +
                "Вы запрашивали восстановление пароля. Пройдите по сслыке, чтобы задать новый пароль: " +
                 linkForResetPassword +
                "\n" +
                "Это письмо отправлено автоматически, на него не нужно отвечать.");
    }

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(PasswordResetToken passwordResetToken,
                                         @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(token);
        if (result != null) {

        return null; // Нужно что-то вернуть ..

        }

//        else {
//            passwordResetToken.addAttribute("token", token);
//            return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//        }
        return null;
    }
}
