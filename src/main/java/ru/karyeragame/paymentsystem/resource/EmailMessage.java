package ru.karyeragame.paymentsystem.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Тестовый класс для проверки emailSender
 */

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailMessage {

    private String to;
    private String subject;
    private String message;
    private String path;
}
