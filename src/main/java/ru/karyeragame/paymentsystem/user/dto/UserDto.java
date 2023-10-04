package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Long avatarId;
    private LocalDateTime createdOn;

}
