package com.qfedu.realm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.vo.JsonBean;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyPermFitler extends PermissionsAuthorizationFilter {

    // 权限验证不通过，在该方法中确定进一步的操作
    // ajax请求，需要返回json格式数据
    // 非ajax请求，跳转到指定的资源
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        // 如果是ajax方式，在请求头中会自动增加X-Requested-With 的key-value数据
        String header = req.getHeader("X-Requested-With");

        if(header != null && !header.equals("") && header.equals("XMLHttpRequest")){
            JsonBean bean = new JsonBean(0, "没有访问权限");
            // 手动转为json格式字符串，返回浏览器端
            ObjectMapper mapper = new ObjectMapper();
            String jsonInfo = mapper.writeValueAsString(bean);
            response.getWriter().write(jsonInfo);
        }else{
            // 非ajax方式，重定向到指定的资源
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
        }

        // 验证不通过
        return false;
    }
}
