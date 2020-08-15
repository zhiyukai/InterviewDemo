package prictise.com.application1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.08.10 00:53
 * @Comment
 */
public class XieyiDialog extends Dialog {

  private static final String TAG = XieyiDialog.class.getSimpleName();
  private String mMessage;
  private int mImageId;
  private boolean mCancelable;
  private RotateAnimation mRotateAnimation;

  public XieyiDialog(@NonNull Context context, String message, int imageId) {
    this(context, R.style.XieyiDialog, message, imageId, false);
  }

  public XieyiDialog(@NonNull Context context, int themeResId, String message, int imageId,
      boolean cancelable) {
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
    setContentView(R.layout.window_tips_layout);
    // 设置窗口大小
    WindowManager windowManager = getWindow().getWindowManager();
    int screenWidth = windowManager.getDefaultDisplay().getWidth();
    int screenHeight = windowManager.getDefaultDisplay().getHeight();
    WindowManager.LayoutParams attributes = getWindow().getAttributes();
    attributes.alpha = 1f;
//    attributes.width = screenWidth / 3;
    attributes.width = screenWidth;
//    attributes.height = attributes.width;
    attributes.height = screenHeight;
    getWindow().setAttributes(attributes);
    setCancelable(mCancelable);

    findViewById(R.id.tv_agree).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isShowing()) {
          dismiss();
        }
      }
    });

//    TextView tv_loading = findViewById(R.id.tv_loading);
//    ImageView iv_loading = findViewById(R.id.iv_loading);
//    tv_loading.setText(mMessage);
//    iv_loading.setImageResource(mImageId);
//    iv_loading.measure(0, 0);
//    mRotateAnimation = new RotateAnimation(0, 360, iv_loading.getMeasuredWidth() / 2,
//        iv_loading.getMeasuredHeight() / 2);
//    mRotateAnimation.setInterpolator(new LinearInterpolator());
//    mRotateAnimation.setDuration(1000);
//    mRotateAnimation.setRepeatCount(-1);
//    iv_loading.startAnimation(mRotateAnimation);
  }

  @Override
  public void dismiss() {
//    mRotateAnimation.cancel();
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
