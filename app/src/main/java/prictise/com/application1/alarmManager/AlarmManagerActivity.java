package prictise.com.application1.alarmManager;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.DateTimeUtil;
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
                String alarmDateTime = DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 20);
                long alarmDateTimeMillis = DateTimeUtil.stringToMillis(alarmDateTime);
                AlarmManagerUtil.sendRepeatAlarmBroadcast(this, 100,
                        AlarmManager.RTC_WAKEUP, alarmDateTimeMillis, 3 * 1000,
                        DeleteLogAlarmReceiver.class);
                break;
            case R.id.bt_stop_alarm:
                break;
        }
    }
}
