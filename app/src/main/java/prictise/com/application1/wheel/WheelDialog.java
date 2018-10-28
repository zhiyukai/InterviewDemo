package prictise.com.application1.wheel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.List;

import prictise.com.application1.R;

/**
 * @Author zsj
 * @Date 2018-10-27 22:34
 * @Commit
 */
public class WheelDialog extends Dialog {
    private String TAG = WheelDialog.class.getSimpleName();

    private List<String> mListItem = Arrays.asList("China", "American", "Japan", "England", "French", "Italy");
    private WheelView wheelView;

    public WheelDialog(@NonNull Context context) {
        super(context, R.style.WheelDialog);
        setOwnerActivity((Activity) context);
        setContentView(R.layout.dialog_wheel);
        init();
    }

    public void init() {
        wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setOffset(2);
        wheelView.setItems(mListItem);
        wheelView.setSelection(3);
        wheelView.setOnWheelPickerListener(new WheelView.OnWheelPickerListener() {
            @Override
            public void wheelSelect(int position, String content) {
                Log.e("TAG", position + "+" + content);
                dismiss();
            }
        });
    }

    public WheelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WheelDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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
}
