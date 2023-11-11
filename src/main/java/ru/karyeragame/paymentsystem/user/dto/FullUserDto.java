package ru.karyeragame.paymentsystem.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.karyeragame.paymentsystem.user.model.ProfileStatus;
import ru.karyeragame.paymentsystem.user.model.Roles;

import java.time.LocalDateTime;

@Builder
@Data
public class FullUserDto {
    private Long id;
    private String name;
    private String email;
    private Long avatarId;
    private LocalDateTime createdOn;
    private Roles role;
    private ProfileStatus status;
    private LocalDateTime removedOn;
    private Long removedBy;

}
