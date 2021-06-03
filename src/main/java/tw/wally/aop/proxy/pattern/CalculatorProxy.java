package tw.wally.aop.proxy.pattern;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author - wally55077@gmail.com
 */
@AllArgsConstructor
public class CalculatorProxy implements Calculator {

    private final Calculator calculator;

    @Override
    public int add(int a, int b) {
        var methodName = "add";
        // 前置通知
        System.out.printf("Proxy pattern calculator before : %s , args : %s%n", methodName, Arrays.asList(a, b));
        var result = calculator.add(a, b);
        // 返回通知
        System.out.printf("Proxy pattern calculator after : %s , return : [%s]%n", methodName, result);
        return result;
    }
}
