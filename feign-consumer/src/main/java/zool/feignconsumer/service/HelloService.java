package zool.feignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import zool.feignconsumer.domain.dto.User;

@FeignClient(value = "HELLO-SERVICE",fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name,@RequestHeader("age") int age);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);

}
