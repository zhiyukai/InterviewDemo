package prictise.com.application1.designMode.sington;

/**
 * @Author zhisiyi
 * @Date 2019.09.18 14:46
 * @Comment
 */
public class A {

  private static Object mLock = new Object();

  private volatile static A mA;

  private A() {

  }

  public static A getInstance() {
    if (mA == null) {
      synchronized (mLock) {
        if (mA == null) {
          mA = new A();
        }
      }
    }
    return mA;
  }
}
