package prictise.com.application1;

import static com.android.volley.Request.Method.GET;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import prictise.com.application1.eventBus.EventBusTest;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zsj
 * @Date 2018-12-29 7:18
 * @Commit
 */
public class BaseActivity extends Activity {

  private final String TAG = BaseActivity.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);

  }


  @Subscribe(threadMode = ThreadMode.BACKGROUND)
  public void onEventTest(final EventBusTest eventBusTest) {
    LogcatUtils.showDLog(TAG, "BACKGROUND eventBusTest = " + eventBusTest.msg);
    LogcatUtils.showDLog(TAG, "BACKGROUND thread = " + Thread.currentThread());
  }

  @Subscribe(threadMode = ThreadMode.ASYNC)
  public void onEventTest1(final EventBusTest eventBusTest) {
    LogcatUtils.showDLog(TAG, "ASYNC eventBusTest = " + eventBusTest.msg);
    LogcatUtils.showDLog(TAG, "ASYNC thread = " + Thread.currentThread());
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEventTest2(final EventBusTest eventBusTest) {
    LogcatUtils.showDLog(TAG, "MAIN eventBusTest = " + eventBusTest.msg);
    LogcatUtils.showDLog(TAG, "MAIN thread = " + Thread.currentThread());
  }

  @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
  public void onEventTest3(final EventBusTest eventBusTest) {
    LogcatUtils.showDLog(TAG, "MAIN_ORDERED eventBusTest = " + eventBusTest.msg);
    LogcatUtils.showDLog(TAG, "MAIN_ORDERED thread = " + Thread.currentThread());
  }

  @Subscribe(threadMode = ThreadMode.POSTING)
  public void onEventTest4(final EventBusTest eventBusTest) {
    LogcatUtils.showDLog(TAG, "POSTING eventBusTest = " + eventBusTest.msg);
    LogcatUtils.showDLog(TAG, "POSTING thread = " + Thread.currentThread());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }
}
