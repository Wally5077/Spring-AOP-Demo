package tw.wally.aop.proxy.pattern;

/**
 * @author - wally55077@gmail.com
 */
public class CalculatorTarget implements Calculator {
    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
