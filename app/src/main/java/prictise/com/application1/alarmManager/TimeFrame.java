package prictise.com.application1.alarmManager;

import java.io.Serializable;

public class TimeFrame implements Serializable {

  public Integer id;

  public String startTime;

  public String endTime;

  public int startRequestCode;

  public int endRequestCode;

  public TimeFrame() {
  }

  public TimeFrame(String startTime, String endTime, int startRequestCode, int endRequestCode) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.startRequestCode = startRequestCode;
    this.endRequestCode = endRequestCode;
  }

  public Integer getId() {
    return id;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "TimeFrame{" +
        "id=" + id +
        ", startTime='" + startTime + '\'' +
        ", endTime='" + endTime + '\'' +
        ", startRequestCode=" + startRequestCode +
        ", endRequestCode=" + endRequestCode +
        '}';
  }
}
