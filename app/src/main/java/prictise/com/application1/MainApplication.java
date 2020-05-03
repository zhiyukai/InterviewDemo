package prictise.com.application1;

import android.app.Application;
import android.provider.Settings;
import org.greenrobot.eventbus.EventBus;

/**
 * @author zhisiyi
 * @date 18.11.9 10:58
 * @comment
 */
public class MainApplication extends Application {
    private static MainApplication mainApplication;

    public static MainApplication getApplicationInstance() {
        return mainApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;

        // EventBus
        EventBus.builder().addIndex(new EventBusIndex()).installDefaultEventBus();

//String POINT_SERVICES_ORDER =
//            "settings put secure enabled_accessibility_services com.sunlands.chatagent/com.sunlands.chatagent.robot.ChatAgentService";
//    String ENABLE_SERVICE_PUT = "settings put secure accessibility_enabled 1"
//        val open = mutableListOf(Keys.POINT_SERVICES_ORDER, Keys.ENABLE_SERVICE_PUT)
//
//
//        Settings.Secure.putString(getContentResolver(),
//            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
//            "prictise.com.demo/prictise.com.application1.kotlin.rxjava.AccessServer");
//        Settings.Secure.putInt(getContentResolver(),
//            Settings.Secure.ACCESSIBILITY_ENABLED,1);
    }
}
