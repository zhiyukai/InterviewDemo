package prictise.com.application1.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import prictise.com.application1.MainApplication;

/**
 * @Author zsj
 * @Date 2018-11-08 23:18
 * @Commit
 */
public class AlarmManagerUtil {

  // 获取AlarmManager实例
  public static AlarmManager getAlarmManager(Context context) {
    return (AlarmManager) context
        .getSystemService(MainApplication.getApplicationInstance().ALARM_SERVICE);
  }

  // 发送定时广播（执行广播中的定时任务）
  // 参数：
  // context:上下文
  // requestCode:请求码，用于区分不同的任务
  // type:alarm启动类型
  // triggerAtTime:定时任务开启的时间，毫秒为单位
  // cls:广播接收器的class
  public static void sendAlarmBroadcast(Context context, int requestCode,
      int type, long triggerAtTime, Class cls) {
    AlarmManager mgr = getAlarmManager(context);

    Intent intent = new Intent(context, cls);
    PendingIntent pi = PendingIntent.getBroadcast(context, requestCode,
        intent, 0);

    mgr.set(type, triggerAtTime, pi);
  }

  // 取消指定requestCode的定时任务
  // 参数：
  // context:上下文
  // requestCode:请求码，用于区分不同的任务
  // cls:广播接收器的class
  public static void cancelAlarmBroadcast(Context context, int requestCode,
      Class cls) {
    AlarmManager mgr = getAlarmManager(context);

    Intent intent = new Intent(context, cls);
    PendingIntent pi = PendingIntent.getBroadcast(context, requestCode,
        intent, 0);

    mgr.cancel(pi);
    ToastUtil
        .showShort("取消定时服务成功" + " @requestCode:" + requestCode);
    Log.d("取消定时服务成功", "@requestCode:" + requestCode);
  }

  // 周期性执行定时任务
  // 参数：
  // context:上下文
  // requestCode:请求码，用于区分不同的任务
  // type:alarm启动类型
  // startTime:开始的时间，毫秒为单位
  // cycleTime:定时任务的重复周期，毫秒为单位
  // cls:广播接收器的class
  public static void sendRepeatAlarmBroadcast(Context context,
      int requestCode, int type, long startTime, long cycleTime, Class cls) {
    AlarmManager mgr = getAlarmManager(context);

    Intent intent = new Intent(context, cls);
    PendingIntent pi = PendingIntent.getBroadcast(context, requestCode,
        intent, 0);

    // pendingIntent 为发送广播
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      mgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, startTime, pi);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      mgr.setExact(AlarmManager.RTC_WAKEUP, startTime, pi);
    } else {
      mgr.setRepeating(AlarmManager.RTC_WAKEUP, startTime, cycleTime, pi);
    }

//        mgr.setRepeating(type, startTime, cycleTime, pi);
  }

}
