package tw.wally.aop.proxy.pattern;

/**
 * @author - wally55077@gmail.com
 */
public class CalculatorTarget implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
