package com.csxkaka.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul不仅具有路由的功能，还具有过滤器的功能
 * 其实zuul的核心就是一系列的filters
 *
 * 如果想禁用某个过滤器，需要在yml中配置
 */
@Component
public class MyFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);
    /**
     * 过滤器的类型：
     *   pre 路由之前
     *   routing 路由之时
     *   post 路由之后
     *   error 发送错误的调用时
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义多个filter时，filterType相同的会组成Pipeline，这时候filterOrder就可以发挥作用了，
     * 值越小就会越早执行，
     * 所以在定义过滤器的时候，相同类型的过滤器，最好都给一个具体的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要过滤，true代表永远过滤
     * 默认为false
     * 这里还可以写逻辑判断，判断完后再选择使用true还是false
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 这里写过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        // 获取当前请求的上下文，每一个线程，都有一个threadLocal保存context
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // String.format(format, str)  第1个参数是格式标识，第2个或第n个是被格式的字符串
        logger.info(String.format("%s>>>%s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        // 请求参数名不是token，则进入
        if (accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty!");
            } catch (Exception e) {}
            return null;
        }
        logger.info("ok");
        return null;
    }
}
