package tw.wally.aop.proxy;

import tw.wally.aop.proxy.dynamic.cglib.CglibLogProxy;
import tw.wally.aop.proxy.dynamic.jdk.JDKLogProxy;
import tw.wally.aop.proxy.pattern.Calculator;
import tw.wally.aop.proxy.pattern.CalculatorProxy;
import tw.wally.aop.proxy.pattern.CalculatorTarget;

/**
 * @author - wally55077@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        int a = 1, b = 2;
        logCalculator(a, b);
        calculatorProxyPattern(a, b);
        calculatorJdkProxy(a, b);
        calculatorCglibProxy(a, b);
    }

    private static void logCalculator(int a, int b) {
        Calculator logCalculator = new LogCalculatorImpl();
        logCalculator.plus(a, b);
        System.out.println();
    }

    private static void calculatorProxyPattern(int a, int b) {
        Calculator target = new CalculatorTarget();
        Calculator calculatorProxy = new CalculatorProxy(target);
        calculatorProxy.plus(a, b);
        System.out.println();
    }

    private static void calculatorJdkProxy(int a, int b) {
        Calculator target = new CalculatorTarget();
        Calculator jdkProxy = JDKLogProxy.newInstance(target);
        jdkProxy.plus(a, b);
        System.out.println();
    }

    private static void calculatorCglibProxy(int a, int b) {
        Calculator target = new CalculatorTarget();
        Calculator cglibProxy = CglibLogProxy.newInstance(target);
        cglibProxy.plus(a, b);
        System.out.println();
    }

}