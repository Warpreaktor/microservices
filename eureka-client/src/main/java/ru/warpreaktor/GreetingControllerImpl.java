package ru.warpreaktor;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingControllerImpl implements GreetingController {
    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

//    @Value("${userValue}")
//    private String username;

    @Override
    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @GetMapping("/abc")
    public void test() {
        System.out.println("_ABC_");
    }

    @GetMapping("/abcr")
    public String abcr() {
        throw new RuntimeException();
    }

    @GetMapping("/parametrized/{id}")
    public String parametrized(@PathVariable(value = "id") String id) {
        return "echo: " + id;
    }
}
