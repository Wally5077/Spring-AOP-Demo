package tw.wally.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author - wally55077@gmail.com
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class LogAspect extends BaseAspect {

    public LogAspect(ObjectMapper mapper) {
        super(mapper);
    }

//    @Pointcut()
//    public void logPointcut() {
//    }
//
//    @Before("logPointcut()")
//    public void before(JoinPoint joinPoint) {
//        var signature = joinPoint.getSignature();
//        var args = joinPoint.getArgs();
//        log.info("before {}, args: {} ", signature.getName(), toJson(args));
//    }
//
//    @AfterReturning(value = "logPointcut()", returning = "ret")
//    public void afterReturning(JoinPoint joinPoint, Object ret) {
//        var signature = joinPoint.getSignature();
//        log.info("after {}, args: {} ", signature.getName(), toJson(ret));
//    }

}
