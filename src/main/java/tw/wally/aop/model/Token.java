package tw.wally.aop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author - wally55077@gmail.com
 */
@Data
@AllArgsConstructor
public class Token {

    private final String token;
    private final long expiryTime;

}
