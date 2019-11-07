package prictise.com.application1.eventBus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2019.07.16 10:13
 * @Comment
 */
public class EventBusActivity extends BaseActivity {

  @BindView(R.id.bt_event_posting)
  Button btEventPosting;
  @BindView(R.id.bt_event_async)
  Button btEventAsync;
  @BindView(R.id.bt_event_background)
  Button btEventBackground;
  @BindView(R.id.bt_event_main)
  Button btEventMain;
  @BindView(R.id.bt_thread_event_posting)
  Button btThreadEventPosting;
  @BindView(R.id.bt_thread_event_async)
  Button btThreadEventAsync;
  @BindView(R.id.bt_thread_event_background)
  Button btThreadEventBackground;
  @BindView(R.id.bt_thread_event_main)
  Button btThreadEventMain;

  ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
      .setNameFormat("demo-pool-%d").build();
  ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
      0L, TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
      new ThreadPoolExecutor.AbortPolicy());

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_bus);
    ButterKnife.bind(this);

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @OnClick({R.id.bt_event_posting, R.id.bt_event_async, R.id.bt_event_background,
      R.id.bt_event_main, R.id.bt_thread_event_posting, R.id.bt_thread_event_async,
      R.id.bt_thread_event_background, R.id.bt_thread_event_main})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.bt_event_posting:
        EventBus.getDefault().post(new EventBusTest("bt_event_posting"));
        break;
      case R.id.bt_event_async:
        EventBus.getDefault().post(new EventBusTest("bt_event_async"));
        break;
      case R.id.bt_event_background:
        EventBus.getDefault().post(new EventBusTest("bt_event_background"));
        break;
      case R.id.bt_event_main:
        EventBus.getDefault().post(new EventBusTest("bt_event_main"));
        break;
      case R.id.bt_thread_event_posting:
        singleThreadPool.execute(new Runnable() {
          @Override
          public void run() {
            EventBus.getDefault().post(new EventBusTest("bt_thread_event_posting"));
          }
        });
        break;
      case R.id.bt_thread_event_async:
        singleThreadPool.execute(new Runnable() {
          @Override
          public void run() {
            EventBus.getDefault().post(new EventBusTest("bt_thread_event_async"));
          }
        });
        break;
      case R.id.bt_thread_event_background:
        singleThreadPool.execute(new Runnable() {
          @Override
          public void run() {
            EventBus.getDefault().post(new EventBusTest("bt_thread_event_background"));
          }
        });
        break;
      case R.id.bt_thread_event_main:
        singleThreadPool.execute(new Runnable() {
          @Override
          public void run() {
            EventBus.getDefault().post(new EventBusTest("bt_thread_event_main"));
          }
        });
        break;
      default:
        break;
    }
  }

}
