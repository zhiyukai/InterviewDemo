package prictise.com.application1.pictureMemory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import prictise.com.application1.R;

public class PictureMemoryActivity extends Activity implements View.OnClickListener {

    private ImageView mShowPictureIV;
    private TextView mPicturePathTV;
    private TextView mPictureMemoryTV;
    private Button mSdPictureBT;
    private Button mHdpiPictureBT;
    private Button mMdpiPictureBT;
    private Button mXhdpiPictureBT;
    private Button mXxhdpiPictureBT;
    private Button mXxxhdpiPictureBT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_momory);
        initView();
        initListener();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;// 屏幕宽度（像素）
        int heightPixels = metrics.heightPixels;// 屏幕高度（像素）
        float density = metrics.density;// 屏幕密度（0.75 / 1.0 / 1.5/  3.0）
        int densityDpi = metrics.densityDpi;// 屏幕密度DPI（120 / 160 / 240）

        Log.e("zhishaoju", "屏幕密度：" + density);
    }

    private void initView() {
        mShowPictureIV = (ImageView) findViewById(R.id.iv_show_picture);
        mPicturePathTV = (TextView) findViewById(R.id.tv_picture_path);
        mPictureMemoryTV = (TextView) findViewById(R.id.tv_picture_memory);
        mSdPictureBT = (Button) findViewById(R.id.bt_sd_picture);
        mHdpiPictureBT = (Button) findViewById(R.id.bt_hdpi_picture);
        mMdpiPictureBT = (Button) findViewById(R.id.bt_mdpi_picture);
        mXhdpiPictureBT = (Button) findViewById(R.id.bt_xhdpi_picture);
        mXxhdpiPictureBT = (Button) findViewById(R.id.bt_xxhdpi_picture);
        mXxxhdpiPictureBT = (Button) findViewById(R.id.bt_xxxhdpi_picture);
    }

    private void initListener() {
        mSdPictureBT.setOnClickListener(this);
        mHdpiPictureBT.setOnClickListener(this);
        mMdpiPictureBT.setOnClickListener(this);
        mXhdpiPictureBT.setOnClickListener(this);
        mXxhdpiPictureBT.setOnClickListener(this);
        mXxxhdpiPictureBT.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Bitmap bitmap = null;
        String path = null;

        switch (v.getId()) {
            case R.id.bt_sd_picture:
                path = "/mnt/sdcard/DCIM/Camera/p1.jpg";
                File file = new File(path);
                bitmap = BitmapFactory.decodeFile(path);
                mPictureMemoryTV.setText("内存大小：" + "\n 字节：" + bitmap.getByteCount() +
                        "\n M:" + bitmap.getByteCount() / 1024 / 1024 +
                        "\n 文件大小：" + file.length() + "图片的宽高：" + bitmap.getWidth()
                        + "," + bitmap.getHeight());

                Log.e("zhishaoju", "width = " + bitmap.getWidth());
                Log.e("zhishaoju", "height = " + bitmap.getHeight());
                mPicturePathTV.setText("路径：" + path);
                // 图片加载不出来，是因为当时没有设置权限
                // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                mShowPictureIV.setImageBitmap(bitmap);
                break;
            case R.id.bt_hdpi_picture:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p2);
                setmPictureMemoryTV(bitmap);
                mShowPictureIV.setImageBitmap(bitmap);
                break;
            case R.id.bt_mdpi_picture:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p1);
                setmPictureMemoryTV(bitmap);
                mShowPictureIV.setImageBitmap(bitmap);
                break;
            case R.id.bt_xhdpi_picture:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p3);
                setmPictureMemoryTV(bitmap);
                mShowPictureIV.setImageBitmap(bitmap);
                break;
            case R.id.bt_xxhdpi_picture:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p4);
                setmPictureMemoryTV(bitmap);
                mShowPictureIV.setImageBitmap(bitmap);
                break;
            case R.id.bt_xxxhdpi_picture:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p5);
                setmPictureMemoryTV(bitmap);
                mShowPictureIV.setImageBitmap(bitmap);
                break;
        }
    }

    private void setmPictureMemoryTV(Bitmap bitmap) {
        mPictureMemoryTV.setText("内存大小：" + "\n 字节：" + bitmap.getByteCount()
                + "图片的宽高：" + bitmap.getWidth()
                + "," + bitmap.getHeight());
    }
}
