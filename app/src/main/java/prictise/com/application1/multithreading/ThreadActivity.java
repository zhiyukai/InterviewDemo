package prictise.com.application1.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.Log;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;
import prictise.com.application1.multithreading.practice.other.TestOther;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.04.02 15:37
 * @Comment
 */
public class ThreadActivity extends BaseActivity {

  private static final String TAG = ThreadActivity.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thread);

    TestOther testOther = new TestOther();

//    Test test = new Test();

//    handler.post(new Runnable() {
//      @Override
//      public void run() {
//        LogcatUtils.showELog(TAG, "thread name = " + Thread.currentThread());
//      }
//    });

//    new CameraHandlerThread("camera");
//    MyThread myThread = new MyThread();
//    LogcatUtils.showELog(TAG, "before");
//    myThread.start();
//    synchronized (myThread) {
//      try {
//        myThread.wait();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//    LogcatUtils.showELog(TAG, "after");

  }

  static class MyThread extends Thread {

    @Override
    public void run() {
      synchronized (this) {
        for (int i = 0; i < 3; i++) {
          LogcatUtils.showELog(TAG, "number:" + i);
        }
      }
    }
  }

  private class CameraHandlerThread extends HandlerThread {

    Handler mHandler;

    public CameraHandlerThread(String name) {
      super(name);
      start();
      mHandler = new Handler(getLooper());
      openCamera();
    }

    synchronized void notifyCameraOpened() {
      notify();
    }

    void openCamera() {
      mHandler.post(new Runnable() {
        @Override
        public void run() {
          LogcatUtils.showELog(TAG, "openCamera thread :" + Thread.currentThread());
        }
      });
      try {
        synchronized (this) {
          wait();
        }
      } catch (InterruptedException e) {
        Log.w(TAG, "wait was interrupted");
      }
    }
  }
}
