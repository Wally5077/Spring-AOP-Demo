package tw.wally.aop.services;

import org.springframework.stereotype.Service;
import tw.wally.aop.exceptions.TokenInvalidException;
import tw.wally.aop.model.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

/**
 * @author - wally55077@gmail.com
 */
@Service
public class FakeTokenService implements TokenService {

    private static final long DEFAULT_TOKEN_EXPIRE_TIME = TimeUnit.DAYS.toMillis(1);
    private final Map<String, Token> tokens = new HashMap<>();

    @Override
    public Token createToken(int id) {
        var expireTime = currentTimeMillis() + DEFAULT_TOKEN_EXPIRE_TIME;
        return tokens.computeIfAbsent(UUID.randomUUID().toString(), token -> new Token(token, expireTime));
    }

    @Override
    public void validateToken(String token) {
        if (!tokens.containsKey(token)
                || currentTimeMillis() >= tokens.get(token).getExpiryTime()) {
            throw new TokenInvalidException(token);
        }
    }

    @Override
    public void clear() {
        tokens.clear();
    }
}
