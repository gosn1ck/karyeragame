package ru.karyeragame.paymentsystem.security.recoverPassword;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoRequest;
import ru.karyeragame.paymentsystem.security.service.SecurityService;

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

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(@RequestParam("token") String token) {
        return securityService.showChangePasswordPage(token);
    }

    @PostMapping("/user/savePassword")
    public void savePassword(@Valid PasswordDtoRequest passwordDtoRequest) {

        securityService.savePassword(passwordDtoRequest);


    }

}
