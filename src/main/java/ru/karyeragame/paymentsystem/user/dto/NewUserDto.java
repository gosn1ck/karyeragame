package ru.karyeragame.paymentsystem.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewUserDto {
    //    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[а-я])(?=.*[А-Я])(?=.*[@#$%^&+=])(?=\\S+$)$")
    @NotBlank
    private String username;
    @Email(regexp = "^(.+)@(.+)$")
    @NotBlank
    private String email;
    //    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)$")
    @NotBlank
    private String password;
    private String avatar;
}
