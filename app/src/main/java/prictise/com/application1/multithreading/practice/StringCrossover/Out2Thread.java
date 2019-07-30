package prictise.com.application1.multithreading.practice.StringCrossover;

/**
 * @Author zhi
 * @Date 2019/7/30 23:37
 * @Describer
 */
public class Out2Thread implements Runnable {

  private final String TAG = Out2Thread.class.getSimpleName();

  private Object lock;

  public Out2Thread(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    for (int i = 0; i < chars.length; i++) {

      synchronized (lock) {
        System.out.println(chars[i]);
        lock.notify();
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
