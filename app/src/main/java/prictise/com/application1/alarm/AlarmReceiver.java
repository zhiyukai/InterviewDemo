package prictise.com.application1.alarm;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import prictise.com.application1.MainApplication;
import prictise.com.application1.utils.DateTimeUtil;
import prictise.com.application1.utils.ToastUtil;

/**
 * @Author zsj
 * @Date 2018-11-08 23:25
 * @Commit
 */
public class AlarmReceiver extends BroadcastReceiver {

  private final String TAG = AlarmReceiver.class.getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
    ToastUtil.showShort(context,
        "从服务启动广播：at " + DateTimeUtil.getCurrentDateTimeString());
    Log.d(TAG, "从服务启动广播：at " + DateTimeUtil.getCurrentDateTimeString());
//    AlarmManager alarmManager = (AlarmManager) MainApplication.getApplicationInstance()
//        .getSystemService(ALARM_SERVICE);
//    PendingIntent pendingIntent = PendingIntent
//        .getBroadcast(MainApplication.getApplicationInstance(), 0, intent, 0);
//    alarmManager
//        .set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000,
//            pendingIntent);
  }
}
