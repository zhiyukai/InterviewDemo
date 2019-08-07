package prictise.com.application1.multithreading.practice.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhisiyi
 * @Date 2019.07.31 11:38
 * @Comment
 */
public class ReentrantLockTest {

  private static Integer nowCnt = 0;

  static class Thread1 extends Thread {

    private ReentrantLock lock;

    private Condition condition1;

    private Condition condition2;

    private Condition condition3;


    public Thread1(ReentrantLock lck, Condition con1, Condition con2, Condition con3) {
      lock = lck;
      condition1 = con1;
      condition2 = con2;
      condition3 = con3;
    }

    @Override
    public void run() {

      try {
        lock.lock();
        while (nowCnt < 50) {
          System.out.print("thread-1: ");
          for (int i = 0; i < 3; i++) {
            System.out.print(nowCnt + " ");
            ++nowCnt;
          }
          System.out.println();
          //线程2激活
          condition2.signal();
          //线程1
          condition1.await();
        }
        condition2.signal();

      } catch (Exception e) {
        e.printStackTrace();

      } finally {
        lock.unlock();
      }

    }
  }

  static class Thread2 extends Thread {

    private ReentrantLock lock;

    private Condition condition1;

    private Condition condition2;

    private Condition condition3;


    public Thread2(ReentrantLock lck, Condition con1, Condition con2, Condition con3) {
      lock = lck;
      condition1 = con1;
      condition2 = con2;
      condition3 = con3;
    }

    @Override
    public void run() {

      try {
        lock.lock();
        while (nowCnt < 50) {
          System.out.print("thread-2: ");
          for (int i = 0; i < 3; i++) {
            System.out.print(nowCnt + " ");
            ++nowCnt;
          }
          System.out.println();
          //线程2激活
          condition3.signal();
          //线程3等待
          condition2.await();
        }

        condition3.signal();

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }


    }
  }

  static class Thread3 extends Thread {

    private ReentrantLock lock;

    private Condition condition1;

    private Condition condition2;

    private Condition condition3;


    public Thread3(ReentrantLock lck, Condition con1, Condition con2, Condition con3) {
      lock = lck;
      condition1 = con1;
      condition2 = con2;
      condition3 = con3;
    }

    @Override
    public void run() {

      try {
        lock.lock();
        while (nowCnt < 50) {
          System.out.print("thread-3: ");
          for (int i = 0; i < 3; i++) {
            System.out.print(nowCnt + " ");
            ++nowCnt;
          }
          System.out.println();
          //线程1激活
          condition1.signal();
          condition3.await();
        }

        condition1.signal();

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }

    }
  }
}
