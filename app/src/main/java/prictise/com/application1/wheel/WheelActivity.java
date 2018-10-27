package prictise.com.application1.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import prictise.com.application1.R;

/**
 * @Author zsj
 * @Date 2018-10-27 22:10
 * @Commit
 */
public class WheelActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        WheelDialog wheelDialog = new WheelDialog(this);
        wheelDialog.show();
    }
}
