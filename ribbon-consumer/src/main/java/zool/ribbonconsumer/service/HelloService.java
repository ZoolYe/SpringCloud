package zool.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
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
    @CacheResult
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

    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
    public String getUserById(){
        throw new RuntimeException("getUserById command failed");
    }


    public String getUserByIdFallback(Throwable throwable){
        assert "getUserById command failed".equals(throwable.getMessage());
        return throwable.getMessage();
    }

    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "100")})
    public void find(){

    }

    @HystrixCommand
    public void findAll(){

    }

}
