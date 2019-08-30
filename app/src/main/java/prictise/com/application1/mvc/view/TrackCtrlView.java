package prictise.com.application1.mvc.view;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import prictise.com.application1.R;
import prictise.com.application1.mvc.model.TrackRecordInfo;
import prictise.com.application1.mvc.util.TrackRecordStatus;

/**
 * @Author zhisiyi
 * @Date 2019.08.30 03:12
 * @Comment
 */
public class TrackCtrlView implements View.OnClickListener,TrackRecordInfo.ModelInterface {

  private ImageView btnStartTrack, btnStopTrack, btnPauseTrack;
  private TrackCtrlViewListener listener;
  private TrackRecordInfo trackRecordInfo;
  public TrackCtrlView(Activity activity, TrackCtrlViewListener listener){
    this.listener = listener;
    btnStartTrack = (ImageView) activity.findViewById(R.id.btnStartTrack);
    btnStopTrack = (ImageView) activity.findViewById(R.id.btnStopTrack);
    btnPauseTrack = (ImageView) activity.findViewById(R.id.btnPauseTrack);
    btnStartTrack.setOnClickListener(this);
    btnStopTrack.setOnClickListener(this);
    btnPauseTrack.setOnClickListener(this);
    btnPauseTrack.setOnClickListener(this);
  }
  /**
   * 将用户请求通知Controller
   */
  @Override
  public void onClick(View v) {
    switch(v.getId()){
      case R.id.btnStartTrack:
        if(listener != null){
          listener.trackStatusRequest(TrackRecordStatus.Recording);
        }
        break;

      case R.id.btnStopTrack:
        if(listener != null){
          listener.trackStatusRequest(TrackRecordStatus.Stoped);
        }
        break;

      case R.id.btnPauseTrack:
        if(listener != null){
          if(trackRecordInfo.status == TrackRecordStatus.Paused){
            listener.trackStatusRequest(TrackRecordStatus.Recording);
          }else{
            listener.trackStatusRequest(TrackRecordStatus.Paused);
          }
        }
        break;
      default:
        break;
    }
  }
  private void refreshView(){
    TrackRecordStatus trackStatus = trackRecordInfo == null ?
        TrackRecordStatus.Stoped : trackRecordInfo.status;
    if (trackStatus == TrackRecordStatus.Recording) {
      btnStartTrack.setVisibility(View.GONE);
      btnPauseTrack.setVisibility(View.VISIBLE);
      btnStopTrack.setVisibility(View.VISIBLE);
      btnPauseTrack.setImageResource(R.mipmap.btn_track_ctrl_pause);

    } else if (trackStatus == TrackRecordStatus.Paused) {
      btnStartTrack.setVisibility(View.GONE);
      btnPauseTrack.setVisibility(View.VISIBLE);
      btnStopTrack.setVisibility(View.VISIBLE);
      btnPauseTrack.setImageResource(R.mipmap.btn_track_ctrl_resume);

    } else {
      // TrackRecordStatus.Stoped
      btnStartTrack.setVisibility(View.VISIBLE);
      btnPauseTrack.setVisibility(View.GONE);
      btnStopTrack.setVisibility(View.GONE);
    }
  }
  public void setTrackRecordInfo(@Nullable TrackRecordInfo trackRecordInfo) {
    this.trackRecordInfo = trackRecordInfo;
    refreshView();
  }

  @Override
  public void success() {

  }

  @Override
  public void error() {

  }

  public interface TrackCtrlViewListener{
    /**
     * 用户点击按钮
     */
    public void trackStatusRequest(@Nullable TrackRecordStatus newStatus);
  }
}
