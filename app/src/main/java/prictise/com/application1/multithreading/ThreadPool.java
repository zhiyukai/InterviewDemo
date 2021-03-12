package prictise.com.application1.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhisiyi
 * @date 18.6.20 0:02
 * @comment
 */
public class ThreadPool {
  public static void cachedThreadPool() {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      final int index = i;
      try {
        Thread.sleep(index * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      cachedThreadPool.execute(new Runnable() {

        @Override
        public void run() {
          System.out.println(index);
        }
      });
    }
  }

  void method() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
  }

  public static void main(String[] args) {
    /*ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);
    ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, queue,
            new ThreadPoolExecutor.AbortPolicy());
    for (int i = 0; i < 20; i++) {
      MyTask myTask = new MyTask(i, pool);
      pool.execute(myTask);
      System.out.println("线程池中的线程数目：" + pool.getPoolSize()
              + ",队列中等待执行的任务数量：" + pool.getQueue().size());

    }
    pool.shutdown();
    */

    ThreadPoolExecutor pool =
            new ThreadPoolExecutor(0, 2, 200, TimeUnit.MILLISECONDS,
                    new SynchronousQueue<Runnable>());

    pool.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("任务1");
        while (true) {

        }
      }
    });

    pool.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("任务2");
        System.out.println(Thread.currentThread().getName());
      }
    });

    pool.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("任务3");
        System.out.println(Thread.currentThread().getName());
      }
    });

  }

  static class MyTask implements Runnable {

    private int taskNum;
    private ThreadPoolExecutor pool;

    public MyTask(int num, ThreadPoolExecutor pool) {
      this.taskNum = num;
      this.pool = pool;
    }

    public void run() {
      System.out.println("正在执行task " + taskNum);
      try {
        Thread.sleep(taskNum * 1200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("task " + taskNum + "执行完毕");
      System.out.println("已执行完的任务数目：" + pool.getCompletedTaskCount());
    }

  }
}
