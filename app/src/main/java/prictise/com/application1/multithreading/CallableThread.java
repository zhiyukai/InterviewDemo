package prictise.com.application1.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author zhisiyi
 * @Date 2019.09.02 18:58
 * @Comment
 */
public class CallableThread implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(4000);
    return "你好";
  }

  public static void main(String[] args) {
    CallableThread callableThread = new CallableThread();
    FutureTask<String> task = new FutureTask<>(callableThread);

    new Thread(task).start();

    System.out.println("task.isDone() : " + task.isDone());
    String callValue = "";
    try {
      callValue = task.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("call value : " + callValue);

    ExecutorService executor = Executors.newFixedThreadPool(2);
    Executors.newCachedThreadPool();
    Executors.newScheduledThreadPool(3);
    Executors.newSingleThreadExecutor();
  }

}
