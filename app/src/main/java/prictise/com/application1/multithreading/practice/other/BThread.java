package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class BThread implements Runnable {

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
    while (true) {
      System.out.print("B");
      cCondition.signal();
      try {
        bCondition.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      mLock.unlock();
    }
  }
}
