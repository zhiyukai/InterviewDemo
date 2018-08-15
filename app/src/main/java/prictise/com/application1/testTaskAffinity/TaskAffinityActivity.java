package prictise.com.application1.testTaskAffinity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.6.18 0:36
 * @comment
 */
public class TaskAffinityActivity extends Activity {

    private TextView mNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affinity);
        mNameTV = (TextView) findViewById(R.id.tv_name);
    }
}
