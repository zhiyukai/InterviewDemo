package prictise.com.application1.lock;

/**
 * @Author zhisiyi
 * @Date 2019.09.18 15:09
 * @Comment Project Name:Spring0725 File Name:TestSynchronized.java Package Name:work1128.singleton
 * Date:2017年11月28日下午3:44:24 Copyright (c) 2017, 深圳金融电子结算中心 All Rights Reserved.
 */
/**
 * Project Name:Spring0725
 * File Name:TestSynchronized.java
 * Package Name:work1128.singleton
 * Date:2017年11月28日下午3:44:24
 * Copyright (c) 2017, 深圳金融电子结算中心 All Rights Reserved.
 *
 */

/**
 * ClassName:TestSynchronized <br/>
 * Function: 测试实例锁和类锁
 * Date:     2017年11月28日 下午3:44:24 <br/>
 * @author prd-lxw
 * @version 1.0
 * @since JDK 1.7
 * @see
 */

public class TestSynchronized {

  public void test1() {
    synchronized (this) {
      int i = 5;
      while (i-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + i);
        try {
          Thread.sleep(500);
        } catch (InterruptedException ie) {
        }
      }
    }
  }

  public synchronized void isSyncA() {
    int i = 5;
    while (i-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }
    }
  }

  public synchronized void isSyncB() {
    int i = 5;
    while (i-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }
    }
  }


  public static synchronized void cSyncA() {
    int i = 5;
    while (i-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }
    }
  }

  public static synchronized void cSyncB() {
    int i = 5;
    while (i-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }
    }
  }

  public static void main(String[] args) {
    final TestSynchronized myt1 = new TestSynchronized();
    final TestSynchronized x = new TestSynchronized();
    final TestSynchronized y = new TestSynchronized();
    //同一个实例，不同的synchronized方法，对象锁有约束（同一个对象——对象锁）——a. x.isSyncA()与x.isSyncB()
        /*Thread test1 = new Thread(new Runnable() {
            public void run() {
                x.isSyncA();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                x.isSyncB();
            }
        }, "test2");
         */

    //不同的实例，同一个synchronized方法，对象锁没有约束（不同的对象——对象锁）——b. x.isSyncA()与y.isSyncA()
/*        Thread test1 = new Thread(new Runnable() {
            public void run() {
                x.isSyncA();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                y.isSyncA();
            }
        }, "test2");*/

    //不同的实例，不同的static synchronized方法，类锁具有约束（不同的对象，类锁）c. x.cSyncA()与y.cSyncB()
/*        Thread test1 = new Thread(new Runnable() {
            public void run() {
                x.cSyncA();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                y.cSyncB();
            }
        }, "test2");*/

    //不同的实例，相同的static synchronized方法，类锁具有约束（不同的对象，类锁）c1. x.cSyncA()与y.cSyncA()
    Thread test1 = new Thread(new Runnable() {
      public void run() {
        cSyncA();
      }
    }, "test1");
    Thread test2 = new Thread(new Runnable() {
      public void run() {
        cSyncA();
      }
    }, "test2");

    //与实例无关，对象锁和类锁互不影响——d. x.isSyncA()与Something.cSyncA()
        /*Thread test1 = new Thread(new Runnable() {
            public void run() {
                x.isSyncA();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                y.cSyncA();
            }
        }, "test2");*/

    test1.start();
    test2.start();

  }

}
