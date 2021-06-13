package tw.wally.aop.proxy.dynamic.cglib;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author - wally55077@gmail.com
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CglibLogProxy implements MethodInterceptor {

    private final Object target;

    /**
     * @param t   目標物件
     * @param <T> 目標型態
     * @return 代理物件
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(T t) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(new CglibLogProxy(t));
        return (T) (enhancer.create());
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable, IllegalAccessException {
        // 裡面的邏輯就是通知 (Advice)
        // 環繞通知開始
        try {
            // 前置通知
            String methodName = method.getName();
            System.out.printf("Cglib proxy before : %s , args : %s%n", methodName, Arrays.toString(objects));
            var result = method.invoke(target, objects);
            // 返回通知
            System.out.printf("Cglib proxy after : %s , return : [%s]%n", methodName, result);
            return result;
        } catch (Throwable t) {
            // 例外通知
            throw t;
        } finally {
            // 後置通知
        }
        // 環繞通知結束
    }
}
