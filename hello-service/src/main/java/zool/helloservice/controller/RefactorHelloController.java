package zool.helloservice.controller;

import org.springframework.web.bind.annotation.RestController;
import zool.helloserviceapi.domain.dto.User;
import zool.helloserviceapi.service.HelloService;

@RestController
public class RefactorHelloController implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello "+name;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User(name,age);
    }

    @Override
    public String hello(User user) {
        return "Hello "+user.getName()+","+user.getAge();
    }

}
