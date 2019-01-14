package prictise.com.application1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.FaceDetect.FaceDetectorActivity;
import prictise.com.application1.animation.AnimationActivity;
import prictise.com.application1.animation.PropertyAnimationActivity;
import prictise.com.application1.circleImageView.CircleActivity;
import prictise.com.application1.countDown.CountDownActivity;
import prictise.com.application1.cus.CusActivity;
import prictise.com.application1.cusListview.RefreshListViewActivity;
import prictise.com.application1.cusListview.weightPullLoadmore.RefreshListView2Activity;
import prictise.com.application1.cusview.ClockActivity;
import prictise.com.application1.cusview.CusViewActivity;
import prictise.com.application1.dialog.DialogActivity;
import prictise.com.application1.eventDispatch.EventDispatchActivity;
import prictise.com.application1.gridview.GridViewActivity;
import prictise.com.application1.gridview.RecycleGridViewActivity;
import prictise.com.application1.gridview.pulltorefresh.PullRefreshActivity;
import prictise.com.application1.lifecycle.SingleInstanceActivity;
import prictise.com.application1.lifecycle.SingleTaskActivity;
import prictise.com.application1.lifecycle.SingleTopActivity;
import prictise.com.application1.lifecycle.StandardActivity;
import prictise.com.application1.log.LogActivity;
import prictise.com.application1.map.MapActivity;
import prictise.com.application1.multithreading.Consumer;
import prictise.com.application1.multithreading.Producter;
import prictise.com.application1.multithreading.SyncStack;
import prictise.com.application1.networkTest.NetWorkActivity;
import prictise.com.application1.notify.NotifyActivity;
import prictise.com.application1.pictureMemory.PictureCompressActivity;
import prictise.com.application1.pictureMemory.PictureMemoryActivity;
import prictise.com.application1.qiniu.activity.QiniuActivity;
import prictise.com.application1.rippeview.RippleActivity;
import prictise.com.application1.wheel.WheelActivity;

public class MainActivity extends Activity {

    private final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bt_lanch_standard)
    Button mClickStandardBT;
    @BindView(R.id.bt_lanch_singleTop)
    Button mClickSingleTopBT;
    @BindView(R.id.bt_lanch_singleTask)
    Button mClickSingleTaskBT;
    @BindView(R.id.bt_lanch_singleInstance)
    Button mClickSingleInstanceBT;
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SyncStack stack = new SyncStack();
        Consumer c = new Consumer(stack);
        Producter p = new Producter(stack);

        new Thread(p).start();
        new Thread(c).start();

        int a = 1 << 20;
        Log.e("zhishaoju", "SHUTDOWN = " + SHUTDOWN);
        Log.e("zhishaoju", "SHUTDOWN = " + SHUTDOWN);
        Log.e("zhishaoju", "STOP = " + STOP);
        Log.e("zhishaoju", "TIDYING = " + TIDYING);
        Log.e("zhishaoju", "TERMINATED = " + TERMINATED);

