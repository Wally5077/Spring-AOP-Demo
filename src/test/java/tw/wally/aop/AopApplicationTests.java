package tw.wally.aop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import tw.wally.aop.model.request.LoginRequest;
import tw.wally.aop.model.request.SignUpRequest;
import tw.wally.aop.model.request.UpdateUserRequest;
import tw.wally.aop.model.response.GetUserResponse;
import tw.wally.aop.model.response.LoginResponse;
import tw.wally.aop.model.response.SignUpResponse;
import tw.wally.aop.model.response.UpdateUserResponse;
import tw.wally.aop.repositories.UserRepository;
import tw.wally.aop.services.TokenService;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AopApplication.class)
public class AopApplicationTests extends AbstractSpringBootTest {

    private static final String API_PREFIX = "/api/users";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PATCH_NAME = "patchName";
    private static final String FAKE_TOKEN = "fakeToken";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
        tokenService.clear();
    }

    @Test
    public void WhenUserSignUpCorrectly_ThenShouldSucceed() throws Exception {
        var request = new SignUpRequest(NAME, EMAIL, PASSWORD);
        var response = signUp(request);

        assertTrue(userRepository.existsById(response.id));
        assertEquals(NAME, response.name);
        assertEquals(EMAIL, response.email);
    }

    @Test
    public void GivenOneUserSignedUp_WhenUserLoginCorrectly_ThenShouldRespondEmailAndTokenAndExpiryTime() throws Exception {
        var signUpResponse = signUp();

        var request = new LoginRequest(EMAIL, PASSWORD);
        var response = login(request);

        assertEquals(signUpResponse.id, response.id);
        assertEquals(EMAIL, response.email);
        assertNotNull(response.token);
        assertDoesNotThrow(() -> tokenService.validateToken(response.token));
        assertTrue(response.expiryTime > currentTimeMillis());
    }

    @Test
    public void GivenOneUserLoggedIn_WhenGetUserWithValidToken_ThenShouldRespondNameAndEmail() throws Exception {
        signUp();
        var loginResponse = login();
        int userId = loginResponse.id;
        String token = loginResponse.token;

        var response = getUser(userId, token);

        assertEquals(loginResponse.id, response.id);
        assertEquals(NAME, response.name);
        assertEquals(EMAIL, response.email);
    }

    @Test
    public void WhenGetUserWithInvalidToken_ThenShouldRespondForbidden() throws Exception {
        mockMvc.perform(withToken(FAKE_TOKEN, get(API_PREFIX + "/{userId}", signUp().id)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void GivenOneUserLoggedIn_WhenUpdateUserWithValidToken_ThenShouldRespondIdAndName() throws Exception {
        signUp();
        var loginResponse = login();
        int userId = loginResponse.id;
        String token = loginResponse.token;

        var response = updateUser(userId, token, PATCH_NAME);

        assertEquals(userId, response.id);
        assertEquals(PATCH_NAME, response.name);
    }

    private SignUpResponse signUp(SignUpRequest request) throws Exception {
        return getBody(mockMvc.perform(post(API_PREFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk()), SignUpResponse.class);
    }

    private SignUpResponse signUp() throws Exception {
        var request = new SignUpRequest(NAME, EMAIL, PASSWORD);
        return signUp(request);
    }

    private LoginResponse login(LoginRequest request) throws Exception {
        return getBody(mockMvc.perform(post(API_PREFIX + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk()), LoginResponse.class);
    }

    private LoginResponse login() throws Exception {
        var request = new LoginRequest(EMAIL, PASSWORD);
        return login(request);
    }

    private GetUserResponse getUser(int userId, String token) throws Exception {
        return getBody(mockMvc.perform(withToken(token, get(API_PREFIX + "/{userId}", userId)))
                .andExpect(status().isOk()), GetUserResponse.class);
    }

    private UpdateUserResponse updateUser(int userId, String token, String name) throws Exception {
        return getBody(mockMvc.perform(withToken(token, patch(API_PREFIX + "/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .content(toJson(new UpdateUserRequest(name))))
                .andExpect(status().isOk()), UpdateUserResponse.class);
    }

}
