package ru.karyeragame.paymentsystem.mailsender.resource;

/*
 * Тестовый класс для проверки emailSender
 */

public record EmailMessage(String to, String subject, String message, String path) {
}
