package prictise.com.application1.cusview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.8.26 21:49
 * @comment 时钟的界面
 */
public class ClockActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
    }
}
