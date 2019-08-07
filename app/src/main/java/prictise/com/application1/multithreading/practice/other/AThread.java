package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class AThread implements Runnable {

  private final String TAG = AThread.class.getSimpleName();
  private int mCount;
  private Lock mLock;
  private Condition aCondition;
  private Condition bCondition;

  public AThread(Lock lock, Condition aConditiona, Condition bConditionb) {
    mLock = lock;
    aCondition = aConditiona;
    bCondition = bConditionb;
  }

  @Override
  public void run() {
    try {
      mLock.lock();
//      while (mCount < ThreadConstants.COUNT) {
      while (true) {
        try {
          Thread.sleep(250);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.print("A");
        mCount++;
        bCondition.signal();
        aCondition.await();
      }
//      bCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      mLock.unlock();
    }
  }
}
