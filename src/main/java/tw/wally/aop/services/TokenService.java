package tw.wally.aop.services;

import tw.wally.aop.model.Token;

/**
 * @author - wally55077@gmail.com
 */
public interface TokenService {

    Token createToken(int id);

    void validateToken(String token);

    void clear();
}
