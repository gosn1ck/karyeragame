package ru.karyeragame.paymentsystem.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Leonid", description = "")
    private String username;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email shouldn't be blank")
    @Schema(example = "user@gmail.com", description = "")
    private String email;
    @Schema(example = "https://u24.ru/img/news/article_big_417211514258010.jpg", description = "")
    private Long avatar;
}
