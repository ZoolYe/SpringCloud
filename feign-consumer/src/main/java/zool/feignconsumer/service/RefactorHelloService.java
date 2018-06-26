package zool.feignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import zool.helloserviceapi.service.HelloService;

@FeignClient(value = "HELLO-SERVICE")//绑定服务
public interface RefactorHelloService extends HelloService {

}
