package tw.wally.aop.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tw.wally.aop.exceptions.NotFoundException;
import tw.wally.aop.model.User;
import tw.wally.aop.model.request.GetUserRequest;
import tw.wally.aop.model.request.LoginRequest;
import tw.wally.aop.model.request.SignUpRequest;
import tw.wally.aop.model.request.UpdateUserRequest;
import tw.wally.aop.model.response.GetUserResponse;
import tw.wally.aop.model.response.LoginResponse;
import tw.wally.aop.model.response.SignUpResponse;
import tw.wally.aop.model.response.UpdateUserResponse;
import tw.wally.aop.repositories.UserRepository;
import tw.wally.aop.services.UserService;

import javax.transaction.Transactional;

/**
 * @author - wally55077@gmail.com
 */
@Service
@AllArgsConstructor
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        var user = userRepository.save(new User(request.name, request.email, request.password));
        return new SignUpResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new NotFoundException(User.class, request.email));
        return new LoginResponse(user.getId(), user.getEmail());
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        var user = userRepository.findById(request.Id)
                .orElseThrow(() -> new NotFoundException(User.class, request.Id));
        return new GetUserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        var user = userRepository.findById(request.id)
                .orElseThrow(() -> new NotFoundException(User.class, request.id));
        request.mayHaveName().ifPresent(user::setName);
        user = userRepository.save(user);
        return new UpdateUserResponse(user.getId(), user.getName());
    }
}
