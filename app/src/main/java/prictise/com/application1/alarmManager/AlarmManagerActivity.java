package prictise.com.application1.alarmManager;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Calendar;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.11.8 17:24
 * @comment
 */
public class AlarmManagerActivity extends Activity {

  private final String TAG = AlarmManagerActivity.class.getSimpleName();

  @BindView(R.id.bt_start_alarm)
  Button btStartAlarm;
  @BindView(R.id.bt_stop_alarm)
  Button btStopAlarm;

  private AlarmManager mAlarmManager;

  private final String ACTION = "test_action";
  String SPLIT = ": ";

  private ArrayList<TimeFrame> mDataTimeFrame = new ArrayList<>();

  int INTERVAL = 1000 * 60 * 60 * 24;

  String HOSTESS_TIME_START_ACTION = "android.time.frame.start.action";
  String HOSTESS_TIME_END_ACTION = "android.time.frame.end.action";

  Intent mAlarmStartTimeIntent = new Intent(HOSTESS_TIME_START_ACTION);
  Intent mAlarmEndTimeIntent = new Intent(HOSTESS_TIME_END_ACTION);

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alarm_manager);
    ButterKnife.bind(this);
    mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    IntentFilter startFilter = new IntentFilter();
    startFilter.addAction(HOSTESS_TIME_START_ACTION);
    registerReceiver(t, startFilter);
    IntentFilter endfilter = new IntentFilter();
    endfilter.addAction(HOSTESS_TIME_END_ACTION);
    registerReceiver(endT, endfilter);

    initData();
  }

  private void initData() {
    TimeFrame timeFrame1 = new TimeFrame("17: 44", "17: 45", 0, 5);
    TimeFrame timeFrame2 = new TimeFrame("17: 46", "17: 47", 1, 6);

    mDataTimeFrame.add(timeFrame1);
    mDataTimeFrame.add(timeFrame2);

    initBroadcast();
  }

  @OnClick({R.id.bt_start_alarm, R.id.bt_stop_alarm})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.bt_start_alarm:
        LogcatUtils.showELog(TAG, "启动定时器");

//        Intent intent = new Intent(ACTION);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 100,
//            intent, FLAG_CANCEL_CURRENT);
//        mAlarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime() + 20 * 1000,
//            pi);

        Calendar calendar = Calendar.getInstance();
//
//        Intent alarmIntent = new Intent(ACTION);
//        PendingIntent pi = PendingIntent
//            .getBroadcast(this, 100, alarmIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 39);
//        calendar.set(Calendar.SECOND, 10);
//
//        mAlarmManager
//            .setRepeating(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), INTERVAL,
//                pi);

//        Intent intent = new Intent(this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent
//            .getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 41);
//        calendar.set(Calendar.SECOND, 10);
//
//        pendingIntent = PendingIntent
//            .getBroadcast(this, 10, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//
//        long cMill = calendar.getTimeInMillis();
//        long sMill = System.currentTimeMillis();
//
//        LogcatUtils.showELog(TAG, "cMill = " + cMill);
//        LogcatUtils.showELog(TAG, "sMill = " + sMill);
        break;
      case R.id.bt_stop_alarm:
        LogcatUtils.showELog(TAG, "停止定时器");
        Intent intentStop = new Intent(ACTION);
        PendingIntent piStop = PendingIntent.getBroadcast(this, 100,
            intentStop, FLAG_CANCEL_CURRENT);
        mAlarmManager.cancel(piStop);
        break;
      default:
        break;
    }
  }

  private void initBroadcast() {
    Calendar calendar = Calendar.getInstance();
    int size = mDataTimeFrame.size();
    for (int i = 0; i < size; i++) {
      TimeFrame timeFrame = mDataTimeFrame.get(i);
      int startHour = Integer.valueOf(timeFrame.startTime.split(SPLIT)[0]);
      int startMinu = Integer.valueOf(timeFrame.startTime.split(SPLIT)[1]);
      int endHour = Integer.valueOf(timeFrame.endTime.split(SPLIT)[0]);
      int endMinu = Integer.valueOf(timeFrame.endTime.split(SPLIT)[1]);

      LogcatUtils.showELog(TAG,
          "initBroadcast startHour = " + startHour + ";startMinu=" + startMinu + ";endHour="
              + endHour + ";endMinu=" + endMinu);
      int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
      int currentMinute = calendar.get(Calendar.MINUTE);
      if (startHour < currentHour || (startHour == currentHour && startMinu < currentMinute)) {
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
      }
      calendar.set(Calendar.HOUR_OF_DAY, startHour);
      calendar.set(Calendar.MINUTE, startMinu);
      calendar.set(Calendar.SECOND, 0);

      LogcatUtils.showELog(TAG, "initBroadcast timeFrame = " + timeFrame.toString());

      PendingIntent pendingIntent = PendingIntent
          .getBroadcast(this, timeFrame.startRequestCode,
              mAlarmStartTimeIntent,
              PendingIntent.FLAG_CANCEL_CURRENT);
      mAlarmManager
          .setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
              pendingIntent);

      pendingIntent = PendingIntent
          .getBroadcast(this, timeFrame.endRequestCode,
              mAlarmEndTimeIntent,
              PendingIntent.FLAG_CANCEL_CURRENT);

      calendar.set(Calendar.HOUR_OF_DAY, endHour);
      calendar.set(Calendar.MINUTE, endMinu);
      calendar.set(Calendar.SECOND, 0);
      mAlarmManager
          .setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
              pendingIntent);

    }
  }

  BroadcastReceiver t = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      LogcatUtils.showELog(TAG,
          "t intent.getAction() = " + intent.getAction() + ";intent.getCategories() = " + intent
              .getCategories());
    }
  };
  BroadcastReceiver endT = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      LogcatUtils.showELog(TAG,
          "endT intent.getAction() = " + intent.getAction() + ";intent.getCategories() = " + intent
              .getCategories());
    }
  };

  class TestAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      // 重复定时任务
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + TIME_INTERVAL, pendingIntent);
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + TIME_INTERVAL, pendingIntent);
//            }
      // to do something
//        doSomething();

      LogcatUtils.showELog(TAG,
          "intent.getAction() = " + intent.getAction() + ";intent.getCategories() = " + intent
              .getCategories());
    }
  }
}
