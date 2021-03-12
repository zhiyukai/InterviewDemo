package prictise.com.application1.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import prictise.com.application1.utils.LogcatUtils;

public class CusActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
  private final String TAG = "CusActivityLifecycleCallbacks";
  @Override
  public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    LogcatUtils.showDLog(TAG, "onActivityCreated");
  }

  @Override
  public void onActivityStarted(Activity activity) {
    LogcatUtils.showDLog(TAG, "onActivityStarted");
  }

  @Override
  public void onActivityResumed(Activity activity) {
    LogcatUtils.showDLog(TAG, "onActivityResumed");
  }

  @Override
  public void onActivityPaused(Activity activity) {
    LogcatUtils.showDLog(TAG, "onActivityPaused");
  }

  @Override
  public void onActivityStopped(Activity activity) {
    LogcatUtils.showDLog(TAG, "onActivityStopped");
  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    LogcatUtils.showDLog(TAG, "onActivitySaveInstanceState");
  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    LogcatUtils.showDLog(TAG, "onActivityDestroyed");
  }
}
