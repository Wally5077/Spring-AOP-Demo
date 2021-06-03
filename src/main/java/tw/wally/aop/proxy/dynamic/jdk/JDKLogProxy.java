package tw.wally.aop.proxy.dynamic.jdk;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author - wally55077@gmail.com
 */
@AllArgsConstructor
public class JDKLogProxy implements InvocationHandler {

    private final Object target;

    /**
     * @param t   目標物件
     * @param <T> 目標型態
     * @return 代理物件
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(T t) {
        var clazz = t.getClass();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(), new JDKLogProxy(t));
    }

    /**
     * @param proxy  代理物件
     * @param method 目標物件執行方法
     * @param args   方法參數
     * @return 方法執行結果
     * @throws Throwable 方法例外
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 裡面的邏輯就是通知 (Advice)
        // 環繞通知開始
        try {
            // 前置通知
            String methodName = method.getName();
            System.out.printf("Jdk proxy before : %s , args : %s%n", methodName, Arrays.toString(args));
            var result = method.invoke(target, args);
            // 返回通知
            System.out.printf("Jdk proxy after : %s , return : [%s]%n", methodName, result);
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
