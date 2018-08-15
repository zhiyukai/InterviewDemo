package prictise.com.application1.cusview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.7.7 0:18
 * @comment
 */
public class CusViewActivity extends Activity {

    private String TAG = CusViewActivity.class.getSimpleName();
    private Switchbutton myButton;
    private ToggleButton mToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_view);
        myButton = (Switchbutton) findViewById(R.id.mybutton);
        mToggleButton = (ToggleButton) findViewById(R.id.tb_button);
        //在外部设置监听
        myButton.setOnStateChange(new Switchbutton.OnStateChangeListener() {

            public void onStateChange(boolean state) {
                Toast.makeText(CusViewActivity.this, "当前状态---" + state, Toast.LENGTH_SHORT).show();
            }
        });

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.e(TAG, "选中状态....");
                    Toast.makeText(CusViewActivity.this, "it's on now", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "取消选中状态....");
                    Toast.makeText(CusViewActivity.this, "it's off now", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
