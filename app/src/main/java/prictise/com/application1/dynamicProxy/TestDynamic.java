package prictise.com.application1.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @Author zhisiyi
 * @Date 2019.12.02 20:47
 * @Comment
 */
public class TestDynamic {

  public static void main(String[] args) {
    Car c = new Car();
    IVehical iVehical = (IVehical) Proxy
        .newProxyInstance(IVehical.class.getClassLoader(), new Class[]{IVehical.class},
            new VehicalInvacationHandler(c));
    iVehical.run("sss");
  }
}
