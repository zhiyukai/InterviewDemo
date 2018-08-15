package prictise.com.application1.multithreading;

/**
 * @author zhisiyi
 * @date 18.6.26 18:21
 * @comment 消费者
 */
public class Consumer implements Runnable {
    private SyncStack stack;

    public Consumer(SyncStack stack) {
        this.stack = stack;
    }

    public void run() {
        for (int i = 0; i < stack.pro().length; i++) {
            String consumer = stack.pop();
            System.out.println("消费了：" + consumer);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
