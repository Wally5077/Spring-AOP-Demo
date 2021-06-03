package tw.wally.aop.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * @author - wally55077@gmail.com
 */
@Data
@NoArgsConstructor
public class UpdateUserRequest {
    public Integer id;
    public String name;

    public UpdateUserRequest(String name) {
        this.name = name;
    }

    public Optional<String> mayHaveName() {
        return ofNullable(name);
    }
}
