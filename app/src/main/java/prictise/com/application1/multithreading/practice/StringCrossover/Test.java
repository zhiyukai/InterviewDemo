package prictise.com.application1.multithreading.practice.StringCrossover;

/**
 * @Author zhi
 * @Date 2019/7/30 23:31
 * @Describer 创建两个线程，其中一个输出1-52，另外一个输出A-Z。输出格式要求：12A 34B 56C 78D
 */
public class Test {

  Object lock = new Object();

  public Test() {
    Thread t1 = new Thread(new Out1Thread(lock));
    Thread t2 = new Thread(new Out2Thread(lock));
    t1.start();
    t2.start();
  }

  public Test(String other) {

  }

}
