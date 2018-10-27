package prictise.com.application1.cus;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import prictise.com.application1.R;
import prictise.com.application1.cusview.GradientTextView;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.10.12 10:18
 * @comment 实现图片不停旋转
 */
public class RobotAnimationDialog extends Dialog {
    private String TAG = RobotAnimationDialog.class.getSimpleName();
    private ImageView mAwardBgIV;
    private ImageView mAwardIV;
    private TextView mShowCountTV;
    private GradientTextView mGradientTaskCountTV;
    private int mShowTaskCount;
    private ObjectAnimator mRotationAnimator;
    private int[] mColors = new int[3];

    public RobotAnimationDialog(@NonNull Context context) {
        super(context, R.style.RobotAnimationDialog);
        setOwnerActivity((Activity) context);
        setContentView(R.layout.dialog_robot_animation);
        init();
    }

    public void init() {
        initView();
        initAnimator();
        initDialogListener();
    }

    public RobotAnimationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RobotAnimationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }

    private void initDialogListener() {
        findViewById(R.id.fl_outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    mRotationAnimator.cancel();
                    dismiss();
                }
            }
        });
    }

    private void initAnimator() {
        mRotationAnimator = ObjectAnimator
                .ofFloat(mAwardBgIV,
                        "rotation", 0f, 360f);
        mRotationAnimator.setDuration(3000); // 3秒之内从不透明到透明再到0.8透明度
        mRotationAnimator.setInterpolator(new LinearInterpolator());
        mRotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mRotationAnimator.setRepeatMode(ObjectAnimator.RESTART);
        mRotationAnimator.start();
    }

    private void initView() {
        mAwardBgIV = findViewById(R.id.iv_award_bg);
        mAwardIV = findViewById(R.id.iv_award);
        mShowCountTV = findViewById(R.id.tv_show_count);
        mGradientTaskCountTV = findViewById(R.id.tv_gradient_task_count);
    }

    public void setAwardPictureBg(int awardBg) {
        mAwardBgIV.setImageResource(awardBg);
    }

    public void setAwardPicture(int awardPicture) {
        mAwardIV.setImageResource(awardPicture);
    }

    public void setTaskCount(int count) {
        mShowTaskCount = count;

        String showContent = "恭喜今日送餐达到 " + mShowTaskCount + " 次";
        int start = showContent.indexOf("到");
        int end = showContent.indexOf("次");

        LogcatUtils.showELog(TAG, "start = " + start);
        LogcatUtils.showELog(TAG, "end = " + end);

        SpannableString ss = new SpannableString(showContent);
        ss.setSpan(new RelativeSizeSpan(1), 0, start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        ss.setSpan(new RelativeSizeSpan(1.5f), start + 2, end - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.2f), end, end + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mShowCountTV.setText(ss);
    }

    public void setTaskTextViewColor(int color) {
        mShowCountTV.setTextColor(color);
    }

    public void setGradient(int[] colors) {
        for (int i = 0; i < 3; i++) {
            mColors[i] = colors[i];
        }

        mGradientTaskCountTV.setGradientColors(mColors);
    }

    public void setGradientTaskCount(int count) {
        mGradientTaskCountTV.setText(String.valueOf(count));
    }
}
