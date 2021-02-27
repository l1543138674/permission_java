package com.acsk.demo.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义权限验证注解
 *
 * @interface:声明一个注解
 * @Target:用在哪个上面，ElementType.METHOD：用在方法上
 * @Retention:注解的生命周期，RetentionPolicy.RUNTIME：运行阶段
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    //是否有全部权限
    boolean isAllMatch() default false;

    //权限值
    PermissionEnum[] value() default {};

    //权限代码
    PermissionEnum[] code() default {};

    //权限分组
    PermissionGroupEnum[] group() default {};

}
