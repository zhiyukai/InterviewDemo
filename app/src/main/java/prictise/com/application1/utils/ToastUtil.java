package prictise.com.application1.utils;

import android.widget.Toast;
import prictise.com.application1.MainApplication;

/**
 * @Author zsj
 * @Date 2018-11-08 23:19
 * @Commit
 */
public class ToastUtil {

  // 短时间显示Toast信息
  public static void showShort(String info) {
    Toast.makeText(MainApplication.getApplicationInstance(), info, Toast.LENGTH_SHORT).show();
  }

  // 长时间显示Toast信息
  public static void showLong(String info) {
    Toast.makeText(MainApplication.getApplicationInstance(), info, Toast.LENGTH_LONG).show();
  }
}
