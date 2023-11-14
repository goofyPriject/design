package cn.aireco.platform.door;

import cn.aireco.platform.door.annotation.DoDoor;
import cn.aireco.platform.door.config.StarterAutoConfigure;
import cn.aireco.platform.door.config.StarterService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

@Aspect
@Component
public class DoJoinPoint {


    @Autowired
    private StarterAutoConfigure starter;

    @Pointcut("@annotation(cn.aireco.platform.door.annotation.DoDoor)")
    public void aopPoint() {

    }

    @Around("aopPoint()")
    public Object doJob(ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        DoDoor door = method.getAnnotation(DoDoor.class);
        //获取字段值
         String keyValue = getFiledValue(door.key(), jp.getArgs());
        if (null == keyValue || "".equals(keyValue)) return jp.proceed();
        StarterService starterService = starter.starterService();
        //配置内容
        String[] split = starterService.split(",");
        //⽩名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return jp.proceed();
            }
        }
        //拦截
        return returnObject(door, method);
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return getClass(jp).getMethod(methodSignature.getName(),
                methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp) throws
            NoSuchMethodException {
        return jp.getTarget().getClass();
    }

    //返回对象
    private Object returnObject(DoDoor doGate, Method method) throws
            IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String name = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
        Object defaultValue = method.getDefaultValue();
        String returnJson = doGate.returnJson();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    //获取属性值
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    Class<?> classBook = Class.forName(filed);
                    Object objectBook = classBook.newInstance();
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }


}
