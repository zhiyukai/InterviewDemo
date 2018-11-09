package prictise.com.application1.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.11.8 17:38
 * @comment
 */
public class DeleteLogAlarmReceiver extends BroadcastReceiver {
    private final String TAG = DeleteLogAlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.com.delete.log.file".equals(intent.getAction())) {
            LogcatUtils.showELog(TAG, "删除日志文件");
//            Util.deleteAllFiles(Constants.LOG_DIR_PATH);
//            Util.deleteAllFiles(Constants.CRASH_DIR_PATH);
        }
    }
}
