package tw.wally.aop.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author - wally55077@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    public int id;
    public String email;
    public String token;
    public long expiryTime;

    public LoginResponse(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
