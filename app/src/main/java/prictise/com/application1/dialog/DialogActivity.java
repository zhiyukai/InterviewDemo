package prictise.com.application1.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhi
 * @Date 2019/8/9 23:57
 * @Describer
 */
public class DialogActivity extends Activity {

  private final String TAG = DialogActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dialog);
    ButterKnife.bind(this);
  }


  @OnClick(R.id.bt_lanch_xieyi_window_dialog)
  public void lanchXieyiDialogActivity() {
    XieyiDialog xieyiDialog = new XieyiDialog(this, "正在登录...", R.mipmap.ic_dialog_loading);
    xieyiDialog.show();
  }

  @OnClick(R.id.bt_lanch_dialog_style_activity)
  public void lanchDialogActivity() {
    startActivity(new Intent(this, DialogStyleActivity.class));
  }

  @OnClick(R.id.bt_lanch_window_dialog)
  public void lanchWindowDialog() {
    LoadingDialog loadingDialog = new LoadingDialog(this, "正在登录...", R.mipmap.ic_dialog_loading);
    loadingDialog.show();
  }

  @OnClick(R.id.bt_lanch_dialog)
  public void lanchDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("title")
        .setMessage("message")
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {

          }
        })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {

          }
        });
    builder.create().show();
  }

  @OnClick(R.id.bt_lanch_service_dialog)
  public void lanchServiceDialog() {
    startService(new Intent(this, DialogService.class));
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogcatUtils.showDLog(TAG, "onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogcatUtils.showDLog(TAG, "onResume");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogcatUtils.showDLog(TAG, "onRestart");
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    LogcatUtils.showDLog(TAG, "onNewIntent");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    LogcatUtils.showDLog(TAG, "onSaveInstanceState");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    LogcatUtils.showDLog(TAG, "onRestoreInstanceState");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogcatUtils.showDLog(TAG, "onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogcatUtils.showDLog(TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogcatUtils.showDLog(TAG, "onDestroy");
  }
}
