package prictise.com.application1.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import prictise.com.application1.R;
import prictise.com.application1.utils.AlarmManagerUtil;
import prictise.com.application1.utils.DateTimeUtil;

/**
 * @Author zsj
 * @Date 2018-11-08 23:21
 * @Commit
 */
public class AlarmManagerActivity extends Activity implements View.OnClickListener {
    // 开启服务按钮
    private Button startServiceBtn;
    // 取消服务按钮
    private Button cancelServiceBtn;

    // 模拟的task id
    private static int mTaskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        startServiceBtn = (Button) findViewById(R.id.start_service_btn);
        cancelServiceBtn = (Button) findViewById(R.id.cancel_service_btn);

        startServiceBtn.setOnClickListener(this);
        cancelServiceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service_btn:
                Intent i = new Intent(this, AlarmService.class);
                // 获取20秒之后的日期时间字符串
                i.putExtra("alarm_time",
                        DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 20));
                i.putExtra("task_id", mTaskId);
                startService(i);
                break;
            case R.id.cancel_service_btn:
                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId,
                        AlarmReceiver.class);
                break;
            default:
                break;
        }
    }
}
