package prictise.com.application1.testGreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author zhisiyi
 * @Date 2019.11.21 11:41
 * @Comment
 */
@Entity
public class Student {

  @Id(autoincrement = true)
  Long stuId;

  @Index(unique = true)
  String stuNo;

  String stuName;
  String stuSex;
  String stuScore;
  @Generated(hash = 315497705)
  public Student(Long stuId, String stuNo, String stuName, String stuSex,
          String stuScore) {
      this.stuId = stuId;
      this.stuNo = stuNo;
      this.stuName = stuName;
      this.stuSex = stuSex;
      this.stuScore = stuScore;
  }
  @Generated(hash = 1556870573)
  public Student() {
  }
  public Long getStuId() {
      return this.stuId;
  }
  public void setStuId(Long stuId) {
      this.stuId = stuId;
  }
  public String getStuNo() {
      return this.stuNo;
  }
  public void setStuNo(String stuNo) {
      this.stuNo = stuNo;
  }
  public String getStuName() {
      return this.stuName;
  }
  public void setStuName(String stuName) {
      this.stuName = stuName;
  }
  public String getStuSex() {
      return this.stuSex;
  }
  public void setStuSex(String stuSex) {
      this.stuSex = stuSex;
  }
  public String getStuScore() {
      return this.stuScore;
  }
  public void setStuScore(String stuScore) {
      this.stuScore = stuScore;
  }
}
