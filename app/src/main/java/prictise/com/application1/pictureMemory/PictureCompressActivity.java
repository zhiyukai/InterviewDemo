package prictise.com.application1.pictureMemory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;

public class PictureCompressActivity extends Activity {

    @BindView(R.id.iv_show_picture)
    ImageView mShowPictureIV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_compress);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {

    }

    @OnClick(R.id.bt_origin_picture)
    public void onOriginPicture(View view) {
        String path = "/mnt/sdcard/DCIM/Camera/IMG_20180519_090146.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        mShowPictureIV.setImageBitmap(bitmap);
    }

    @OnClick(R.id.bt_sampling_rate)
    public void onSampleRate(View view) {
        String path = "/mnt/sdcard/DCIM/Camera/IMG_20180519_090146.jpg";
        mShowPictureIV.setImageBitmap(sampleRate(path));
    }

    @OnClick(R.id.bt_quality)
    public void onQuality() {
        String path = "/mnt/sdcard/DCIM/Camera/IMG_20180519_090146.jpg";
        String path2 = "/mnt/sdcard/quality_compress.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        qualityCompress(bitmap, path2);
    }

    //    calculateInSampleSize
    private Bitmap sampleRate(String path) {
        //获取手机的分辨率
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        BitmapFactory.Options options = new BitmapFactory.Options();
        //当解析器为true时 能获取图片的宽和高 ，不会加载图片
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // 进行缩放比显示
        options.inSampleSize = calculateInSampleSize(options, width, height);
        //解析位图
        options.inJustDecodeBounds = false; // 当值为false 时 加载图片
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }

    /**
     * @param options
     * @param reqWidth  需要压缩成的宽度
     * @param reqHeight 需要压缩成的高度
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 计算出图片的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSamepleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halHeight = height / 2;
            final int halWidth = width / 2;
            while ((halHeight / inSamepleSize) >= reqHeight
                    && (halWidth / inSamepleSize) >= reqWidth) {
                inSamepleSize *= 2;
            }
        }
        return inSamepleSize;
    }

    /**
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     *
     * @param bitmap
     * @param path
     */
    private void qualityCompress(Bitmap bitmap, String path) {
        // 0-100 100为不压缩
        int quality = 20;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("zhishaoju", "quality compress");
    }

}
