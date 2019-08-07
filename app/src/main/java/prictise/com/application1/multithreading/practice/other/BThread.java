package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class BThread implements Runnable {

  private final String TAG = BThread.class.getSimpleName();
  private int mCount;
  private Lock mLock;
  private Condition bCondition;
  private Condition cCondition;

  public BThread(Lock lock, Condition bConditionb, Condition cConditionc) {
    mLock = lock;
    bCondition = bConditionb;
    cCondition = cConditionc;
  }

  @Override
  public void run() {
    mLock.lock();
    try {
//      while (mCount < ThreadConstants.COUNT) {
      while (true) {
        try {
          Thread.sleep(250);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.print("B");
        mCount++;
        cCondition.signal();
        bCondition.await();
      }
//      cCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      mLock.unlock();
    }
  }
}
