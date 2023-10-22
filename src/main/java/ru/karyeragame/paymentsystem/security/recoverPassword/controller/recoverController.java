package ru.karyeragame.paymentsystem.security.recoverPassword.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoFinal;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoRequest;
import ru.karyeragame.paymentsystem.security.recoverPassword.service.SecurityService;

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

    /**
     * @param token - переходя по ссылке для восстановления пароля пользователь передаёт токен из этой ссылки
     * @return - если токен корректный, открывается страница для введения нового пароля
     * Как эта страница должна открываться - видимо вопрос к фронту
     */
    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(@RequestParam("token") String token) {
        return securityService.showChangePasswordPage(token);
    }

    /**
     * @param newPassword  после введения нового пароля, пользователь нажимает кнопку "сохранить пароль"
     * @param token если токен корректен и пароль валиден, происходит сохранение нового пароля,
     *              пользователю отправляется письмо с новым паролем
     */
    @PostMapping("/user/savePassword")
    public void savePassword(@RequestParam @Valid String newPassword,
                             @RequestParam String token) {
        PasswordDtoRequest passwordDtoRequest = new PasswordDtoRequest(token, newPassword);
        PasswordDtoFinal result = securityService.savePassword(passwordDtoRequest);
    }
}
