package prictise.com.application1.countDown;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.9.1 17:29
 * @comment
 */
public class CountDownActivity extends Activity {
    private final String TAG = CountDownActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_start_time)
    public void startTime() {
        CountDownTimer mAskTimer = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                LogcatUtils.showELog(TAG, "onTick");
            }

            public void onFinish() {
                LogcatUtils.showELog(TAG, "onFinish");
            }
        };
        mAskTimer.start();
    }
}
