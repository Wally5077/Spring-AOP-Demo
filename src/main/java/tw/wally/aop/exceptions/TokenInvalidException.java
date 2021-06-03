package tw.wally.aop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

/**
 * @author - wally55077@gmail.com
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String resource) {
        super(format("Token %s invalid", resource));
    }

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenInvalidException(Throwable cause) {
        super(cause);
    }

    public TokenInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
