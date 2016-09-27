package cn.firgavin.firgavin;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/22.
 */
public class DayItem implements Serializable {
    private String day;
    private int dayInt;
    private String week;
    private String detail;
    private String year;
    private String mouth;

    public DayItem(){};

    public void setDayInt(int dayInt) {
        this.dayInt = dayInt;
    }

    public int getDayInt() {
        return dayInt;
    }

    public DayItem(String day, String week, String detail){
        this.day=day;
        this.week=week;
        this.detail=detail;
        //this.dayInt=Integer.parseInt(this.day);
    }

    public DayItem(String year,String mouth,String day,String week,String detail)
    {
        this.detail=detail;
        this.day=day;
        this.week=week;
        this.mouth=mouth;
        this.year=year;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getMouth() {
        return mouth;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
