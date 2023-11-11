
package ru.karyeragame.paymentsystem.avatar.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AvatarDto {

    private Long id;

    private String url;
    private Long userId;
}
