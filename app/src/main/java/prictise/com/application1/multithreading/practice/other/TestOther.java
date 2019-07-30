package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhi
 * @Date 2019/7/31 0:59
 * @Describer
 */
public class TestOther {

  public TestOther() {
    Lock lock = new ReentrantLock();
    Condition aCondition = lock.newCondition();
    Condition bCondition = lock.newCondition();
    Condition cCondition = lock.newCondition();

    Thread aThread = new Thread(new AThread(lock, aCondition, bCondition));
    Thread bThread = new Thread(new BThread(lock, bCondition, cCondition));
    Thread cThread = new Thread(new CThread(lock, cCondition, aCondition));

//    for (;;) {
      aThread.start();
      bThread.start();
      cThread.start();
/*      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
//    }
  }
}
