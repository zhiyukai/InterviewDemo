package prictise.com.application1.mvc.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import org.greenrobot.eventbus.EventBus;
import prictise.com.application1.R;
import prictise.com.application1.mvc.bean.EventTrackRecordInfoChanged;
import prictise.com.application1.mvc.model.TrackRecordInfo;
import prictise.com.application1.mvc.util.TrackRecordStatus;
import prictise.com.application1.mvc.view.TrackCtrlView;

/**
 * @Author zhisiyi
 * @Date 2019.08.30 03:16
 * @Comment https://blog.csdn.net/csdn_aiyang/article/details/69230397
 */
public class MVCActivity extends Activity implements TrackCtrlView.TrackCtrlViewListener {

  private TrackCtrlView trackCtrlView;
  private TrackRecordInfo trackRecordInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mvc_main);
    trackCtrlView = new TrackCtrlView(this, this);
    EventBus.getDefault().register(this);
    trackRecordInfo = TrackRecordInfo.loadTrackRecordInfo();
    trackCtrlView.setTrackRecordInfo(trackRecordInfo);
    trackRecordInfo.setListener(trackCtrlView);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Override
  public void trackStatusRequest(@Nullable TrackRecordStatus newStatus) {
    if (newStatus == TrackRecordStatus.Recording) {
      int trackId = 0;  //在数据库创建一条轨迹，并获取到数据库id
      trackRecordInfo = new TrackRecordInfo(trackId, TrackRecordStatus.Recording);
    } else if (newStatus == TrackRecordStatus.Paused) {
      if (trackRecordInfo != null) {
        trackRecordInfo.status = newStatus;
      }
    } else {
      trackRecordInfo = null;
    }
    TrackRecordInfo.changeTrackRecordInfo(trackRecordInfo);
  }

  public void onEventMainThread(EventTrackRecordInfoChanged event) {
    trackRecordInfo = event.info;
    trackCtrlView.setTrackRecordInfo(trackRecordInfo);
  }

}
