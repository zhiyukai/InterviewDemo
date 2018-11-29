package prictise.com.application1.alarmManager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        ButterKnife.bind(this);
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @OnClick({R.id.bt_start_alarm, R.id.bt_stop_alarm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start_alarm:
                LogcatUtils.showELog(TAG, "启动定时器");
//                String alarmDateTime = DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 20);
//                long alarmDateTimeMillis = DateTimeUtil.stringToMillis(alarmDateTime);
//                AlarmManagerUtil.sendRepeatAlarmBroadcast(this, 100,
//                        AlarmManager.RTC_WAKEUP, alarmDateTimeMillis, 3 * 1000,
//                        DeleteLogAlarmReceiver.class);


                Intent intent = new Intent(this, TestAlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(this, 100,
                        intent, FLAG_CANCEL_CURRENT);
                break;
            case R.id.bt_stop_alarm:
                break;
        }
    }

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
        }
    }
}
