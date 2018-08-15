package prictise.com.application1.cus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.6.25 21:51
 * @comment
 */
public class CusActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_layout);
    }
}
