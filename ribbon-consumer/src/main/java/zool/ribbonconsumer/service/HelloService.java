package zool.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HelloService {

    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        long start = System.currentTimeMillis();
        restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("运行时间："+(end-start));
        return "运行时间："+(end-start);
    }


    @HystrixCommand(fallbackMethod = "defalutFallback")
    public String helloFallback() throws InterruptedException {
        Thread.sleep(3000);
        return "error";
    }

    public String defalutFallback(){
        log.info("error defalut");
        return "error defalut";
    }

}
