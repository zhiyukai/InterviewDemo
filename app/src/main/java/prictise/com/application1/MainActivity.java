package prictise.com.application1;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.File;
import prictise.com.application1.FaceDetect.FaceDetectorActivity;
import prictise.com.application1.alarmManager.AlarmManagerActivity;
import prictise.com.application1.animation.AnimationActivity;
import prictise.com.application1.animation.PropertyAnimationActivity;
import prictise.com.application1.animation.TestPropertyAniActivity;
import prictise.com.application1.asyncTask.AsyncTaskActivity;
import prictise.com.application1.canvas.TestCanvasActivity;
import prictise.com.application1.circleImageView.CircleActivity;
import prictise.com.application1.countDown.CountDownActivity;
import prictise.com.application1.cus.CusActivity;
import prictise.com.application1.cusListview.RecycleViewActivity;
import prictise.com.application1.cusListview.RefreshListViewActivity;
import prictise.com.application1.cusListview.ViewPagerRefreshListActivity;
import prictise.com.application1.cusListview.weightPullLoadmore.RefreshListView2Activity;
import prictise.com.application1.cusService.TestIntentService;
import prictise.com.application1.cusview.CusViewActivity;
import prictise.com.application1.dialog.DialogActivity;
import prictise.com.application1.dynamicProxy.DynamicProxyActivity;
import prictise.com.application1.dynamicProxy.DynamicProxyActvity;
import prictise.com.application1.eventBus.EventBusActivity;
import prictise.com.application1.eventDispatch.EventDispatchActivity;
import prictise.com.application1.fragment.FragmentMainActivity;
import prictise.com.application1.gridview.GridViewActivity;
import prictise.com.application1.gridview.RecycleGridViewActivity;
import prictise.com.application1.gridview.pulltorefresh.PullRefreshActivity;
import prictise.com.application1.intent.IntentActivity;
import prictise.com.application1.kotlin.database.KotlinMainActivity;
import prictise.com.application1.kotlin.okhttpTest.OkHttpActivity;
import prictise.com.application1.kotlin.rxjava.JavaRxjavaTestActivity;
import prictise.com.application1.kotlin.rxjava.RxJavaActivity;
import prictise.com.application1.lifecycle.SingleInstanceActivity;
import prictise.com.application1.lifecycle.SingleTaskActivity;
import prictise.com.application1.lifecycle.SingleTopActivity;
import prictise.com.application1.lifecycle.StandardActivity;
import prictise.com.application1.log.LogActivity;
import prictise.com.application1.map.MapActivity;
import prictise.com.application1.multithreading.SyncStack;
import prictise.com.application1.multithreading.ThreadActivity;
import prictise.com.application1.networkTest.NetWorkActivity;
import prictise.com.application1.notify.NotifyActivity;
import prictise.com.application1.otherApplication.ArouseOtherApplication;
import prictise.com.application1.pictureMemory.PictureCompressActivity;
import prictise.com.application1.pictureMemory.PictureMemoryActivity;
import prictise.com.application1.qiniu.activity.QiniuActivity;
import prictise.com.application1.rippeview.RippleActivity;
import prictise.com.application1.shipei.ShiPeiActivity;
import prictise.com.application1.testConstraintLayout.ConstraintLayoutActivity;
import prictise.com.application1.testEdittext.TestEditTextActivity;
import prictise.com.application1.testGreendao.TestGreenDaoActivity;
import prictise.com.application1.testStackActivity.MainStackActivity;
import prictise.com.application1.viewpager.ViewPagerMainActivity;
import prictise.com.application1.viewpager.ViewPagerSourceActivity;
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

  private static String[] PERMISSIONS_STORAGE = {
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.RECORD_AUDIO};
  //请求状态码动态申请权限
  private static int REQUEST_PERMISSION_CODE = 1;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    long time = SystemClock.currentThreadTimeMillis();
    long time2 = System.currentTimeMillis();
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.e(TAG, "time3 = " + SystemClock.currentThreadTimeMillis());
      }
    }).start();
    Log.e(TAG, "time = " + time);
    Log.e(TAG, "time2 = " + time2);

    ButterKnife.bind(this);
    //2、初始化：
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
          != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat
            .requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
      }
    }

    ImageView iv = new ImageView(this);
//    HookUtil hookUtil = new HookUtil();
//    hookUtil.hookStartActivity(this);

    SyncStack stack = new SyncStack();
//    Consumer c = new Consumer(stack);
//    Producter p = new Producter(stack);
//
//    new Thread(p).start();
//    new Thread(c).start();

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

    StatFs statFs = new StatFs(
        Environment.getDataDirectory().getPath());//调用该类来获取磁盘信息（而getDataDirectory就是内部存储）
    long tcounts = statFs.getBlockCount();//总共的block数
    long counts = statFs.getAvailableBlocks(); //获取可用的block数
    long size = statFs.getBlockSize(); //每格所占的大小，一般是4KB==
    long availROMSize = counts * size;//可用内部存储大小
    long totalROMSize = tcounts * size; //内部存储总大小

    Log.e("手机内存", "内存：" + totalROMSize);

