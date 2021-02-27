package com.acsk.demo.interceptor;

import com.acsk.demo.permission.Authorization;
import com.acsk.demo.permission.PermissionEnum;
import com.acsk.demo.permission.PermissionGroupEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限拦截器
 */
public class PermissionInterceptor implements HandlerInterceptor {

    /**
     * 在DispatcherServlet之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //用instanceof来判断一个对象是否为一个类的实例
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //HandlerMethod封装了很多属性，在访问请求方法的时候可以方便的访问到方法、方法参数、方法上的注解、所属类等并且对方法参数封装处理，也可以方便的访问到方法参数的注解等信息。
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //获取该方法的权限注解
        Authorization authorization = method.getAnnotation(Authorization.class);

        //(1)无权限注解时，放行
        if (null == authorization) {
            return true;
        }
        //获取是否有全部权限,权限值和权限代码，权限分组
        boolean isAllMatch = authorization.isAllMatch();
        PermissionEnum[] value = authorization.value();
        PermissionEnum[] code = authorization.code();
        PermissionGroupEnum[] group = authorization.group();

        //(2)无权限设置时，放行
        Set permissionSet = new HashSet();
        permissionSet.addAll(Arrays.asList(value));//将数组转为List集合
        permissionSet.addAll(Arrays.asList(code));
        for (PermissionGroupEnum groupEnum : group) {//遍历分组并根据分组获取分组下全部权限
            List<PermissionEnum> list = PermissionEnum.getPermissionsByGroup(groupEnum);
            permissionSet.addAll(list);
        }
        if (permissionSet.size() == 0) {
            return true;
        }

        //(3)判断拥有的权限
        PermissionEnum[] permissionEnums = (PermissionEnum[]) permissionSet.toArray(new PermissionEnum[0]);//转化为数组
        boolean flag = PermissionEnum.hasPower(isAllMatch, permissionEnums);
        if (flag) {
            return true;
        }


        //(4)无该权限，拦截提醒
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<p style='color:red'>对不起，您没有该操作权限</p>");

        System.out.println("*****************************");
        System.out.println("preHandle");
        System.out.println("*****************************");
        return false;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("*****************************");
        System.out.println("postHandle");
        System.out.println("*****************************");

    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("*****************************");
        System.out.println("afterCompletion");
        System.out.println("*****************************");
    }
}
