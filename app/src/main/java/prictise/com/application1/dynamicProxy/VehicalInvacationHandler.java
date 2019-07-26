package prictise.com.application1.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import prictise.com.application1.utils.LogcatUtils;

public class VehicalInvacationHandler implements InvocationHandler {

  private final String TAG = VehicalInvacationHandler.class.getSimpleName();

  private final IVehical vehical;

  public VehicalInvacationHandler(IVehical vehical) {
    this.vehical = vehical;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    LogcatUtils.showDLog(TAG, "---------before-------");
    LogcatUtils.showDLog(TAG, "proxy = " + proxy.getClass().getCanonicalName());
    LogcatUtils.showDLog(TAG, "method = " + method.getName());
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        LogcatUtils.showDLog(TAG, "args[" + i + "] = " + args[i].getClass().getName());
      }
    } else {
      LogcatUtils.showDLog(TAG, "args == null");
    }
    Object invoke = method.invoke(vehical, args);
    LogcatUtils.showDLog(TAG, "---------after-------");

    return invoke;
  }
}
