package com.ln.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by linan   on 2017/1/9.
 */
@Entity
public class RecordPiaDB {
    @Id
    private Long id;
    private String date; //日期
    private String time;//具体时间
    private String num;//次数
    private String location;//地点
    private String totalTime;//消耗时间
    private String commant;//评论
    private boolean YuFang;
    private boolean doublec;

    @Generated(hash = 701805340)
    public RecordPiaDB(Long id, String date, String time, String num,
            String location, String totalTime, String commant, boolean YuFang,
            boolean doublec) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.num = num;
        this.location = location;
        this.totalTime = totalTime;
        this.commant = commant;
        this.YuFang = YuFang;
        this.doublec = doublec;
    }
    @Generated(hash = 1711230900)
    public RecordPiaDB() {
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getTotalTime() {
        return this.totalTime;
    }
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
    public String getCommant() {
        return this.commant;
    }
    public void setCommant(String commant) {
        this.commant = commant;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getYuFang() {
        return this.YuFang;
    }
    public void setYuFang(boolean YuFang) {
        this.YuFang = YuFang;
    }
    public boolean getDoublec() {
        return this.doublec;
    }
    public void setDoublec(boolean doublec) {
        this.doublec = doublec;
    }


}
