package prictise.com.application1.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.7.12 10:43
 * @comment 动画学习
 */
public class AnimationActivity extends Activity {

    @BindView(R.id.tv_animation)
    TextView mAnimationTV;
    @BindView(R.id.tv_property_animation_alpha)
    TextView mPropertyAlphaAnimationTV;
    @BindView(R.id.tv_property_animation_rotation)
    TextView mPropertyRotationAnimationTV;
    @BindView(R.id.tv_property_animation_scale)
    TextView mPropertyScaleAnimationTV;
    @BindView(R.id.tv_property_animation_translationX)
    TextView mPropertyTranslationXAnimationTV;
    @BindView(R.id.bt_property_animation_group)
    Button mPropertyGroupAnimationBT;
    @BindView(R.id.bt_property_animation_xml_group)
    Button mPropertyXmlGroupAnimationBT;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_animation)
    public void testClick() {
        Toast.makeText(this, "测试点击的位置", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_animation_single)
    public void singleAnimation() {
        // 步骤1:创建 需要设置动画的 视图View
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation);
        // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
        mAnimationTV.startAnimation(translateAnimation);
        // 步骤3:播放动画
    }

    @OnClick(R.id.bt_animation_group)
    public void groupAnimation() {
        Animation continueAnim = AnimationUtils.loadAnimation(this, R.anim.group_animation);
        mAnimationTV.startAnimation(continueAnim);
    }

    @OnClick(R.id.bt_property_animation_alpha)
    public void propertyAnimationAlpha() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mPropertyAlphaAnimationTV,
                        "alpha", 1f, 0f, 0.8f);
        objectAnimator.setDuration(3000); // 3秒之内从不透明到透明再到0.8透明度
        objectAnimator.start();
    }

    @OnClick(R.id.bt_property_animation_rotation)
    public void propertyAnimationRotation() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mPropertyRotationAnimationTV,
                        "rotation", 0f, 180f, 300f, 270f, 360f);
        objectAnimator.setDuration(8000); // 3秒之内从不透明到透明再到0.8透明度
        objectAnimator.start();
    }

    @OnClick(R.id.bt_property_animation_scale)
    public void propertyAnimationScale() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mPropertyScaleAnimationTV,
                        "scaleX", 1f, 0f, 1f);
        objectAnimator.setDuration(8000); // 3秒之内从不透明到透明再到0.8透明度
        objectAnimator.start();
    }

    @OnClick(R.id.bt_property_animation_translationX)
    public void propertyAnimationTranslationX() {
        float currentX = mPropertyTranslationXAnimationTV.getTextScaleX();
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mPropertyTranslationXAnimationTV,
                        "translationX",
                        currentX, -300f, currentX + 200f, currentX);
        objectAnimator.setDuration(8000); // 3秒之内从不透明到透明再到0.8透明度
        objectAnimator.start();
    }

    @OnClick(R.id.bt_property_animation_group)
    public void propertyAnimationGroup() {
        float currentX = mPropertyGroupAnimationBT.getTextScaleX();
        ObjectAnimator moveIn = ObjectAnimator
                .ofFloat(mPropertyGroupAnimationBT,
                        "translationX",
                        currentX, -300f, currentX + 200f, currentX);

        ObjectAnimator scaleX = ObjectAnimator
                .ofFloat(mPropertyGroupAnimationBT,
                        "scaleX", 1f, 0f, 1f);

        ObjectAnimator rotation = ObjectAnimator
                .ofFloat(mPropertyGroupAnimationBT,
                        "rotation", 0f, 180f, 300f, 270f, 360f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotation).with(scaleX).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }

    @OnClick(R.id.bt_property_animation_xml_group)
    public void propertyXmlAnimationGroup() {
        Animator animator = AnimatorInflater.loadAnimator(this,
                R.animator.group_property_animation);
        animator.setTarget(mPropertyXmlGroupAnimationBT);
        animator.start();
    }


}
