package prictise.com.application1.kotlin.rxjava

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import prictise.com.application1.utils.LogcatUtils

/**
 * @Author zhisiyi
 * @Date 2019.11.12 15:38
 * @Comment
 */
class AccessServer : AccessibilityService() {

    val TAG = AccessServer::class.simpleName

    override fun onCreate() {
        LogcatUtils.showDLog(TAG, "oncreate");
    }

    override fun onInterrupt() {
    }

    override fun onServiceConnected() {
        LogcatUtils.showDLog(TAG, "onServiceConnected");
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        LogcatUtils.showDLog(TAG, "onAccessibilityEvent $event");
//        LogcatUtils.showDLog(TAG, "event = $event");
//        AccessibilityServiceInfo.FLAG_ENABLE_ACCESSIBILITY_VOLUME
//        Log.i(TAG, "event = $event")
        var textList = event?.text;
        for (index in 0 until textList!!.size) {
            LogcatUtils.showDLog(TAG, "textList[$index] = " + textList[index]);
        }
        var type = event?.eventType
        when (type) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                LogcatUtils.showDLog(TAG, "aaa");
            }
        }

    }

}