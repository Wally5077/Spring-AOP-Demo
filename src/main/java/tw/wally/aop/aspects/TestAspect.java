package tw.wally.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author - wally55077@gmail.com
 */
@Slf4j
@Aspect
@Component
@Order(100)
public class TestAspect extends BaseAspect {

    public TestAspect(ObjectMapper mapper) {
        super(mapper);
    }

//    @Before("within(tw.wally.aop.controllers.DemoController)")
//    public void withinAdvice(JoinPoint joinPoint) {
//        log.info("within Advice");
//    }
//
//    @Before("bean(DemoController)")
//    public void beanAdvice(JoinPoint joinPoint) {
//        log.info("bean Advice");
//    }
//
//    @Before("this(tw.wally.aop.controllers.DemoController)")
//    public void thisAdvice(JoinPoint joinPoint) {
//        log.info("this Advice");
//    }
//
//    @Before("target(tw.wally.aop.controllers.DemoController)")
//    public void targetAdvice(JoinPoint joinPoint) {
//        log.info("target Advice");
//    }

}
