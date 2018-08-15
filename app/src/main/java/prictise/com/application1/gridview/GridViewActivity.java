package prictise.com.application1.gridview;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;
import prictise.com.application1.utils.SharedPrefsUtil;

/**
 * @author zhisiyi
 * @date 18.8.3 9:51
 * @comment
 */
public class GridViewActivity extends Activity {
    private String TAG = GridViewActivity.class.getSimpleName();
    GridView gv_layout;
    private List<String> mModeList = new ArrayList<>();
    private SelectGridAdapter mSelectGridAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gv_layout = findViewById(R.id.gv_layout);
        mRecyclerView = findViewById(R.id.recyclerview);

        for (int i = 100; i < 110; i++) {
            mModeList.add(i + "");
        }
        gv_layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int gridViewPos,
                                    long arg3) {
                long begin = SystemClock.elapsedRealtime();
                LogcatUtils.showELog(TAG, "号码点击开始时间：" + begin);
                playSound();
////                clearKeyBoardText();
                selectItem(gridViewPos);
//                closeAllKeyboard();
//                if (!isServiceConnect(false)) {
//                    MyLogcat.showLog("服务未连接");
//                }
                LogcatUtils.showELog(TAG, "号码点击结束时间：" + SystemClock.elapsedRealtime());
                LogcatUtils.showELog(TAG, "号码点击花费时间：" + (SystemClock.elapsedRealtime() - begin));
                mSelectGridAdapter.notifyDataSetChanged();
            }
        });
        initSound();
        setSelectAdapter();


        //垂直的Linearlayout
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RecyclerAdapter(getApplicationContext(), getLayoutInflater(), mModeList);
        mRecyclerView.setAdapter(mAdapter);
        //设置布局
//        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                playSound();
                selectItem(position);
                mAdapter.notifyDataSetChanged();
//                Toast.makeText(GridViewActivity.this, "您点击了" + position + "行", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {

            }
        });

    }


    private void selectItem(int gridViewPos) {

        String tableName = SharedPrefsUtil.get(getApplicationContext(), "a", "");
        String newTableName = mModeList.get(gridViewPos);

        if (!TextUtils.isEmpty(tableName) && newTableName.equals(tableName)) {
            SharedPrefsUtil.put(getApplicationContext(), "a", "");
        } else {
            SharedPrefsUtil.put(getApplicationContext(), "a", newTableName);
        }
//        setSelectAdapter();
    }

    private void setSelectAdapter() {

        if (mModeList.size() > 0) {
            if (mSelectGridAdapter == null) {
                mSelectGridAdapter = new SelectGridAdapter(getApplicationContext(), mModeList);
                gv_layout.setAdapter(mSelectGridAdapter);
            } else {
                mSelectGridAdapter.notifyDataSetChanged();
            }
        }
    }

    private SoundPool mSoundPool;//声明一个SoundPool
    private int mSoundID;//创建某个声音对应的音频ID

    /**
     * 初始化音频
     */
    private void initSound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder().build();
        }
        mSoundID = mSoundPool.load(this, R.raw.pressed, 1);
    }

    private void playSound() {
        mSoundPool.play(
                mSoundID,
                0.1f,   //左耳道音量【0~1】
                0.5f,   //右耳道音量【0~1】
                0,     //播放优先级【0表示最低优先级】
                0,     //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1     //播放速度【1是正常，范围从0~2】
        );
    }
}
