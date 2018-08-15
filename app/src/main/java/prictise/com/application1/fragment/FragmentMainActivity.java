package prictise.com.application1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.7.23 17:00
 * @comment
 */
public class FragmentMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
    }
}
