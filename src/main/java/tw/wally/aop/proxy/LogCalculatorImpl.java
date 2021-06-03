package tw.wally.aop.proxy;

import tw.wally.aop.proxy.pattern.Calculator;

import java.util.Arrays;

/**
 * @author - wally55077@gmail.com
 */
public class LogCalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        var methodName = "add";
        // 前置通知
        System.out.printf("Log calculator before : %s , args : %s%n", methodName, Arrays.asList(a, b));
        var result = a + b;
        // 返回通知
        System.out.printf("Log calculator after : %s , return : [%s]%n", methodName, result);
        return result;
    }
}
