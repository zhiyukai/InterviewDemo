package prictise.com.application1.kotlin.rxjava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2019.11.11 16:00
 * @Comment
 */
public class A extends Activity {

  final String TAG = A.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initTest();
    initTest2();
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
}
