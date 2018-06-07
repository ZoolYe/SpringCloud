package zool.ribbonconsumer.controller.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zool.ribbonconsumer.service.HelloService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @RequestMapping(value = "/ribbon-user",method = RequestMethod.GET)
    public String userConsumer(){
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/user?username={1}",String.class,"zool");

        Map<String,String> params = new HashMap<>();
        params.put("name","zoolye");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/user?username={name}",String.class,params);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/ribbon-user2",method = RequestMethod.GET)
    public String userConsumer2(){
        return restTemplate.getForObject("http://HELLO-SERVICE/user?username={1}",String.class,"http://www.zoolye.com");
    }

    @RequestMapping(value = "/ribbon-user3",method = RequestMethod.GET)
    public String userConsumer3(){
        User user = new User("zool",23);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://HELLO-SERVICE/userObj",user,String.class);
        //String body = responseEntity.getBody();
        return restTemplate.postForObject("http://HELLO-SERVICE/userObj",user,String.class);
    }

    @RequestMapping(value = "/ribbon-consumer-hello",method = RequestMethod.GET)
    public String helloConsumer2(){
        return helloService.helloService();
    }

}
