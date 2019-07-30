package prictise.com.application1.multithreading.practice.StringCrossover;

import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhi
 * @Date 2019/7/30 23:32
 * @Describer
 */
public class Out1Thread implements Runnable {

  private final String TAG = Out1Thread.class.getSimpleName();

  private Object lock;

  public Out1Thread(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    for (int i = 1; i < 52; i += 2) {
//            对Lock对象上锁
      synchronized (lock) {
        LogcatUtils.showDLog(TAG, "i = " + i);
        LogcatUtils.showDLog(TAG, "i + 1 = " + (i + 1));
        System.out.print(i);
        System.out.print(i + 1);
        try {
          // 先释放等待的线程 再进入等待状态
          lock.notify();
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }
}
