package zool.feignconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zool.feignconsumer.domain.dto.User;
import zool.feignconsumer.service.HelloService;
import zool.feignconsumer.service.RefactorHelloService;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("zool")).append("\n");
        sb.append(helloService.hello("zoolye",23)).append("\n");
        sb.append(helloService.hello(new User("zoolye",23))).append("\n");
        return sb.toString();
    }

    @RequestMapping(value = "/feign-consumer3",method = RequestMethod.GET)
    public String helloConsumer3(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("zool")).append("\n");
        sb.append(helloService.hello("zoolye",23)).append("\n");
        sb.append(helloService.hello(new User("zoolye",23))).append("\n");
        return sb.toString();
    }

}
