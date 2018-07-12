package zool.apigateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AccessFilter extends ZuulFilter {


    /**
     * 过滤器的类型， 它决定过滤器在请求的哪个生命周期中执行。 这里
     * 定义为pre, 代表会在请求被路由之前执行。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。 当请求在一个阶段中存在多个过滤器时， 需
     * 要根据该方法返回的值来依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。 这里我们直接返回了true, 因
     * 此该过滤器对所有请求都会生效。 实际运用中我们可以利用该函数来指定过滤器的
     * 有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null){
            log.warn("access token is empty");
            //令zuul过滤该请求， 不对其进行路由
            ctx.setSendZuulResponse(false);
            //设置了其返回的错误码
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}