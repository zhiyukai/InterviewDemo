package prictise.com.application1.zhujie;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class ZhuJie {

  public void method() {
    System.out.println("");
  }

  private final static int SUNDAY = 1;
  private final static int SATURDAY = 2;

  @IntDef({SATURDAY, SUNDAY})
  @Retention(RetentionPolicy.SOURCE)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  @interface Weak {
    String value() default "ss";
  }

}
