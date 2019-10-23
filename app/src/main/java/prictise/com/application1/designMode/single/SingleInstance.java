package prictise.com.application1.designMode.single;

/**
 * @Author zhisiyi
 * @Date 2019.09.02 20:13
 * @Comment
 */
public class SingleInstance {

  private volatile static SingleInstance mSingleInstance;

  private final static Object mLock = new Object();

  private SingleInstance() {

  }

  public static SingleInstance getInstance() {
    if (mSingleInstance == null) {
      synchronized (mLock) {
        if (mSingleInstance == null) {
          mSingleInstance = new SingleInstance();
        }
      }
    }
    return mSingleInstance;
  }

}
