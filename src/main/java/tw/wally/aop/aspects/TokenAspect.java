package tw.wally.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tw.wally.aop.services.TokenService;

/**
 * @author - wally55077@gmail.com
 */
@Slf4j
@Aspect
@Order(2)
@Component
public class TokenAspect extends BaseAspect {

    private final TokenService tokenService;

    public TokenAspect(ObjectMapper mapper, TokenService tokenService) {
        super(mapper);
        this.tokenService = tokenService;
    }

//    @Pointcut()
//    public void createTokenPointcut() {
//    }
//
//    @Pointcut()
//    public void tokenValidationPointcut() {
//    }
//
//    @AfterReturning(value = "createTokenPointcut()", returning = "ret")
//    public void afterReturning(Object ret) {
//        var response = (LoginResponse) ret;
//        var userId = response.id;
//        var token = tokenService.createToken(userId);
//        response.setToken(token.getToken());
//        response.setExpiryTime(token.getExpiryTime());
//        log.info("User: {}, token: {}", userId, toJson(token));
//    }
//
//    @Before("tokenValidationPointcut()")
//    public void before(JoinPoint joinPoint) {
//        var args = joinPoint.getArgs();
//        var token = args[0].toString();
//        tokenService.validateToken(token);
//        log.info("validate token: {}", token);
//    }

}
