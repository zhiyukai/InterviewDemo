package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class AThread implements Runnable {

  private Lock mLock;
  private Condition bCondition;
  private Condition aCondition;

  public AThread(Lock lock, Condition bConditionb, Condition aConditiona) {
    mLock = lock;
    bCondition = bConditionb;
    aCondition = aConditiona;
  }

  @Override
  public void run() {
    mLock.lock();
    while (true) {
      System.out.print("A");

      bCondition.signal();
      try {
        aCondition.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      mLock.unlock();
    }
  }
}
