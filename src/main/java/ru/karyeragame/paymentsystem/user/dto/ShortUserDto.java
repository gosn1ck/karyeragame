package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.user.model.ProfileStatus;

@Builder
@Data
public class ShortUserDto {
    private Long id;
    private String name;
    private String email;
    private ProfileStatus status;

}
