package prictise.com.application1.thread;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ThreadLocalTest {
  public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      final int finalI = i;
      threadPool.submit(new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
          String data = new ThreadLocalTest().date(finalI);
          System.out.println(data);
        }
      });
    }
    threadPool.shutdown();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private String date(int seconds) {
    Date date = new Date(1000 * seconds);
    SimpleDateFormat dateFormat = ThreadSafeFormater.dateFormatThreadLocal.get();
    return dateFormat.format(date);
  }
}

@RequiresApi(api = Build.VERSION_CODES.O)
class ThreadSafeFormater {
  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
          = ThreadLocal.withInitial(new Supplier<SimpleDateFormat>() {
    @Override
    public SimpleDateFormat get() {
      return new SimpleDateFormat("mm:ss");
    }
  });
}
