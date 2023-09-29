package ru.karyeragame.authservice.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayloadDto {
    @JsonProperty("sub")
    private String email;
}
