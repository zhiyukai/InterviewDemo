package prictise.com.application1.cusService;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.07.04 18:02
 * @Comment
 */
public class TestIntentService extends IntentService {

  private static final String TAG = TestIntentService.class.getSimpleName();
  private int mCount;
  private boolean isRunning;

  /**
   * Creates an IntentService.  Invoked by your subclass's constructor.
   *
   * @param name Used to name the worker thread, important only for debugging.
   */
  public TestIntentService(String name) {
    super(name);
  }

  public TestIntentService() {
    super("tets");
  }

  @Override
  public void onCreate() {
    super.onCreate();
    LogcatUtils.showDLog(TAG, "onCreate()");
  }

  @Override
  public void onStart(@Nullable Intent intent, int startId) {
    super.onStart(intent, startId);
    LogcatUtils.showDLog(TAG, "onStart(@Nullable Intent intent, int startId)");
  }

  @Override
  public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
    LogcatUtils.showDLog(TAG, "onStartCommand");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    LogcatUtils.showDLog(TAG, "onDestroy");
    super.onDestroy();
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    LogcatUtils.showDLog(TAG, "onBind");
    return super.onBind(intent);
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    LogcatUtils.showDLog(TAG, "onHandleIntent count = " + mCount);

    try {
      Thread.sleep(3000);

      isRunning = true;
      mCount = 0;
      while (isRunning) {
        mCount++;
        if (mCount >= 20) {
          LogcatUtils.showDLog(TAG, "onHandleIntent if (mCount >= 10) count = " + mCount);
          isRunning = false;
        }
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
