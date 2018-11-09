package prictise.com.application1.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.11.5 11:55
 * @comment
 */
public class RecycleGridViewActivity extends Activity {

    private GridView mPassRV;
    RecyclerGridViewAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_pass);
        mPassRV = (GridView) findViewById(R.id.rv_password);
        initValue();
    }

    private void initValue() {
        mRecyclerAdapter = new RecyclerGridViewAdapter(this);
    }
}
