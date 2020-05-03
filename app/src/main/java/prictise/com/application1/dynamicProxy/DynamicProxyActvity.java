package prictise.com.application1.dynamicProxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.lang.reflect.Proxy;
import prictise.com.application1.BaseActivity;

/**
 * @Author zhisiyi
 * @Date 2019.12.02 20:51
 * @Comment
 */
public class DynamicProxyActvity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Car c = new Car();
    IVehical iVehical = (IVehical) Proxy
        .newProxyInstance(IVehical.class.getClassLoader(), new Class[]{IVehical.class},
            new VehicalInvacationHandler(c));
    iVehical.run("sss",3);
  }
}
