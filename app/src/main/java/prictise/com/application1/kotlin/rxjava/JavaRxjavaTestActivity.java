package prictise.com.application1.kotlin.rxjava;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import prictise.com.application1.MainApplication;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.11.11 16:00
 * @Comment
 */
public class JavaRxjavaTestActivity extends Activity {

  static final String TAG = JavaRxjavaTestActivity.class.getSimpleName();
  private ArrayList<String> mSdcardPics = new ArrayList<>();

  String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION
      , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA};

  List<String> mPermissionList;
  private final int mRequestCode = 100;//权限请求码
  AlertDialog mPermissionDialog;
  String mPackName = "prictise.com.application1";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPermissionList = new ArrayList<>();
    LogcatUtils.showDLog(TAG, "Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
//    if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
//      verifyStoragePermissions(this);
//    } else {
//      test5();
//    }
//    initTest();
//    initTest2();
//    testMaybe();
//    test3();
//    test4();
    test6();
  }

  private void test6() {
//    /** @hide */ public static final int LOG_ID_MAIN = 0;
//    /** @hide */ public static final int LOG_ID_RADIO = 1;
//    /** @hide */ public static final int LOG_ID_EVENTS = 2;
//    /** @hide */ public static final int LOG_ID_SYSTEM = 3;
//    /** @hide */ public static final int LOG_ID_CRASH = 4;
    Log.println(Log.INFO, TAG, "test6");
  }

  /**
   * 使用Observable+Glide下载图片到本地
   */
  private void test5() {
    LogcatUtils.showDLog(TAG, "test5");
    List<String> picList = new ArrayList<>();
    picList.add(
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2479815909,2383805735&fm=26&gp=0.jpg");
    picList.add(
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574179308384&di=ce65c3b84bee0501fc9475fa8a9a81c5&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180803%2F21%2F1533301608-rpZnFKAbzk.jpg");
    picList.add(
        "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3329142348,2886683430&fm=26&gp=0.jpg");

    Observable
        .fromArray(picList).subscribe(new Observer<List<String>>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(List<String> list) {
        LogcatUtils.showDLog(TAG, "onNext(List<String> list) = " + list.toString());
        LogcatUtils.showDLog(TAG, "当前线程 = " + Thread.currentThread());
        int len = list.size();
        LogcatUtils.showDLog(TAG, "len = " + len);
        for (int i = 0; i < len; i++) {
          String picPath = list.get(i);
          String postFix = picPath.substring(picPath.lastIndexOf("."));
          savePicture((System.currentTimeMillis() + postFix), picPath);
        }
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });

//    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//      @Override
//      public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//        emitter.onNext("Hello World");
//        emitter.onComplete();
//      }
//    });

//    Observable.create(new ObservableOnSubscribe<List<String>>() {
//      @Override
//      public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
//        //通过gilde下载得到file文件,这里需要注意android.permission.INTERNET权限
//        e.onNext(Glide.with(MainApplication.getApplicationInstance())
//            .load(imagePathList.get(currentViewPosition).getPath())
//            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//            .get());
//        e.onComplete();
//      }
//    }).subscribeOn(Schedulers.io())
//        .observeOn(Schedulers.newThread())
//        .subscribe(new Consumer<List<String>>() {
//          @Override
//          public void accept(List<String> al) throws Exception {
//            //获取到下载得到的图片，进行本地保存
//            File pictureFolder = Environment.getExternalStorageDirectory();
//            //第二个参数为你想要保存的目录名称
//            File appDir = new File(pictureFolder, "your_picture_save_path");
//            if (!appDir.exists()) {
//              appDir.mkdirs();
//            }
//            String fileName = System.currentTimeMillis() + ".jpg";
//            File destFile = new File(appDir, fileName);
//            //把gilde下载得到图片复制到定义好的目录中去
////            copy(file, destFile);
////
////            // 最后通知图库更新
////            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
////                Uri.fromFile(new File(destFile.getPath()))));
//          }
//        });
  }

  void test4() {
    Observable.concat(Observable.just(1, 2, 7, 8), Observable.just(3, 4, 5))
        .subscribe(new Consumer<Integer>() {
          @Override
          public void accept(@NonNull Integer integer) throws Exception {
            LogcatUtils.showDLog(TAG, "integer = " + integer);
          }
        });

  }


  private void initTest() {
    Observable.just("map/image/map.png")
        .map(new Function<String, Bitmap>() {
          @Override
          public Bitmap apply(String s) throws Exception {
            return null;
          }
        }).doOnComplete(new Action() {
      @Override
      public void run() throws Exception {

      }
    }).subscribe(new Observer<Bitmap>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Bitmap bitmap) {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  private void initTest2() {
//    Observable.just("","","")
//        .flatMap(new Function<String, ObservableSource<?>>() {
//          @Override
//          public ObservableSource<?> apply(String s) throws Exception {
//            return null;
//          }
//        }).subscribe();

//    Observable.just(System.currentTimeMillis())
//        .filter {
//      true
//    }
//                    .subscribeOn(Schedulers.io())
//        .map {
//      LogcatUtils.showDLog(TAG, "map")
//      arrayOf("a", "b", "c")
//    }
//                    .filter {
//      LogcatUtils.showDLog(TAG, "it.isEmpty() = " + it.isEmpty())
//      it.isNotEmpty() }
//                    .flatMap {
//      hashMapOf("uploadStates" to it)
//    }
//                    .subscribe({
//        LogcatUtils.showDLog(TAG, "checkSyncUploadState result $it")
//    }, {
//        LogcatUtils.showDLog(TAG, "checkSyncUploadState error ${it.localizedMessage}")
//    })

    Observable.just(System.currentTimeMillis())
        .filter(new Predicate<Long>() {
          @Override
          public boolean test(Long aLong) throws Exception {
            return aLong > 100;
          }
        })
        .map(new Function<Long, List<String>>() {
          @Override
          public List<String> apply(Long aLong) throws Exception {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            return list;
          }
        })
        .filter(new Predicate<List<String>>() {
          @Override
          public boolean test(List<String> strings) throws Exception {
            LogcatUtils.showDLog(TAG, "strings = " + strings.toString());
            return strings.size() > 0;
          }
        })
        .flatMap(new Function<List<String>, ObservableSource<?>>() {
          @Override
          public ObservableSource<?> apply(List<String> strings) throws Exception {
            HashMap<String, List<String>> h = new HashMap<>();
            h.put("test", strings);
            LogcatUtils.showDLog(TAG, "flatmap :" + h.toString());
            return Observable.fromIterable(h.entrySet());
          }
        })
        .subscribe(new Observer<Object>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Object o) {
            LogcatUtils.showDLog(TAG, "o = " + o.toString());
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  public void testMaybe() {
    Disposable d = Maybe.just("Hello World")
        .delay(10, TimeUnit.SECONDS, Schedulers.io())
        .subscribeWith(new DisposableMaybeObserver<String>() {
          @Override
          public void onStart() {
            System.out.println("Started");
          }

          @Override
          public void onSuccess(String value) {
            System.out.println("Success: " + value);
          }

          @Override
          public void onError(Throwable error) {
            error.printStackTrace();
            System.out.println("error = " + error.toString());

          }

          @Override
          public void onComplete() {
            System.out.println("Done!");
          }
        });
    d.isDisposed();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    d.dispose();
  }

  static HashMap<String, String> h = new HashMap<>();

  public void test3() {
    Observable.interval(60, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override
          public void accept(Disposable disposable) throws Exception {
            h.put("intervalNewFriends", "intervalNewFriends a");
          }
        })
//        .doOnSubscribe {
//      multiFinishCheckQueue?.add("intervalNewFriends")
        .flatMap(new Function<Long, ObservableSource<String>>() {
          @Override
          public ObservableSource<String> apply(Long aLong) throws Exception {
            return Observable.just(aLong + "张三");
          }
        })
        .subscribe(new Observer<String>() {
          @Override
          public void onSubscribe(Disposable d) {
            LogcatUtils.showDLog(TAG, "d = " + d);
          }

          @Override
          public void onNext(String s) {
            LogcatUtils.showDLog(TAG, "s = " + s);
          }

          @Override
          public void onError(Throwable e) {
            LogcatUtils.showDLog(TAG, "e = " + e);
          }

          @Override
          public void onComplete() {
            LogcatUtils.showDLog(TAG, "onComplete");
          }
        });

//  {
//    Logger.t(TAG).i("intervalNewFriends start $it")
//    heartBeat(it, deviceID)
//  }

  }

  //Glide保存图片
  public void savePicture(final String fileName, String url) {
    LogcatUtils
        .showDLog(TAG, "savePicture(final String fileName, String url) fileName = " + fileName
            + ", url = " + url);

    try {
      File f = Glide.with(MainApplication.getApplicationInstance())
          .load("")
          .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
          .get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

//    Glide.with(MainApplication.getApplicationInstance()).asBitmap().load(url).into(new Targ);

//    Glide.with(MainApplication.getApplicationInstance()).load(url).asBitmap().toBytes()
//        .into(new SimpleTarget<byte[]>() {
//          @Override
//          public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
//            Observable.just(bytes).subscribeOn(Schedulers.io()).subscribe(
//                new Observer<byte[]>() {
//                  @Override
//                  public void onSubscribe(Disposable d) {
//
//                  }
//
//                  @Override
//                  public void onNext(byte[] bytes) {
//                    try {
//                      saveFileToSD(fileName, bytes);
//                    } catch (Exception e) {
//                      e.printStackTrace();
//                    }
//                  }
//
//                  @Override
//                  public void onError(Throwable e) {
//
//                  }
//
//                  @Override
//                  public void onComplete() {
//
//                  }
//                });
//          }
//        });
  }

  private void saveFileToSD(String fileName, byte[] bytes) {
    LogcatUtils.showDLog(TAG, "saveFileToSD(String fileName, byte[] bytes) fileName = " + fileName);
    //如果手机已插入sd卡,且app具有读写sd卡的权限
    String filePath = Environment.getExternalStorageDirectory() + File.separator + "A1";
    File dir1 = new File(filePath);
    if (!dir1.exists()) {
      dir1.mkdirs();
    }
    LogcatUtils.showDLog(TAG, "filePath = " + filePath);
    String fn = filePath + "/" + fileName;
    //这里就不要用openFileOutput了,那个是往手机内存中写数据的
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(fn);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        output.write(bytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
      //将bytes写入到输出流中
      try {
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    LogcatUtils.showDLog(TAG, "pic path = " + fn);
    mSdcardPics.add(fn);
    //关闭输出流
//      Toast.makeText(MainApplication.getApplicationInstance(), "图片已成功保存到" + filePath,
//          Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    boolean hasPermissionDismiss = false;//有权限没有通过
    if (mRequestCode == requestCode) {
      for (int i = 0; i < grantResults.length; i++) {
        if (grantResults[i] == -1) {
          hasPermissionDismiss = true;
        }
      }
      //如果有权限没有被允许
      if (hasPermissionDismiss) {
        showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
      } else {
        //全部权限通过，可以进行下一步操作。。。

      }
    }
  }

  /**
   * 不再提示权限时的展示对话框
   */
  private void showPermissionDialog() {
    if (mPermissionDialog == null) {
      mPermissionDialog = new AlertDialog.Builder(this)
          .setMessage("已禁用权限，请手动授予")
          .setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              cancelPermissionDialog();

              Uri packageURI = Uri.parse("package:" + mPackName);
              Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
              startActivity(intent);
            }
          })
          .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //关闭页面或者做其他操作
              cancelPermissionDialog();

            }
          })
          .create();
    }
    mPermissionDialog.show();
  }

  //关闭对话框
  private void cancelPermissionDialog() {
    mPermissionDialog.cancel();
  }

  private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[]{
      Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
  private static final int REQUEST_EXTERNAL_STORAGE = 100;


  public void verifyStoragePermissions(Activity activity) {

    int permissionWrite = ActivityCompat
        .checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int permissionCheck = ContextCompat
        .checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      requestPermissions(
          new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
              Manifest.permission.READ_PHONE_STATE,
              Manifest.permission.SYSTEM_ALERT_WINDOW,
              Manifest.permission.ACCESS_FINE_LOCATION,
              Manifest.permission.ACCESS_COARSE_LOCATION}, 5);
    }

    if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat
          .requestPermissions(activity, PERMISSION_EXTERNAL_STORAGE, REQUEST_EXTERNAL_STORAGE);
    }
    test5();

//       if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
//        }
  }


  private void initPermission() {
    mPermissionList.clear();//清空没有通过的权限
    //逐个判断你要的权限是否已经通过
    for (int i = 0; i < permissions.length; i++) {
      if (ContextCompat.checkSelfPermission(this, permissions[i])
          != PackageManager.PERMISSION_GRANTED) {
        mPermissionList.add(permissions[i]);//添加还未授予的权限
      }
    }
    //申请权限
    if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
      LogcatUtils.showDLog(TAG, "mPermissionList.size() > 0");
      ActivityCompat.requestPermissions(this, permissions, mRequestCode);
    } else {
      //说明权限都已经通过，可以做你想做的事情去
      test5();
    }
  }


  public static void main(String[] args) {
//    test3();
  }

}
