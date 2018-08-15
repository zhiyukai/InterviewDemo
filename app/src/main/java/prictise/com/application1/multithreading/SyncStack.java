package prictise.com.application1.multithreading;

/**
 * @author zhisiyi
 * @date 18.6.26 18:19
 * @comment 生产者，消费者模式
 */
public class SyncStack {
    private String[] str = new String[10];

    private int index;

    //供生产者调用
    public synchronized void push(String sst) {
        if (index == str.length) {
            try {
                System.out.println("push wait()");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程
        str[index] = sst;
        index++;
    }

    //供消费者调用
    public synchronized String pop() {
        if (index == 0) {
            try {
                System.out.println("pop wait()");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        String product = str[index];
        index--;
        return product;

    }

    //就是定义一个返回值为数组的方法,返回的是一个String[]引用
    public String[] pro() {
        return str;
    }
}
