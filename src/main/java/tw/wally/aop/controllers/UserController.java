package tw.wally.aop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tw.wally.aop.model.request.GetUserRequest;
import tw.wally.aop.model.request.LoginRequest;
import tw.wally.aop.model.request.SignUpRequest;
import tw.wally.aop.model.request.UpdateUserRequest;
import tw.wally.aop.model.response.GetUserResponse;
import tw.wally.aop.model.response.LoginResponse;
import tw.wally.aop.model.response.SignUpResponse;
import tw.wally.aop.model.response.UpdateUserResponse;
import tw.wally.aop.services.TokenService;
import tw.wally.aop.services.UserService;

/**
 * @author - wally55077@gmail.com
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping
    public SignUpResponse signUp(@RequestBody SignUpRequest request) {
        return userService.signUp(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        var response = userService.login(request);
        var token = tokenService.createToken(response.id);
        response.setToken(token.getToken());
        response.setExpiryTime(token.getExpiryTime());
        return response;
    }

    @GetMapping("/{userId}")
    public GetUserResponse getUser(@RequestHeader("Authorization") String token,
                                   @PathVariable int userId) {
        tokenService.validateToken(token);
        return userService.getUser(new GetUserRequest(userId));
    }

    @PatchMapping("/{userId}")
    public UpdateUserResponse updateUser(@RequestHeader("Authorization") String token,
                                         @PathVariable int userId,
                                         @RequestBody UpdateUserRequest request) {
        tokenService.validateToken(token);
        request.setId(userId);
        return userService.updateUser(request);
    }
}
