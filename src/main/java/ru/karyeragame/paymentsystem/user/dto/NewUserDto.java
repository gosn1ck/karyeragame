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
    @Pattern(regexp = "^[^\\s][a-zA-Z0-9][\\sa-zA-Z0-9][\\p{L}\\p{N}\\p{P}\\p{Z}]+$")
    //кириллица, латиница, 0-9, видимые символы
    @NotBlank
    @Size(min = 1, max = 20)
    private String username;
    // латиница (верхний и нижний регистры), 0-9, символы . _ - допустимы, точка не первый или последний символ
    @Email(regexp = "^(?=.{1,255}@)[A-Za-z0-9_-]" +
            "+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]" +
            "+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotBlank
    @Size(min = 6, max = 255)
    private String email;
    @Pattern(regexp = "^[^\\s][\\p{ASCII}\\p{Punct}\\p{Graph}]+$")// латиница, 0-9, видимые символы
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;
}
