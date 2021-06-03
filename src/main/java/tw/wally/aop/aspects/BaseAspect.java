package tw.wally.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author - wally55077@gmail.com
 */
@AllArgsConstructor
public abstract class BaseAspect {

    protected final ObjectMapper mapper;

    @SneakyThrows
    public String toJson(Object... objects) {
        return mapper.writeValueAsString(objects);
    }

}
