package prictise.com.application1.eventDispatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import prictise.com.application1.MainActivity;
import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.7.11 20:13
 * @comment 事件分发
 */
public class EventDispatchActivity extends Activity {
    private final String TAG = EventDispatchActivity.class.getSimpleName();
    CusView1 button1;
    CusView2 button2;
    CusViewGroup myLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);

        button1 = (CusView1) findViewById(R.id.button1);
        button2 = (CusView2) findViewById(R.id.button2);
        myLayout = (CusViewGroup) findViewById(R.id.my_layout);

//        // 1.为ViewGroup布局设置监听事件
//        myLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "点击了ViewGroup");
//            }
//        });

        // 2. 为按钮1设置监听事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击了button1");
            }
        });

        // 3. 为按钮2设置监听事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击了button2");
            }
        });

    }
}
