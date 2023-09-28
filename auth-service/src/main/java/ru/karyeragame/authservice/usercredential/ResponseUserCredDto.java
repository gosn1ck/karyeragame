package ru.karyeragame.authservice.usercredential;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUserCredDto {
    private final Long id;
    private final String email;
}
