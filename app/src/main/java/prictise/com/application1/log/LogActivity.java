package prictise.com.application1.log;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.9.4 15:08
 * @comment
 */
public class LogActivity extends Activity {
    private final String TAG = LogActivity.class.getSimpleName();
    private int REQUEST_WRITE = 100;
    String filePath = "/sdcard/test/log.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);

        //判断是否6.0以上的手机   不是就不用
        if (Build.VERSION.SDK_INT >= 23) {
//判断是否有这个权限
            if (ContextCompat.checkSelfPermission(LogActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//2、申请权限: 参数二：权限的数组；参数三：请求码
                ActivityCompat.requestPermissions(LogActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE);
            } else {
                writeToFile(filePath, 's', "msg", "msg1");
            }
        }


    }

    //判断授权的方法  授权成功直接调用写入方法  这是监听的回调
    //参数  上下文   授权结果的数组   申请授权的数组
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            writeToFile(filePath, 's', "msg", "msg1");
        }

    }

    @OnClick(R.id.bt_collect_log)
    public void collectLog() {
        LogcatUtils.showELog(TAG, "log1");
//        try {
//            File fp = new File(filePath);
//            if (!fp.exists()) {
//                fp.createNewFile();
//            }
//            PrintWriter pfp = new PrintWriter(fp);
//            pfp.print("msg");
//            pfp.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writeToFile(filePath, 's', "msg", "msg1");
        try {
            String data = "hello,world! Zhang Phil @ CSDN";
            byte[] buffer = data.getBytes();
            FileOutputStream fos = new FileOutputStream(filePath);
            // 开始写入数据到这个文件。
            fos.write(buffer, 0, buffer.length);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogcatUtils.showELog(TAG, "log2");
    }

    private void writeToFile(String logPath, char type, String tag, String msg) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);//日期格式;
//        String fileName = logPath + "/deliverrobot"+ ".log";//log日志名，使用时间命名，保证不重复
//        String log = dateFormat.format(date) + " " + type + " " + tag + " " + msg + "\n";//log日志内容，可以自行定制
        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }
        FileOutputStream fos = null;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(logPath, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
