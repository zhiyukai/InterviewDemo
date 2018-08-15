package prictise.com.application1.cusGroupView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author zhisiyi
 * @date 18.7.9 21:49
 * @comment
 */
public class CusGroupView extends LinearLayout{
    public CusGroupView(Context context) {
        super(context);
        initView(context);
    }

    public CusGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CusGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        
    }


}
