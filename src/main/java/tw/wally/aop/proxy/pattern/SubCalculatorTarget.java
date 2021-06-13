package tw.wally.aop.proxy.pattern;

import java.util.Arrays;

/**
 * @author - wally55077@gmail.com
 */
public class SubCalculatorTarget extends CalculatorTarget {

    @Override
    public int plus(int a, int b) {
        var methodName = "plus";
        // 前置通知
        System.out.printf("Proxy pattern calculator before : %s , args : %s%n", methodName, Arrays.asList(a, b));
        int result = super.plus(a, b);
        // 返回通知
        System.out.printf("Proxy pattern calculator after : %s , return : [%s]%n", methodName, result);
        return result;
    }

    @Override
    public int minus(int a, int b) {
        var methodName = "minus";
        // 前置通知
        System.out.printf("Proxy pattern calculator before : %s , args : %s%n", methodName, Arrays.asList(a, b));
        var result = super.minus(a, b);
        // 返回通知
        System.out.printf("Proxy pattern calculator after : %s , return : [%s]%n", methodName, result);
        return result;
    }
}
