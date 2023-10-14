package ru.karyeragame.paymentsystem.security.recoverPassword;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.exceptions.ErrorPasswordRecovery;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.mailsender.service.EmailService;
import ru.karyeragame.paymentsystem.security.recoverPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.security.service.SecurityService;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.UUID;

@Validated
@RestController
@Slf4j
@AllArgsConstructor
public class recoverController {
    SecurityService securityService;

    /**
     * @param userEmail - введённый пользователем e-mail для восстановления пароля
     */
    @PostMapping("/user/resetPassword")
    public void resetPassword(@RequestParam("userEmail") String userEmail) {  // не забыть валидацию
        securityService.resetPassword(userEmail);
    }

//    @GetMapping("/user/changePassword")
//    public String showChangePasswordPage(PasswordResetToken passwordResetToken,
//                                         @RequestParam("token") String token) {
//        String result = securityService.validatePasswordResetToken(token);
//        if (result != null) {
//
//        return null; // Нужно что-то вернуть ..
//
//        }

//        else {
//            passwordResetToken.addAttribute("token", token);
//            return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//        }
//        return null;
//    }
}
