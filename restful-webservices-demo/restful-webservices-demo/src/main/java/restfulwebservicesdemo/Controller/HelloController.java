package restfulwebservicesdemo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import restfulwebservicesdemo.Domain.HelloBean;

@RestController
public class HelloController {

    // get endpoint
    @GetMapping("/hello")
    public ResponseEntity<String> getHelloMessage(){
        return ResponseEntity.ok("Welcome to Restful service");
    }

    // return bean type
    @GetMapping("/helloBean")
    public HelloBean getHelloBean(){
        return new HelloBean("hello bean");
    }

    //path parameters
    @GetMapping("/hello/{name}")
    public ResponseEntity<HelloBean> getPathParametersValue(@PathVariable("name") String testName){
        return ResponseEntity.ok(new HelloBean(String.format("hi ,%s",testName)));
    }
}
