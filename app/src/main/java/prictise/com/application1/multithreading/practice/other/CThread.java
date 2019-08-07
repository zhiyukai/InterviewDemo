package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zhi
 * @Date 2019/7/31 0:07
 * @Describer
 */
public class CThread implements Runnable {

  private final String TAG = CThread.class.getSimpleName();
  private int mCount;
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
    try {
//      while (mCount < ThreadConstants.COUNT) {
      while (true) {
        try {
          Thread.sleep(250);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("C");
        mCount++;
        aCondition.signal();
        cCondition.await();
//        System.out.println("C 执行");
      }
//      aCondition.signal();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mLock.unlock();
    }
  }
}
