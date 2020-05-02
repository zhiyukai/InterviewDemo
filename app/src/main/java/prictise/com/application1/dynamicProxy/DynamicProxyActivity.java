package prictise.com.application1.dynamicProxy;

import android.os.Bundle;
import java.lang.reflect.Proxy;
import prictise.com.application1.BaseActivity;

/**
 * @Author zhisiyi
 * @Date 2019.12.01 21:20
 * @Comment
 */
public class DynamicProxyActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    IVehical c = new Car();
    IVehical iVehical = (IVehical) Proxy.newProxyInstance(IVehical.class.getClassLoader(), new Class[]{IVehical.class},
        new VehicalInvacationHandler(c));
    iVehical.run("s");
  }
}
