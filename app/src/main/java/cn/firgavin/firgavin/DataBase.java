package cn.firgavin.firgavin;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/25.
 */
public class DataBase {
    public ArrayList<DayItem> savedDayItemForMonth;
    private String yearStr,monthStr;
    private int yearInt,monthInt;
    private int sumOfThisMonth=0;

    /*  private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }*/

    public DataBase(String yearStr,String monthStr,int sumOfThisMonth){
        this.yearStr=yearStr;
        this.monthStr=monthStr;
        this.sumOfThisMonth=sumOfThisMonth;
        savedDayItemForMonth = new ArrayList<DayItem>(0);
    }

    public int getSumOfThisMonth() {
        return sumOfThisMonth;
    }

    public void setSumOfThisMonth(int sumOfThisMonth) {
        this.sumOfThisMonth = sumOfThisMonth;
    }

    public ArrayList<DayItem> getSavedDayItemForMonth() {
        return savedDayItemForMonth;
    }

    public void setSavedDayItemForMonth(ArrayList<DayItem> savedDayItemForMonth) {
        this.savedDayItemForMonth = savedDayItemForMonth;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public void setMonthInt(int monthInt) {
        this.monthInt = monthInt;
    }

    public int getYearInt() {
        return yearInt;
    }

    public void setYearInt(int yearInt) {
        this.yearInt = yearInt;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public String getYearStr() {
        return yearStr;
    }

    public void setYearStr(String yearStr) {
        this.yearStr = yearStr;
    }
}
