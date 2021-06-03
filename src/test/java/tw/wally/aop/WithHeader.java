package tw.wally.aop;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * @author - wally55077@gmail.com
 */
@FunctionalInterface
public interface WithHeader {

    static WithHeader empty() {
        return request -> {

        };
    }

    void decorate(MockHttpServletRequestBuilder request);

}

