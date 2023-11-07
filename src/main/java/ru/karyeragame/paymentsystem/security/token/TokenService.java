package ru.karyeragame.paymentsystem.security.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.user.model.User;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository repository;

    public void revokeAllUserTokens(User user) {
        var validUserTokens = repository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        repository.saveAll(validUserTokens);
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        repository.save(token);
    }
}
