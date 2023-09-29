package ru.karyeragame.paymentsystem.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewUserDto {
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Z}]+$") //кириллица, латиница, 0-9, видимые символы
    @NotBlank
    @Size(min = 1, max = 20)
    private String username;
    @Email
    @NotBlank
    private String email;
    private Long avatar;
}
