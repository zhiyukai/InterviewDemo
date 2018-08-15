package prictise.com.application1.FaceDetect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.FloatMath;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import prictise.com.application1.MainActivity;
import prictise.com.application1.R;

public class FaceDetectorActivity extends Activity {

    private static final int MAX_FACE = 20;
    private ImageView mImageView;

    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();

    private ImageView showImg;
    private String path;
    private String txtPath;
    private static final String LOG_TAG = "FaceHelper";
    private static final boolean DEBUG_ENABLE = true;
    private GridView mFaceDetectGV;
    private FaceDetectAdapter mFaceDetectAdapter;
    private ArrayList<Bitmap> mData = new ArrayList<>();
    private Camera camera;
    private Bitmap bitmap1;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);
        mFaceDetectGV = (GridView) findViewById(R.id.gv_face_detect);
        mFaceDetectAdapter = new FaceDetectAdapter(this);
        mFaceDetectGV.setAdapter(mFaceDetectAdapter);
        initPermission();
//        mImageView = new ImageView(this);
//        setContentView(mImageView);
        showImg = (ImageView) findViewById(R.id.iv_face_detect);
        //6.0获取多个权限
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(FaceDetectorActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(FaceDetectorActivity.this, "已经授权", Toast.LENGTH_LONG).show();
            //有权限，直接做自己想做得
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.jpg";
            boolean fileExist = fileIsExists(path);
            initCamera();
            readImg(showImg);

        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(FaceDetectorActivity.this, permissions, 1);
        }

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.jpg";
        boolean fileExist = fileIsExists(path);
        if (fileExist) {
            //存在该图片就显示出来
            readImg(showImg);
        }

        txtPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Logcat.txt";
        boolean txtfileExist = fileIsExists(txtPath);
        if (txtfileExist) {
            //存在该文件这里仅仅弹出提示
            showToast("路径正确 文件存在：" + txtPath);
        }

    }

    private Bitmap getLocalPicture() {
        // ContentProvider から最新の画像ファイルを取得する
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        String sortOrder = Media.DATE_TAKEN + " DESC";
        Cursor c = getContentResolver().query(uri, null, null, null, sortOrder);
        c.moveToFirst();
        // ファイル名を取得
        int index = c.getColumnIndex(MediaStore.MediaColumns.DATA);
        String path = c.getString(index);
        // Bitmap を取得
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(FaceDetectorActivity.this, permissions[i]);
                    if (showRequestPermission) {
                        showToast("权限未申请");
                    } else {
                        //有权限，直接做自己想做得
                        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.jpg";
                        boolean fileExist = fileIsExists(path);
                        readImg(showImg);

                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showToast(String string) {
        Toast.makeText(FaceDetectorActivity.this, string, Toast.LENGTH_LONG).show();
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void readImg(View view) {
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap bitmap = getLocalPicture();
//        showImg.setImageBitmap(genFaceBitmap(bitmap));
//        if (bitmap1 != null) {
//            genFaceBitmapList(bitmap1);
//            mFaceDetectAdapter.setData(mData);
//        }
        genFaceBitmapList(bitmap);
        mFaceDetectAdapter.setData(mData);
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            saveImage(mData.get(i));
        }
    }
    //

    private void initCamera() {
        mSurfaceView = (SurfaceView) findViewById(R.id.preview);
        mHolder = mSurfaceView.getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                parameters.setPictureFormat(PixelFormat.JPEG);
                parameters.set("jpeg-quality", 85);
                camera.setParameters(parameters);
                try {
                    camera.setPreviewDisplay(mHolder);
                    camera.setPreviewCallback(new Camera.PreviewCallback() {
                        @Override
                        public void onPreviewFrame(byte[] data, Camera camera) {
                            Log.e("genFaceBitmap", "onPreviewFrame");
                            camera.setOneShotPreviewCallback(null);
                            //处理data
                            Camera.Size previewSize = camera.getParameters().getPreviewSize();//获取尺寸,格式转换的时候要用到
                            BitmapFactory.Options newOpts = new BitmapFactory.Options();
                            newOpts.inJustDecodeBounds = true;
                            YuvImage yuvimage = new YuvImage(
                                    data,
                                    ImageFormat.NV21,
                                    previewSize.width,
                                    previewSize.height,
                                    null);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            yuvimage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, baos);// 80--JPG图片的质量[0-100],100最高
                            byte[] rawImage = baos.toByteArray();
                            //将rawImage转换成bitmap
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.RGB_565;
                            Bitmap bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
                            bitmap1 = convertGreyImg(bitmap);
                            Log.e(LOG_TAG, "genFaceBitmap() ");
                        }
                    });

                    //开启预览
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera != null) {
                    camera.release();
                    camera = null;
                }
            }
        });
    }


    public void saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将彩色图转换为灰度图
     *
     * @param img 位图
     * @return 返回转换好的位图
     */
    public Bitmap convertGreyImg(Bitmap img) {
        int width = img.getWidth();         //获取位图的宽
        int height = img.getHeight();       //获取位图的高

        int[] pixels = new int[width * height]; //通过位图的大小创建像素点数组

        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }


    public void genFaceBitmapList(Bitmap sourceBitmap) {

        if (!checkBitmap(sourceBitmap, "genFaceBitmap()")) {
            return;
        }

        // default algorithm of face detecting of Android can only handle
        // RGB_565 bitmap, so copy it by RGB_565 here.
        Bitmap cacheBitmap = sourceBitmap.copy(Bitmap.Config.RGB_565, false);

        // gives up strong reference here. because this method may be
        // time-consuming, so maybe run in work thread usually, we give up the
        // strong reference of the source bitmap after it copied, so that it can
        // be recycled and GC in another thread.
        sourceBitmap = null;

        if (DEBUG_ENABLE) {
            Log.i(LOG_TAG,
                    "genFaceBitmap() : source bitmap width - "
                            + cacheBitmap.getWidth() + " , height - "
                            + cacheBitmap.getHeight());
        }

        int cacheWidth = cacheBitmap.getWidth();
        int cacheHeight = cacheBitmap.getHeight();

        // default algorithm of face detecting of Android can only handle the
        // bitmap that width is even, so we give up the 1 pixel from right if
        // not even.
        if (cacheWidth % 2 != 0) {
            if (0 == cacheWidth - 1) {
                if (DEBUG_ENABLE) {
                    Log.e(LOG_TAG,
                            "genFaceBitmap() : source bitmap width is only 1 , return null.");
                }
                return;
            }
            final Bitmap localCacheBitmap = Bitmap.createBitmap(cacheBitmap, 0,
                    0, cacheWidth - 1, cacheHeight);
            cacheBitmap.recycle();
            cacheBitmap = localCacheBitmap;
            --cacheWidth;

            if (DEBUG_ENABLE) {
                Log.i(LOG_TAG,
                        "genFaceBitmap() : source bitmap width - "
                                + cacheBitmap.getWidth() + " , height - "
                                + cacheBitmap.getHeight());
            }
        }

        final Face[] faces = new Face[MAX_FACE];
        final int facefound = new FaceDetector(cacheWidth, cacheHeight, MAX_FACE)
                .findFaces(cacheBitmap, faces);

        for (int i = 0; i < facefound; i++) {
            final PointF p = new PointF();
            faces[i].getMidPoint(p);
            float dis = faces[i].eyesDistance();
            if (DEBUG_ENABLE) {
                Log.i(LOG_TAG, "genFaceBitmap() : facefound - " + facefound);
                Log.i(LOG_TAG, "genFaceBitmap() : dis - " + dis);
            }
            if (DEBUG_ENABLE) {
                Log.i(LOG_TAG, "genFaceBitmap() : pointF x- " + p.x);
                Log.i(LOG_TAG, "genFaceBitmap() : pointF y- " + p.y);
            }
            if (0 == facefound) {
                if (DEBUG_ENABLE) {
                    Log.e(LOG_TAG, "genFaceBitmap() : no face found , return null.");
                }
                return;
            }


            if (DEBUG_ENABLE) {
                Log.i(LOG_TAG,
                        "getFaceBitmap() : confidence - " + faces[0].confidence());
                Log.i(LOG_TAG, "genFaceBitmap() : face center x - " + p.x
                        + " , y - " + p.y);
            }
            final int faceX = (int) p.x;
            final int faceY = (int) p.y;
            if (DEBUG_ENABLE) {
                Log.i(LOG_TAG, "genFaceBitmap() : int faceX - " + faceX
                        + " , int faceY - " + faceY);
                Log.i(LOG_TAG, "genFaceBitmap() : int width - " + cacheWidth
                        + " , int height - " + cacheHeight);
            }

            // compute an area that face in middle of it.
            int startX, startY, width, height;
            if (faceX <= dis * 1.2) {
                startX = 0;
            } else {
                startX = (int) (faceX - dis * 1.2);
            }
            width = (int) ((dis * 2.5));

            if (faceY <= faceY - dis) {
                startY = 0;
            } else {
                startY = (int) (faceY - dis);
            }
            height = (int) (dis * 3.2);
            if (width > cacheWidth)
                width = cacheWidth;
            if (height > cacheHeight)
                height = cacheHeight;

            final Bitmap result = Bitmap.createBitmap(cacheBitmap, startX, startY,
                    width, height);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Log.i(LOG_TAG, "genFaceBitmap() : 大小 = " + result.getAllocationByteCount());
            }
            mData.add(result);
        }
        cacheBitmap.recycle();
    }

    private static boolean checkBitmap(final Bitmap bitmap,
                                       final String debugInfo) {
        if (null == bitmap || bitmap.isRecycled()) {
            if (DEBUG_ENABLE) {
                Log.e(LOG_TAG, debugInfo
                        + " : check bitmap , is null or is recycled.");
            }
            return false;
        }
        if (0 == bitmap.getWidth() || 0 == bitmap.getHeight()) {
            if (DEBUG_ENABLE) {
                Log.e(LOG_TAG, debugInfo
                        + " : check bitmap , width is 0 or height is 0.");
            }
            return false;
        }
        return true;
    }

    private void initPermission() {
        String permissions[] = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.CAMERA,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

}
