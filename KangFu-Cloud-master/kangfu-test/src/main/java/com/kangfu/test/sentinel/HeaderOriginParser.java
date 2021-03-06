package com.kangfu.test.sentinel;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.kangfu.common.core.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求解析器
 * 授权规则
 * Sentinel通过RequestOriginParser这个接口的parseOrigin方法来获取HttpServletRequest的来源。
 * 定义：放行网关传递的request，拦截非网关传递的request，通过请求头中的origin的值进行判断。
 *
 * @author kangfu
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {
    /**
     * 请求解析
     * 从request对象中获取origin，获取方式自定义
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 1.获取请求头
        String origin = request.getHeader("origin");
        // 2.非空判断
        if (StringUtils.isEmpty(origin)){
            origin = request.getParameter("origin");
        }
        if (StringUtils.isEmpty(origin)){
            origin = request.getRemoteAddr();
        }
        //System.out.println("test RequestOriginParser origin = " + origin);
        return origin;

    }
}
