package ru.karyeragame.paymentsystem.security.recoverPassword;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class recoverControllerTest {
    @MockBean
    private MailSender mailSender;

    @Test
    void resetPassword() {
    }
}