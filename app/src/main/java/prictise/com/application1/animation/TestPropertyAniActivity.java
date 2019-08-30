package prictise.com.application1.animation;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.08.20 19:51
 * @Comment
 */
public class TestPropertyAniActivity extends Activity {

  private static final String TAG = TestPropertyAniActivity.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    start();
  }

  public void start() {
    final ValueAnimator animator = ValueAnimator.ofFloat(60, 600);
    animator.setDuration(2000);
    animator.setRepeatCount(ValueAnimator.INFINITE);
//    animator.setRepeatMode(ValueAnimator.);
    animator.setRepeatCount(1);
    animator.setInterpolator(new LinearInterpolator());
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
//        // 获取到动画每次该变得float值，赋值给xpoint
//        XPoint = (Float)animation.getAnimatedValue();
//        // 通知view重绘
//        invalidate();
        float v = (Float) animation.getAnimatedValue();
        LogcatUtils.showDLog(TAG, "property animation.getAnimatedValue() = " + v);
      }
    });
    animator.start();
  }
}
