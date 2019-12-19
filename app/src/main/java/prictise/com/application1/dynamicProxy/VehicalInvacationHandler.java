package prictise.com.application1.dynamicProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import prictise.com.application1.utils.LogcatUtils;

public class VehicalInvacationHandler implements InvocationHandler {

  private final String TAG = VehicalInvacationHandler.class.getSimpleName();

  private final IVehical vehical;

  public VehicalInvacationHandler(IVehical vehical) {
    this.vehical = vehical;
  }

  @Override
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
    Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
    int len = parameterAnnotationsArray.length;
    for (int i = 0; i < len; i++) {
      LogcatUtils.showDLog(TAG, "第一重 i = " + i);
      Annotation[] annotations = parameterAnnotationsArray[i];

      int size = annotations.length;
      for (int j = 0; j < size; j++) {
        LogcatUtils.showDLog(TAG, "第二重 j = " + j);
        LogcatUtils.showDLog(TAG, "第二重 annotations[" + j + "] = " + annotations[j].toString());
      }
    }

    Annotation[] annotations = method.getAnnotations();
    int aL = annotations.length;
    for (int i = 0; i < aL; i++) {
      LogcatUtils.showDLog(TAG, "annotations[" + i + "] type = " + annotations[i].annotationType());
      LogcatUtils.showDLog(TAG,
          "annotations[" + i + "] className = " + annotations[i].getClass().getSimpleName());
    }

    Type[] type = method.getGenericParameterTypes();

    int typeLen = type.length;
    for (int i = 0; i < typeLen; i++) {
      LogcatUtils.showDLog(TAG, "type[" + i + "] name = " + type[i].toString());
    }

    return invoke;
  }
}
