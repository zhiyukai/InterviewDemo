package prictise.com.application1;

import android.app.Application;

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
    }
}
