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
    @Pattern(regexp = "^(?! )[а-яА-Яa-zA-Z0-9\\s\\S]+$")
    //кириллица, латиница, 0-9, видимые символы
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
    // латиница (верхний и нижний регистры), 0-9, символы . _ - допустимы, точка не первый или последний символ
    @Email(regexp = "^(?! )[a-zA-Z0-9\\s\\S]+@[a-zA-Z0-9\\s\\S]+\\.[a-zA-Z0-9\\s\\S]{2,}$")
    @NotBlank
    @Size(min = 6, max = 255)
    private String email;
    @Pattern(regexp = "^[^\\s][\\p{ASCII}\\p{Punct}\\p{Graph}]+$")// латиница, 0-9, видимые символы
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;
}
