package prictise.com.application1.utils;


import android.util.Log;

/**
 *
 */
public class LogcatUtils {

  public static void showLog(String message) {
    if (Constants.Debug) {
      Log.e("HiggsDynamics", message);
    }
  }

  public static void showELog(String tag, String message) {
    if (Constants.Debug) {
      Log.e(tag, message);
    }
  }

  public static void showDLog(String tag, String message) {
    if (Constants.Debug) {
      Log.d(tag, message);
    }
  }

}
