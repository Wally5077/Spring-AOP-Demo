package tw.wally.aop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tw.wally.aop.services.TokenService;

import java.util.List;

/**
 * @author - wally55077@gmail.com
 */
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractSpringBootTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected TokenService tokenService;

    @SneakyThrows
    public String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public <T> T fromJson(String json, Class<T> type) {
        return objectMapper.readValue(json, type);
    }

    protected <T> T getBody(ResultActions actions, Class<T> type) {
        return fromJson(getContentAsString(actions), type);
    }

    protected <T> List<T> getBody(ResultActions actions, TypeReference<List<T>> type) {
        return fromJson(getContentAsString(actions), type);
    }

    @SneakyThrows
    protected String getContentAsString(ResultActions actions) {
        return actions
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @SneakyThrows
    public <T> List<T> fromJson(String json, TypeReference<List<T>> type) {
        return objectMapper.readValue(json, type);
    }

    protected MockHttpServletRequestBuilder withToken(String token, MockHttpServletRequestBuilder builder) {
        withToken(token).decorate(builder);
        return builder;
    }

    protected WithHeader withToken(String token) {
        return request -> request.header("Authorization", token);
    }

}
