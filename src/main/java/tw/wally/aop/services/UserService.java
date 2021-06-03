package tw.wally.aop.services;

import tw.wally.aop.model.request.GetUserRequest;
import tw.wally.aop.model.request.LoginRequest;
import tw.wally.aop.model.request.SignUpRequest;
import tw.wally.aop.model.request.UpdateUserRequest;
import tw.wally.aop.model.response.GetUserResponse;
import tw.wally.aop.model.response.LoginResponse;
import tw.wally.aop.model.response.SignUpResponse;
import tw.wally.aop.model.response.UpdateUserResponse;

/**
 * @author - wally55077@gmail.com
 */
public interface UserService {

    SignUpResponse signUp(SignUpRequest request);

    LoginResponse login(LoginRequest request);

    GetUserResponse getUser(GetUserRequest request);

    UpdateUserResponse updateUser(UpdateUserRequest request);
}
