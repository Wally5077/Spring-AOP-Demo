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
@Component
@Order(4)
public class AdviceSequenceAspect extends BaseAspect {

    public AdviceSequenceAspect(ObjectMapper mapper) {
        super(mapper);
    }

//    @Pointcut("bean(demoController)")
//    public void adviceSequencePointCut() { }
//
//    @Before("adviceSequencePointCut()")
//    public void beforeAdvice(JoinPoint joinPoint) {
//        log.info("before");
//    }
//
//    @AfterReturning("adviceSequencePointCut()")
//    public void afterReturningAdvice(JoinPoint joinPoint) {
//            log.info("afterReturning");
//    }
//
//    @AfterThrowing("adviceSequencePointCut()")
//    public void afterThrowingAdvice(JoinPoint joinPoint) {
//        log.info("afterThrowing");
//    }
//
//    @After("adviceSequencePointCut()")
//    public void afterAdvice(JoinPoint joinPoint) {
//        log.info("after");
//    }
//
//    @Around("adviceSequencePointCut()")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
//        try {
//            log.info("around before");
//            var result = joinPoint.proceed(joinPoint.getArgs());
//            log.info("around after return");
//            return result;
//        } catch (Throwable t) {
//            log.info("around after throw");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t);
//        } finally {
//            log.info("around after");
//        }
//    }

}
