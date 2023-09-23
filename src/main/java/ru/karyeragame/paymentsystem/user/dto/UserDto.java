package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.enums.Roles;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private Roles role;
    private LocalDateTime createdOn;

}
