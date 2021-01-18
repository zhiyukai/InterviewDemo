package prictise.com.application1.hook;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import prictise.com.application1.utils.LogcatUtils;

public class HookUtil {

  private Context context;
  private String TAG = HookUtil.class.getSimpleName();

  public void hookStartActivity(Context context) {
    this.context = context;
    try {
      /**
       * 这里注意一下，用我们分析的源码运行不了，所以稍微改了一下，
       * 思路什么都一样，只是源码的属性名做了修改
       */
//            Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
      Class<?> activityManagerClass = Class.forName("android.app.ActivityManagerNative");
      //拿到 IActivityManagerSingleton 属性
//            Field field = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
      Field field = activityManagerClass.getDeclaredField("gDefault");
      field.setAccessible(true);
      //获取到是 Singleton 对象，也就是 field 对应的类型
      Object singletonObj = field.get(null);

      //然后获取 Singletone 的 mInstance 属性
      Class<?> singtonClass = Class.forName("android.util.Singleton");
      Field mInstanceField = singtonClass.getDeclaredField("mInstance");
      mInstanceField.setAccessible(true);
      //真正的 hook 点
      Object iActivityManagerObj = mInstanceField.get(singletonObj);

      //hook 第二步，动态代理
      Class<?> iActivityManagerIntercept = Class.forName("android.app.IActivityManager");
      StartActivityHandler startActivityHandler = new StartActivityHandler(iActivityManagerObj);
      Object proxyIActivityManager = Proxy.newProxyInstance(getClass().getClassLoader(),
          new Class[]{iActivityManagerIntercept}, startActivityHandler);
      //在这我们将系统的对象更换成我们生成的动态代理对象，为了是调用动态代理的 invoke 方法，不更换不执行
      mInstanceField.set(singletonObj, proxyIActivityManager);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  class StartActivityHandler implements InvocationHandler {

    //系统真正的对象
    private Object trueIActivityManager;

    public StartActivityHandler(Object trueIActivityManager) {
      this.trueIActivityManager = trueIActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("startActivity".equals(method.getName())) {
        LogcatUtils
            .showDLog(TAG, "abc : --------------------- startActivity ---------------------");
        Intent intent = null;
        int index = -1;
        for (int i = 0; i < args.length; i++) {
          Object obj = args[i];
          if (obj instanceof Intent) {
            //找到 startActivity 传递进来的 Intent
            intent = (Intent) obj;
            index = i;
          }
        }
        //瞒天过海，获取想要跳转的意图，进行篡改
        Intent newIntent = new Intent(context, ProxyActivity.class);
        //我们将真实的意图封装在假意图当中
        newIntent.putExtra("oldIntent", intent);
        args[index] = newIntent;
      }
      return method.invoke(trueIActivityManager, args);
    }
  }

}
