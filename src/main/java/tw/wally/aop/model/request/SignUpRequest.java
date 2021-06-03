package tw.wally.aop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author - wally55077@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    public String name;
    public String email;
    public String password;
}
