package ru.karyeragame.paymentsystem.security.service;

public interface SecurityService {

    String validatePasswordResetToken(String token);
}
