package prictise.com.application1.dynamicProxy;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author zhisiyi
 * @Date 2019.12.02 20:56
 * @Comment
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface POST {
  String value() default "";
}
