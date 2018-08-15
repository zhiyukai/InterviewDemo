package prictise.com.application1.multithreading;

/**
 * @author zhisiyi
 * @date 18.6.26 18:20
 * @comment 生产者
 */
public class Producter implements Runnable {
    private SyncStack stack;

    public Producter(SyncStack stack) {
        this.stack = stack;
    }

    public void run() {
        for (int i = 0; i < stack.pro().length; i++) {
            String producter = "产品" + i;
            stack.push(producter);
            System.out.println("生产了：" + producter);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