//    Glide.with(this).load("").into(iv);
    File f = new File("");
    f.length();

  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_PERMISSION_CODE) {
      for (int i = 0; i < permissions.length; i++) {
        Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @OnClick(R.id.bt_cus_view_pager)
  public void testCusViewPager() {
    startActivity(
        new Intent(this, ViewPagerSourceActivity.class));
  }
  @OnClick(R.id.bt_test_arouse_other_application)
  public void testArouseOtherApplication() {
    startActivity(
        new Intent(this, ArouseOtherApplication.class));
  }

  @OnClick(R.id.bt_test_recycle_view)
  public void testRecycleView() {
    startActivity(
        new Intent(this, RecycleViewActivity.class));
  }

  @OnClick(R.id.bt_lunch_viewpager_listview)
  public void testViewPagerListView() {
    startActivity(
        new Intent(this, ViewPagerRefreshListActivity.class));
  }

  @OnClick(R.id.bt_lunch_test_okhttp)
  public void testOKHttp() {
    startActivity(
        new Intent(this, OkHttpActivity.class));
  }

  @OnClick(R.id.bt_lunch_test_greendao_crud)
  public void testGreenDaoCRUD() {
    startActivity(
        new Intent(this, prictise.com.application1.kotlin.greendao.GreenDaoTestActivity.class));
  }

  @OnClick(R.id.bt_lunch_dynamic_proxy)
  public void testDynamicClick() {
    startActivity(new Intent(this, DynamicProxyActvity.class));
  }

  @OnClick(R.id.bt_lunch_test_edittext)
  public void testEdittextClick() {
    startActivity(new Intent(this, TestEditTextActivity.class));
  }

  @OnClick(R.id.bt_lunch_greendao)
  public void testGreenDaoClick() {
    startActivity(new Intent(this, TestGreenDaoActivity.class));
  }

  @OnClick(R.id.bt_lunch_javarxjava)
  public void testJavaRxJavaClick() {
    startActivity(new Intent(this, JavaRxjavaTestActivity.class));
  }

  @OnClick(R.id.bt_lunch_rxjava)
  public void testRxJavaClick() {
    startActivity(new Intent(this, RxJavaActivity.class));
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

  @OnClick(R.id.bt_lanch_test_property_animation)
  public void lauchTestPropertyAnimationActivity() {
    startActivity(new Intent(this, TestPropertyAniActivity.class));
  }

  @OnClick(R.id.bt_launch_test_canvas)
  public void launchTestCanvasActivity() {
    startActivity(new Intent(this, TestCanvasActivity.class));
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
//    startActivity(new Intent(this, ClockActivity.class));
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

  @OnClick(R.id.bt_lanch_alarm)
  public void lanchAlarm() {
    startActivity(new Intent(this, AlarmManagerActivity.class));
  }

  @OnClick(R.id.bt_lanch_thread)
  public void lanchThread() {
    startActivity(new Intent(this, ThreadActivity.class));
  }

  @OnClick(R.id.bt_lanch_intent_service)
  public void lanchIntentService() {
    startActivity(new Intent(this, TestIntentService.class));
  }

  @OnClick(R.id.bt_lanch_event_bus)
  public void lanchEventBus() {
    startActivity(new Intent(this, EventBusActivity.class));
  }

  @OnClick(R.id.bt_lanch_test_stack)
  public void lanchTestStackActivity() {
    startActivity(new Intent(this, MainStackActivity.class));
  }

  @OnClick(R.id.bt_lanch_test_constraintlayout)
  public void lanchTestconstraintlayoutActivity() {
    startActivity(new Intent(this, ConstraintLayoutActivity.class));
  }

  @OnClick(R.id.bt_lanch_fragment)
  public void launchFragment() {
    startActivity(new Intent(this, FragmentMainActivity.class));
  }

  @OnClick(R.id.bt_launch_test_intent)
  public void launchTestIntentActivity() {
    startActivity(new Intent(this, IntentActivity.class));
  }

  @OnClick(R.id.bt_lunch_async_task)
  public void lunchAsyncActivity() {
    startActivity(new Intent(this, AsyncTaskActivity.class));
  }

  @OnClick(R.id.bt_lunch_kotlin)
  public void lunchKotlinActivity() {
    startActivity(new Intent(this, KotlinMainActivity.class));
  }

  @OnClick(R.id.bt_launch_shipei)
  public void launchShiPei() {
    startActivity(new Intent(this, ShiPeiActivity.class));
  }

  @OnClick(R.id.bt_dynamic_proxy)
  public void launchDynamicProxy() {
    startActivity(new Intent(this, DynamicProxyActivity.class));
  }

  @OnClick(R.id.bt_viewpager_infinite)
  public void launchViewpagerInfinite() {
    startActivity(new Intent(this, ViewPagerMainActivity.class));
  }

}
