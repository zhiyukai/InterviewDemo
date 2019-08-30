package prictise.com.application1.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.11.8 17:38
 * @comment
 */
public class DeleteLogAlarmReceiver extends BroadcastReceiver {

  private final String TAG = DeleteLogAlarmReceiver.class.getSimpleName();

  @Override
  public void onReceive(Context context, Intent intent) {
//        AlarmManager alarmManager = AlarmManagerUtil.getAlarmManager(MainApplication.getApplicationInstance());
//        // 重复定时任务
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20 * 1000, pendingIntent);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20 * 1000, pendingIntent);
//        }
    LogcatUtils.showELog(TAG, "删除日志文件");

//        if ("android.com.delete.log.file".equals(intent.getAction())) {
//            LogcatUtils.showELog(TAG, "删除日志文件");
////            Util.deleteAllFiles(Constants.LOG_DIR_PATH);
////            Util.deleteAllFiles(Constants.CRASH_DIR_PATH);
//        }
  }
}
