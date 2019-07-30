package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class CThread implements Runnable {

  private Lock mLock;
  private Condition cCondition;
  private Condition aCondition;

  public CThread(Lock lock, Condition cConditionc, Condition aConditiona) {
    mLock = lock;
    cCondition = cConditionc;
    aCondition = aConditiona;
  }

  @Override
  public void run() {
    mLock.lock();
    while (true) {
      System.out.println("C");
      aCondition.signal();
      try {
        cCondition.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      mLock.unlock();
    }
  }
}
