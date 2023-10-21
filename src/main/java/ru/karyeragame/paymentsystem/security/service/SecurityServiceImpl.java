package ru.karyeragame.paymentsystem.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.ErrorPasswordRecovery;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.mailsender.service.EmailService;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoFinal;
import ru.karyeragame.paymentsystem.security.recoverPassword.dto.PasswordDtoRequest;
import ru.karyeragame.paymentsystem.security.recoverPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.security.recoverPassword.repository.PasswordTokenRepository;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapperImpl;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService{
    private final PasswordTokenRepository passwordTokenRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final UserMapperImpl userMapper;
    private String contextPath = "https://karyera-game.ru/";

    @Override
    public void resetPassword(String userEmail) {
        try {
            log.info("Find user by email {} started", userEmail);
            User user = userService.findUserByEmail(userEmail);
            // создаём токен
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = userService.createPasswordResetTokenForUser(user, token);

            log.info("User Id {} created token for reset password", user.getId());

            String linkForResetPassword = contextPath + "\"user/changePassword?token=\"" + token;

            emailService.sendSimpleMessage(userEmail, "Восстановление пароля в платежной системе Игры «КарьерА»",
                "Это письмо платежной системы Игры «КарьерА». " +
                    "Вы запрашивали восстановление пароля. Пройдите по ссылке, чтобы сбросить текущий пароль и " +
                    "задать новый: " +
                    linkForResetPassword +
                    "\n" +
                    "Это письмо отправлено автоматически, на него не нужно отвечать.");

        } catch (NotFoundException e) {
            log.warn("Could not find any user with the email {}", userEmail);
            throw new ErrorPasswordRecovery(e.getMessage());  // нужно ли тут бросать исключение ???
        }
    }

    @Override
    public String showChangePasswordPage(String token) {
        String result = validatePasswordResetToken(token);

        if (result != null) {
            log.warn("Used the incorrect token");
            return "Token is not correct";
        } else {
            log.info("Token accepted");
            return "redirect:/updatePassword.html?lang=";
        }
    }

    @Override
    public PasswordDtoFinal savePassword(PasswordDtoRequest passwordDtoRequest) {

        String passToken = passwordDtoRequest.getToken();
        String result = validatePasswordResetToken(passToken);

        if(result != null) {
            return new PasswordDtoFinal("Token is incorrect");
        } else {
            Long userId = passwordTokenRepository.findByToken(passToken).getUser().getId();
            userService.getUser(userId);
            userService.changeUserPassword(userId, passwordDtoRequest.getNewPassword());
                return new PasswordDtoFinal("Password is changed successful");
        }
    }

    @Override
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
            : isTokenExpired(passToken) ? "expired"
            : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().isBefore(LocalDateTime.now());
    }


}
