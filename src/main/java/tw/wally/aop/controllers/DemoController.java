package tw.wally.aop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.wally.aop.annotations.Token;

import static java.lang.String.format;

/**
 * @author - wally55077@gmail.com
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/demos")
public class DemoController {

    @GetMapping("/{demoId}")
    public ResponseEntity<?> getDemo(@PathVariable String demoId) {
        if ("nonexistentDemoId".equals(demoId)) {
            throw new IllegalArgumentException(format("nonexistentDemoId %s", demoId));
        }
        return ResponseEntity.ok(format("Demo %s", demoId));
    }

}
