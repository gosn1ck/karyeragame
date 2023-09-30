package ru.karyeragame.paymentsystem.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.user.model.Avatar;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {
    @Schema(example = "2764", description = "")
    private Long id;
    @Schema(example = "Leonid", description = "")
    private String username;
    @Schema(example = "user@gmail.com", description = "")
    private String email;
    @Schema(example = "https://u24.ru/img/news/article_big_417211514258010.jpg", description = "")
    private Avatar avatar;
    @Schema(example = "2020-05-30T22:33:19.953567014", description = "")
    private LocalDateTime createdOn;

}
