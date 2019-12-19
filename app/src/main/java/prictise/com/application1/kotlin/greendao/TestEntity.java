package prictise.com.application1.kotlin.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @Author zhisiyi
 * @Date 2019.12.04 11:41
 * @Comment
 */
@Entity
public class TestEntity {

    @Id(autoincrement = true)
    private Long id;
    private Integer addType = 1;
    @Index(unique = true)
    private String stuWechatId;


    private String text;
    private String time;
    private Long timeMill;

    @Generated(hash = 267521444)
    public TestEntity(Long id, Integer addType, String stuWechatId, String text,
            String time, Long timeMill) {
        this.id = id;
        this.addType = addType;
        this.stuWechatId = stuWechatId;
        this.text = text;
        this.time = time;
        this.timeMill = timeMill;
    }

    @Generated(hash = 1020448049)
    public TestEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAddType() {
        return this.addType;
    }

    public void setAddType(Integer addType) {
        this.addType = addType;
    }

    public String getStuWechatId() {
        return this.stuWechatId;
    }

    public void setStuWechatId(String stuWechatId) {
        this.stuWechatId = stuWechatId;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTimeMill() {
        return this.timeMill;
    }

    public void setTimeMill(Long timeMill) {
        this.timeMill = timeMill;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
            "id=" + id +
            ", addType=" + addType +
            ", stuWechatId='" + stuWechatId + '\'' +
            ", text='" + text + '\'' +
            ", time='" + time + '\'' +
            ", timeMill=" + timeMill +
            '}';
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
