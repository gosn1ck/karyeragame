package ru.karyeragame.authservice.usercredential;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link UserCredential}
 */
@Getter
@Builder
@AllArgsConstructor
public class UserCredentialDto implements Serializable {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Length(min = 6, max = 254, message = "Email must be from 6 to 254 chars")
    private final String email;
    @NotBlank(message = "Password must not be blank")
    @Length(min = 8, max = 60, message = "Login must be from 2 to 250 chars")
    @Pattern(regexp = "^(?!.*[^\\x00-\\x7F])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,60}$",
            message = "Password must contain at least one digit, one lowercase and one uppercase letter" +
                    " and must be from 8 to 60 chars")
    private final String password;
    @NotBlank(message = "Match password must not be blank")
    private final String matchPassword;
}