//        StringRequest stringRequest = new StringRequest();
//        Volley.newRequestQueue(stringRequest);

        String s1 = "a";
        String s2 = new String("a");

        Log.e(TAG, "" + (s1 == s2));
        Log.e(TAG, "" + s1.equals(s2));

        int b = 10;
        Integer integer = new Integer(10);
        Log.e(TAG, "" + (b == integer));
        Log.e(TAG, "" + (b + "").equals(Integer.toString(integer)));


        int b2 = 1000;
        Integer integer2 = new Integer(1000);
        Integer integer3 = new Integer(1000);
        Integer integer4 = 10;
        Integer integer5 = 10;
        Log.e(TAG, "b2 == integer2:" + (b2 == integer2));
        Log.e(TAG, "integer2 == integer3:" + (integer2 == integer3));
        Log.e(TAG, "integer4 == integer5:" + (integer4 == integer5));
        Log.e(TAG, "" + (b2 + "").equals(Integer.toString(integer2)));

        float f1 = 122f;
        Float f2 = new Float(1f);
        Float f3 = new Float(1f);
        Float f4 = 1000f;
        Float f5 = 1000f;
        Float f6 = 10f;
        Float f7 = 10f;
        Log.e(TAG, "f1 == f2 :" + (f1 == f2));
        Log.e(TAG, "f2 == f3 :" + (f2 == f3));
        Log.e(TAG, "f4 == f5 :" + (f4 == f5));
        Log.e(TAG, "f6 == f7 :" + (f6 == f7));

        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());//调用该类来获取磁盘信息（而getDataDirectory就是内部存储）
        long tcounts = statFs.getBlockCount();//总共的block数
        long counts = statFs.getAvailableBlocks(); //获取可用的block数
        long size = statFs.getBlockSize(); //每格所占的大小，一般是4KB==
        long availROMSize = counts * size;//可用内部存储大小
        long totalROMSize = tcounts * size; //内部存储总大小

        Log.e("手机内存", "内存：" + totalROMSize);
    }

    @OnClick(R.id.bt_lanch_standard)
    public void standardClick() {
        startActivity(new Intent(this, StandardActivity.class));
    }

    @OnClick(R.id.bt_lanch_singleTop)
    public void singleTopClick() {
        startActivity(new Intent(this, SingleTopActivity.class));
//        Toast.makeText(MainActivity.this, "你好，按钮被点击了", Toast.LENGTH_SHORT)
//                .show();
    }

    @OnClick(R.id.bt_lanch_singleTask)
    public void singleTaskClick() {
        startActivity(new Intent(this, SingleTaskActivity.class));
//        Toast.makeText(MainActivity.this, "你好，按钮被点击了", Toast.LENGTH_SHORT)
//                .show();
    }

    @OnClick(R.id.bt_lanch_singleInstance)
    public void singleInstanceClick() {
        startActivity(new Intent(this, SingleInstanceActivity.class));
//        Toast.makeText(MainActivity.this, "你好，按钮被点击了", Toast.LENGTH_SHORT)
//                .show();
    }

    @OnClick(R.id.bt_lanch_picture_memory)
    public void pictureMemoryClick() {
        startActivity(new Intent(this, PictureMemoryActivity.class));
    }

    @OnClick(R.id.bt_lanch_picture_compress)
    public void pictureCompressClick() {
        startActivity(new Intent(this, PictureCompressActivity.class));
    }

    @OnClick(R.id.bt_cus_layout)
    public void cusLayout() {
        startActivity(new Intent(this, CusActivity.class));
    }

    @OnClick(R.id.bt_on_activity_result)
    public void launchOnActivityResultActivity() {
        startActivity(new Intent(this,
                prictise.com.application1.onActivityResult.StandardActivity.class));
    }

    @OnClick(R.id.bt_task_affinity)
    public void lauchTaskActivity() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("prictise.com.myapplication",
                "prictise.com.myapplication.testTaskAffinity.TaskAffinityActivity");
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    @OnClick(R.id.bt_cus_view)
    public void lauchCusViewActivity() {
        startActivity(new Intent(this, CusViewActivity.class));
    }

    @OnClick(R.id.bt_ripple)
    public void lauchRippleActivity() {
        startActivity(new Intent(this, RippleActivity.class));
    }

    @OnClick(R.id.bt_lanch_dialog)
    public void lauchDialogActivity() {
        startActivity(new Intent(this, DialogActivity.class));
    }

    @OnClick(R.id.bt_lanch_circle_image)
    public void lauchCircleImageActivity() {
        startActivity(new Intent(this, CircleActivity.class));
    }

    @OnClick(R.id.bt_lanch_event_dispatch)
    public void lauchEventDispatchActivity() {
        startActivity(new Intent(this, EventDispatchActivity.class));
    }

    @OnClick(R.id.bt_lanch_animation)
    public void lauchAnimationActivity() {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    @OnClick(R.id.bt_lanch_property_animation)
    public void lauchPropertyAnimationActivity() {
        startActivity(new Intent(this, PropertyAnimationActivity.class));
    }

    @OnClick(R.id.bt_lanch_notify)
    public void lauchNotifyActivity() {
        startActivity(new Intent(this, NotifyActivity.class));
    }

    @OnClick(R.id.bt_lanch_gridview)
    public void lauchGridViewActivity() {
        startActivity(new Intent(this, GridViewActivity.class));
    }

    @OnClick(R.id.bt_lanch_face_detect)
    public void lanchFaceDetect() {
        startActivity(new Intent(this, FaceDetectorActivity.class));
    }

    @OnClick(R.id.bt_lanch_clock)
    public void lanchClock() {
        startActivity(new Intent(this, ClockActivity.class));
    }

    @OnClick(R.id.bt_lanch_net_test)
    public void lanchNetWorkTest() {
        startActivity(new Intent(this, NetWorkActivity.class));
    }

    @OnClick(R.id.bt_lanch_count_time)
    public void lanchCountTime() {
        startActivity(new Intent(this, CountDownActivity.class));
    }

    @OnClick(R.id.bt_log)
    public void lanchCollectLog() {
        startActivity(new Intent(this, LogActivity.class));
    }

    @OnClick(R.id.bt_wheel)
    public void lanchWheel() {
        startActivity(new Intent(this, WheelActivity.class));
    }

    @OnClick(R.id.bt_recyle)
    public void lanchRecyle() {
        startActivity(new Intent(this, RecycleGridViewActivity.class));
    }

    @OnClick(R.id.bt_time_setting)
    public void lanchTimeSetting() {
        startActivity(new Intent(this, prictise.com.application1.alarm.AlarmManagerActivity.class));
    }

    @OnClick(R.id.bt_lanch_listview)
    public void lanchListView() {
        startActivity(new Intent(this, RefreshListViewActivity.class));
    }

    @OnClick(R.id.bt_lanch_loadmore_listview)
    public void lanch2ListView() {
        startActivity(new Intent(this, RefreshListView2Activity.class));
    }

    @OnClick(R.id.bt_lanch_gridview_frash)
    public void lanchGridFrash() {
        startActivity(new Intent(this, PullRefreshActivity.class));
    }

    @OnClick(R.id.bt_lanch_qiniu)
    public void lanchQiniu() {
        startActivity(new Intent(this, QiniuActivity.class));
    }

    @OnClick(R.id.bt_lanch_test_map)
    public void lanchTestMap() {
        startActivity(new Intent(this, MapActivity.class));
    }
}
