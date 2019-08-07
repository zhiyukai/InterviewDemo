package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import prictise.com.application1.multithreading.practice.other.ReentrantLockTest.Thread1;
import prictise.com.application1.multithreading.practice.other.ReentrantLockTest.Thread2;
import prictise.com.application1.multithreading.practice.other.ReentrantLockTest.Thread3;

/**
 * @Author zhi
 * @Date 2019/7/31 0:59
 * @Describer
 */
public class TestOther {

  public TestOther() {
    ReentrantLock lock = new ReentrantLock();
    Condition aCondition = lock.newCondition();
    Condition bCondition = lock.newCondition();
    Condition cCondition = lock.newCondition();

    Thread aThread = new Thread(new AThread(lock, aCondition, bCondition));
    Thread bThread = new Thread(new BThread(lock, bCondition, cCondition));
    Thread cThread = new Thread(new CThread(lock, cCondition, aCondition));

//    for (;;) {
    aThread.start();
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    bThread.start();
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    cThread.start();
//    }
  }

  public TestOther(String s) {
    try {
      ReentrantLock lock = new ReentrantLock();
      Condition condition1 = lock.newCondition();
      Condition condition2 = lock.newCondition();
      Condition condition3 = lock.newCondition();
      Thread1 thread1 = new Thread1(lock, condition1, condition2, condition3);
      Thread2 thread2 = new Thread2(lock, condition1, condition2, condition3);
      Thread3 thread3 = new Thread3(lock, condition1, condition2, condition3);

      thread1.start();
      Thread.sleep(50);
      thread2.start();
      Thread.sleep(50);
      thread3.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();
    Condition aCondition = lock.newCondition();
    Condition bCondition = lock.newCondition();
    Condition cCondition = lock.newCondition();

    Thread aThread = new Thread(new AThread(lock, aCondition, bCondition));
    Thread bThread = new Thread(new BThread(lock, bCondition, cCondition));
    Thread cThread = new Thread(new CThread(lock, cCondition, aCondition));

//    for (;;) {
    aThread.start();
    try {
      Thread.sleep(250);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    bThread.start();
    try {
      Thread.sleep(250);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    cThread.start();
  }
}
