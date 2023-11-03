package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.enums.user.ProfileStatus;

@Builder
@Data
public class ShortUserDto {
    private Long id;
    private String username;
    private String email;
    private ProfileStatus status;

}
