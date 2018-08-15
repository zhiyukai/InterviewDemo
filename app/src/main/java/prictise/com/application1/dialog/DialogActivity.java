package prictise.com.application1.dialog;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.cus.CusActivity;
import prictise.com.application1.cusview.CusViewActivity;
import prictise.com.application1.lifecycle.SingleInstanceActivity;
import prictise.com.application1.lifecycle.SingleTaskActivity;
import prictise.com.application1.lifecycle.SingleTopActivity;
import prictise.com.application1.lifecycle.StandardActivity;
import prictise.com.application1.multithreading.Consumer;
import prictise.com.application1.multithreading.Producter;
import prictise.com.application1.multithreading.SyncStack;
import prictise.com.application1.pictureMemory.PictureCompressActivity;
import prictise.com.application1.pictureMemory.PictureMemoryActivity;
import prictise.com.application1.rippeview.RippleActivity;

public class DialogActivity extends AppCompatActivity {

    private final String TAG = DialogActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_lanch_dialog)
    public void lanchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title")
                .setMessage("message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.create().show();
    }

    @OnClick(R.id.bt_lanch_service_dialog)
    public void lanchServiceDialog() {
        startService(new Intent(this, DialogService.class));
    }
}
