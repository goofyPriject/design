package cn.aireco.platform.door.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoDoor {

    String key() default "";

    String returnJson() default "{\"errorInfo\":\"拦截\"}";

}
