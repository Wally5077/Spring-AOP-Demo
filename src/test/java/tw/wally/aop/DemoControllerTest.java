package tw.wally.aop;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import tw.wally.aop.model.request.SignUpRequest;
import tw.wally.aop.model.response.SignUpResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AopApplication.class)
public class DemoControllerTest extends AbstractSpringBootTest {

    private static final String API_PREFIX = "/api/demos";
    private static final String API_DEMO_ID_SUFFIX = "/{demoId}";
    private static final String EXISTS_DEMO_ID = "existsDemoId";
    private static final String NONEXISTENT_DEMO_ID = "nonexistentDemoId";

    @Test
    public void TestAdviceSequenceByExistsDemoId() throws Exception {
        getDemoApi(EXISTS_DEMO_ID).andExpect(status().isOk());
    }

    @Test
    public void TestAdviceSequenceByNonexistentDemoId() throws Exception {
        getDemoApi(NONEXISTENT_DEMO_ID).andExpect(status().isInternalServerError());
    }

    @Test
    public void TestNotOperation() throws Exception {
        signUp();
    }

    private SignUpResponse signUp() throws Exception {
        return getBody(mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SignUpRequest("NAME", "EMAIL", "PASSWORD"))))
                .andExpect(status().isOk()), SignUpResponse.class);
    }

    private ResultActions getDemoApi(String demoId) throws Exception {
        return mockMvc.perform(get(API_PREFIX + API_DEMO_ID_SUFFIX, demoId));
    }

}