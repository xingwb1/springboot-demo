package com.mygit.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * Description:
 *
 * @author: 邢武彪
 * Date: 2021-02-20
 * Time: 18:24
 */

/**
 * 切面表达式
 * 例如定义切入点表达式 execution (* com.sample.service.impl..*. *(..))
 * execution()是最常用的切点函数，其语法如下所示：
 * 整个表达式可以分为五个部分：
 * 1、execution(): 表达式主体。
 * 2、第一个*号：表示返回类型， *号表示所有的类型。
 * 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
 * 4、第二个*号：表示类名，*号表示所有的类。
 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
 * <p>
 * order    配置类的加载顺序
 *
 * @author 邢武彪
 */

@Component
@Aspect
@Order(1)
@Slf4j
public class LogAspect {
    /**
     * 中心名称(项目名), 模块名称
     */
    private final String PROJECT_NAME = "DATA_CENTER";
    /**
     * 模块名， execution(*  com.mygit.controller..*(..))  设置的生效的包，
     */
    private final String MODULE_NAME = "CONTROLLER";
    /**
     * 日志级别
     */
    private final String LOG_LEVEL = "INFO";


    /**
     * 切面生效范围: controller包下, 不包含other包
     */
    @Around("execution(*  com.mygit.controller..*(..))" +
            " && !execution(* com.mygit.controller.other..*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        log.info("@Around：执行目标方法之前...");

        // 请求的ip地址, URL
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        /**
         *     方法执行时 异常 message ,
         *     项目名称： 每个模块添加的切面， 设为常量
         *     模块名称： @Around 注解， 可设置切面生效的包  data-center/service
         */




        //方法参数
        Object[] args = point.getArgs();
        //        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
        //            args[0] = "改变后的参数1";
        //        }

        //开始调用时间 计时并调用目标函数,获得返回值
        long start = System.currentTimeMillis();
        // fixme 执行抛出的异常
        Exception exception = null;
        Object returnValue ;
        try {
            returnValue = point.proceed(args);
        } catch (Exception e) {
            exception = e;
            log.error(e.getMessage(), e);
            throw e;
        }
        //代理方法对象
        //类名
        String clazzName = point.getTarget().getClass().getName();
        //方法名
        String methodName = point.getSignature().getName();

        //消耗时间
        Long time = System.currentTimeMillis() - start;


        log.info("@Around：执行目标方法之后,项目名称 {}，模块名称 {},请求ip {},url {}, 类名 {}, 方法名 {}, 参数 {},返回值 {},产生异常 {},总耗时 {} ms",
                PROJECT_NAME,MODULE_NAME, ip, url, clazzName, methodName, Arrays.toString(args), returnValue, exception.getMessage(), time);

        // 实际方法对象,可以获取方法注解等
        Class<?> clazz = point.getTarget().getClass();
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());

        /**
         *  将审计日志存入ES, 省略
         */
        return returnValue;
    }

//    //前置通知
//    @Before("execution(*  com.mygit.controller..*(..))" +
//            " && !execution(* com.mygit.controller.other..*(..))")
//    public void beforeMethod(JoinPoint joinPoint) {
//        log.info("调用了前置通知");
//
//    }
//
//    //@After: 后置通知
//    @After("execution(*  com.mygit.controller..*(..))" +
//            " && !execution(* com.mygit.controller.other..*(..))")
//    public void afterMethod(JoinPoint joinPoint) {
//        log.info("调用了后置通知");
//    }
//
//    //@AfterRunning: 返回通知 result为返回内容
//    @AfterReturning(value = "execution(*  com.mygit.controller..*(..))" +
//            "&& !execution(* com.mygit.controller.other..*(..))", returning = "result")
//    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
//        log.info("调用了返回通知");
//    }
//
//    //@AfterThrowing: 异常通知
//    @AfterThrowing(value = "execution(*  com.mygit.controller..*(..))"
//            + "&& !execution(* com.mygit.controller.other..*(..))", throwing = "e")
//    public void afterReturningMethod(JoinPoint joinPoint, Exception e) {
//        log.info("调用了异常通知");
//    }


}

