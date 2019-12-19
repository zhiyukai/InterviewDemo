package prictise.com.application1.dynamicProxy;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author zhisiyi
 * @Date 2019.12.02 20:57
 * @Comment
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface BODY {

}
