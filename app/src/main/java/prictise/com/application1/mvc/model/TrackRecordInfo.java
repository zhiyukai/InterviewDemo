package prictise.com.application1.mvc.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import prictise.com.application1.mvc.bean.EventTrackRecordInfoChanged;
import prictise.com.application1.mvc.util.SpUtil;
import prictise.com.application1.mvc.util.TrackRecordStatus;

/**
 * @Author zhisiyi
 * @Date 2019.08.30 03:14
 * @Comment
 */
public class TrackRecordInfo {

  private static final Gson gson = new Gson();
  /**
   * 应该是保存轨迹数据库id，此demo中数据库操作不实现，暂时trackId一直为0
   */
  public int trackId;
  public TrackRecordStatus status;

  public TrackRecordInfo(int trackId, TrackRecordStatus status) {
    this.trackId = trackId;
    this.status = status;
  }

  private ModelInterface modelInterface;

  public void setListener(ModelInterface m) {
    modelInterface = m;
  }

  @NonNull
  public static TrackRecordInfo loadTrackRecordInfo() {
    String pref = SpUtil.getString(SpUtil.KEY_TRACK_RECORD_INFO, "");
    if (!TextUtils.isEmpty(pref)) {
      return gson.fromJson(pref, TrackRecordInfo.class);
    }
    return null;
  }

  public static void changeTrackRecordInfo(@Nullable TrackRecordInfo info) {
    SpUtil.saveString(SpUtil.KEY_TRACK_RECORD_INFO,
        info == null ? "" : gson.toJson(info));

    //model通过消息总线，通知View刷新
    EventBus.getDefault().post(new EventTrackRecordInfoChanged(info));
  }

  public void request(){
    modelInterface.success();
  }

  public interface ModelInterface {

    void success();

    void error();
  }
}
