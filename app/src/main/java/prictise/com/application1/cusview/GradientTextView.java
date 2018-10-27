package prictise.com.application1.cusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.10.12 14:45
 * @comment
 */
@SuppressLint("AppCompatCustomView")
public class GradientTextView extends TextView {

    private int[] mGradientColors = new int[3];

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);
        int startColor = tya.getColor(R.styleable.GradientTextView_startColor, 0xffff0000);
        int centerColor = tya.getColor(R.styleable.GradientTextView_centerColor, 0xffff0000);
        int endColor = tya.getColor(R.styleable.GradientTextView_endColor, 0xffff0000);

        mGradientColors[0] = startColor;
        mGradientColors[1] = centerColor;
        mGradientColors[2] = endColor;

        tya.recycle();
    }

    public GradientTextView(Context context,
                            AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed,
                            int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0, 0, 0, getHeight(),
                    mGradientColors, null,
                    Shader.TileMode.CLAMP));
        }
    }

    public void setGradientColors(int[] colors) {
        for (int i = 0; i < 3; i++) {
            mGradientColors[i] = colors[i];
        }

        requestLayout();
    }
}
