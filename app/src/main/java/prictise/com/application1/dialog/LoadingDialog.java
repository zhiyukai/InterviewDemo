package prictise.com.application1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.08.10 00:53
 * @Comment
 */
public class LoadingDialog extends Dialog {

  private static final String TAG = LoadingDialog.class.getSimpleName();
  private String mMessage;
  private int mImageId;
  private boolean mCancelable;
  private RotateAnimation mRotateAnimation;

  public LoadingDialog(@NonNull Context context, String message, int imageId) {
    this(context, R.style.LoadingDialog, message, imageId, false);
  }

  public LoadingDialog(@NonNull Context context, int themeResId, String message, int imageId, boolean cancelable) {
    super(context, themeResId);
    mMessage = message;
    mImageId = imageId;
    mCancelable = cancelable;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogcatUtils.showDLog(TAG, "onCreate: ");
    initView();
  }

  private void initView() {
    setContentView(R.layout.dialog_loading);
    // 设置窗口大小
    WindowManager windowManager = getWindow().getWindowManager();
    int screenWidth = windowManager.getDefaultDisplay().getWidth();
    int screenHeight = windowManager.getDefaultDisplay().getHeight();
    WindowManager.LayoutParams attributes = getWindow().getAttributes();
    attributes.alpha = 0.3f;
//    attributes.width = screenWidth / 3;
    attributes.width = screenWidth;
//    attributes.height = attributes.width;
    attributes.height = screenHeight;
    getWindow().setAttributes(attributes);
    setCancelable(mCancelable);

    TextView tv_loading = findViewById(R.id.tv_loading);
    ImageView iv_loading = findViewById(R.id.iv_loading);
    tv_loading.setText(mMessage);
    iv_loading.setImageResource(mImageId);
    iv_loading.measure(0, 0);
    mRotateAnimation = new RotateAnimation(0, 360, iv_loading.getMeasuredWidth() / 2,
        iv_loading.getMeasuredHeight() / 2);
    mRotateAnimation.setInterpolator(new LinearInterpolator());
    mRotateAnimation.setDuration(1000);
    mRotateAnimation.setRepeatCount(-1);
    iv_loading.startAnimation(mRotateAnimation);
  }

  @Override
  public void dismiss() {
    mRotateAnimation.cancel();
    super.dismiss();
  }

  @Override
  public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      // 屏蔽返回键
      return mCancelable;
    }
    return super.onKeyDown(keyCode, event);
  }
}
