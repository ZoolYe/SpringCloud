package zool.helloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        //让处理线程等待几秒钟
        int sleepTime = new Random().nextInt(3000);
        logger.info("线程等待时间："+sleepTime);
        Thread.sleep(sleepTime);
        logger.info("/hello,    host:"+instance.getHost()+",    service_id:"+instance.getServiceId());
        return "Hello World";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(String username){
        logger.info(username);
        return username;
    }

    @RequestMapping(value = "/userObj",method = RequestMethod.POST)
    public String userObj(String name,int age){
        logger.info(name+age);
        return name+age;
    }

}
