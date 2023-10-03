package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Avatar avatar;
    private LocalDateTime createdOn;

}
