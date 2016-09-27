package cn.firgavin.firgavin;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    private static boolean initFlag = false; //标识第一次初始化

    private ArrayList<Object> daylist = new ArrayList<Object>();
    private ArrayList<Object> pointlist = new ArrayList<Object>();
    private ArrayList<Object> data = new ArrayList<Object>();
    private String clickYear,clickMouth,tempYearMonth;
    private int sumOfMonth;
    private String fileName,tempFile;

    public StaticStorage publicStorage = new StaticStorage();//静态类全局变量

    //private ArrayList<DataBase> AllContent = new ArrayList<DataBase>();
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置Mouth和Year的点击事件
        Spinner spinnermouth = (Spinner) findViewById(R.id.choseMouth);
        spinnermouth.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[]  mouth = getResources().getStringArray(R.array.mouthList);
                Toast.makeText(MainActivity.this,"点击的是："+mouth[position],Toast.LENGTH_SHORT).show();
                clickMouth=mouth[position];
                publicStorage.SpinnerMonth=clickMouth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        Spinner spinneryear = (Spinner) findViewById(R.id.choseYear);
        spinneryear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] year = getResources().getStringArray(R.array.yearList);
                Toast.makeText(MainActivity.this,"点击的是："+year[position],Toast.LENGTH_SHORT).show();
                clickYear=year[position];
                publicStorage.SpinnerYear=clickYear;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        ImageView longSquareClick = (ImageView) findViewById(R.id.longSquare);
        longSquareClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               /* DayItem d = new DayItem();
                Intent intent = new Intent(MainActivity.this, ClickAddItemPoint.class);
                //intent.putExtra("extra_data", s);
                intent.putExtra("week", d.getWeek());
                intent.putExtra("day", d.getDay());
                intent.putExtra("year", clickYear);
                intent.putExtra("month", clickMouth);*/
                Intent intent = new Intent(MainActivity.this, ClickLongSquare.class);
                startActivity(intent);
            }
        });
        //设置+加号添加的点击事件
        ImageView addPint = (ImageView) findViewById(R.id.addPuls);
        addPint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddItemPoint d = (AddItemPoint) data.get(position);此处应该自动寻找到今天
                DayItem d = new DayItem();
                Intent intent = new Intent(MainActivity.this, ClickAddItemPoint.class);
                //intent.putExtra("extra_data", s);
                intent.putExtra("week", d.getWeek());
                intent.putExtra("day", d.getDay());
                intent.putExtra("year", clickYear);
                intent.putExtra("month", clickMouth);
                startActivity(intent);
            }
        });

        if(!initFlag) {
            initDays();
            publicStorage.AllMonth = data;
        }
        ListViewApater adapter = new ListViewApater(MainActivity.this,publicStorage.AllMonth);
        ListView listView = (ListView)findViewById(R.id.main_list_view);
        listView.setAdapter(adapter);

        //initListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Object ob = data.get(position);
                if(ob instanceof DayItem) {
                    DayItem d = (DayItem) data.get(position);
                    String s = d.getDetail();
                    Intent intent = new Intent(MainActivity.this, ClickDayItem.class);
                    intent.putExtra("extra_data", s);
                    intent.putExtra("week", d.getWeek());
                    intent.putExtra("day", d.getDay());
                    intent.putExtra("year", clickYear);
                    intent.putExtra("month", clickMouth);
                    intent.putExtra("positionOfPoint",position);
                    startActivity(intent);
                }else if(ob instanceof AddItemPoint){
                    AddItemPoint d = (AddItemPoint) data.get(position);
                    //String s = d.getDetail();
                    Intent intent = new Intent(MainActivity.this, ClickAddItemPoint.class);
                    //intent.putExtra("extra_data", s);
                    intent.putExtra("week", d.getWeek());
                    intent.putExtra("day", d.getDay());
                    intent.putExtra("year", clickYear);
                    intent.putExtra("month", clickMouth);
                    intent.putExtra("positionOfPoint",position);
                    startActivity(intent);
                }
            }
        });
    }

    /*private void saveObject(String name,Object sod){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = this.openFileOutput(name, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(sod);
            //System.out.println("存储对象成功");
        } catch (Exception e) {
            //System.out.println("存储对象出错");
            e.printStackTrace();//保存文件产生异常
        }finally {
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();//fos文件流关闭异常
                }
            }
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e){
                    e.printStackTrace();//oos对象输出流关闭异常
                }
            }
        }
    }*/

    /*private Object getObject(String name){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = this.openFileInput(name);
            ois = new ObjectInputStream(fis);
            //System.out.println("读取对象成功");
            return ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //System.out.println("储存文件没有找到");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("读取对象失败");
        } finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        if(ois != null){
            try{
                ois.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }*/

    /*private String getCurDate(){
        SimpleDateFormat formatter = new SimpleDateFormat(("yyyy-MM-dd"));
        //Date curDate = new Date(System.currentTimeMillis());
        //String str = formatter.format(curDate);
        String curDate = formatter.format(new java.util.Date());
        //System.out.println(curDate);
        return curDate;
    }

    private int getCalendar(String yearMonth){
        Calendar calendar = new GregorianCalendar();//声明使用Calendar函数

        //SimpleDateFormat sdf = new SimpleDateFormat("",locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat(("yyyyMM"));
        //Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(sdf.parse(yearMonth));
            calendar.setTime(sdf.parse(yearMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int num2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //System.out.println(num2);

        return num2;
    }

    private void initListView(){
        String changeMonth="";
        *//*switch (clickMouth){
            case "January":changeMonth="01";break;
            case "February":changeMonth="02";break;
            case "March":changeMonth="03";break;
            case "April":changeMonth="04";break;
            case "May":changeMonth="05";break;
            case "June":changeMonth="06";break;
            case "July":changeMonth="07";break;
            case "August":changeMonth="08";break;
            case "September":changeMonth="09";break;
            case "October":changeMonth="10";break;
            case "November":changeMonth="11";break;
            case "December":changeMonth="12";break;
        }*//*
        if(clickMouth!=null){
            if(clickMouth.equals("January")) changeMonth="01";
            if(clickMouth.equals("February")) changeMonth="02";
            if(clickMouth.equals("March")) changeMonth="03";
            if(clickMouth.equals("April")) changeMonth="04";
            if(clickMouth.equals("May")) changeMonth="05";
            if(clickMouth.equals("June")) changeMonth="06";
            if(clickMouth.equals("July")) changeMonth="07";
            if(clickMouth.equals("August")) changeMonth="08";
            if(clickMouth.equals("September")) changeMonth="09";
            if(clickMouth.equals("October")) changeMonth="10";
            if(clickMouth.equals("November")) changeMonth="11";
            if(clickMouth.equals("December")) changeMonth="12";
        }
        //fileName=clickYear+"_"+changeMonth+".dat";
        tempYearMonth = clickYear+changeMonth;
        sumOfMonth=getCalendar(tempYearMonth);
        int searchFlag = -1;
        //判断一下这个月是不是被创建了
        if(!publicStorage.AllMonth.isEmpty()){
            for(int i=0;i<publicStorage.AllMonth.size();i++){
                if((publicStorage.AllMonth.get(i).getYearStr().equals(publicStorage.SpinnerYear))&&(publicStorage.AllMonth.get(i).getMonthStr().equals(publicStorage.SpinnerMonth))){
                    searchFlag = i;
                    break;
                }
            }
        }
        if (searchFlag != -1){
            //找到了创建的
            DataBase temp = publicStorage.AllMonth.get(searchFlag);
            ArrayList<Object> tempList = new ArrayList<Object>();

            for (int i =0;i<temp.getSumOfThisMonth();i++){
                boolean searching = true;
                for(int j=0;j<temp.savedDayItemForMonth.size();j++){
                    if(temp.savedDayItemForMonth.get(j).getDayInt()==(i+1)){
                        tempList.add(temp.savedDayItemForMonth.get(j));
                        searching= false;
                    }
                }
                if (searching) {
                    String date = clickYear + "-" + clickMouth + "-" + Integer.toString(i);
                    AddItemPoint newInit = new AddItemPoint(R.drawable.add_dot_btn, getWeekOfMe(date), Integer.toString(i));
                    tempList.add(newInit);
                }
            }

            ListViewApater adapter = new ListViewApater(MainActivity.this,tempList);
            ListView listView = (ListView)findViewById(R.id.main_list_view);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {

                    Object ob = data.get(position);
                    if(ob instanceof DayItem) {
                        DayItem d = (DayItem) data.get(position);
                        String s = d.getDetail();
                        Intent intent = new Intent(MainActivity.this, ClickDayItem.class);
                        intent.putExtra("extra_data", s);
                        intent.putExtra("week", d.getWeek());
                        intent.putExtra("day", d.getDay());
                        intent.putExtra("year", clickYear);
                        intent.putExtra("month", clickMouth);
                        startActivity(intent);
                    }else if(ob instanceof AddItemPoint){
                        AddItemPoint d = (AddItemPoint) data.get(position);
                        //String s = d.getDetail();
                        Intent intent = new Intent(MainActivity.this, ClickAddItemPoint.class);
                        //intent.putExtra("extra_data", s);
                        intent.putExtra("week", d.getWeek());
                        intent.putExtra("day", d.getDay());
                        intent.putExtra("year", clickYear);
                        intent.putExtra("month", clickMouth);
                        startActivity(intent);
                    }
                }
            });
        }else {
            if (searchFlag == -1) {
                //没有找到，重新创建
                ArrayList<Object> tempList = new ArrayList<Object>();
                DataBase newMonth = new DataBase(publicStorage.SpinnerYear, publicStorage.SpinnerMonth, sumOfMonth);
                for(int i=0;i<sumOfMonth;i++){
                    String date = clickYear + "-" +clickMouth+"-"+ Integer.toString(i);
                    AddItemPoint newInit = new AddItemPoint(R.drawable.add_dot_btn,getWeekOfMe(date),Integer.toString(i));
                    tempList.add(newInit);
                }
                ListViewApater adapter = new ListViewApater(MainActivity.this,tempList);
                ListView listView = (ListView)findViewById(R.id.main_list_view);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                        Object ob = data.get(position);
                        if(ob instanceof DayItem) {
                            DayItem d = (DayItem) data.get(position);
                            String s = d.getDetail();
                            Intent intent = new Intent(MainActivity.this, ClickDayItem.class);
                            intent.putExtra("extra_data", s);
                            intent.putExtra("week", d.getWeek());
                            intent.putExtra("day", d.getDay());
                            intent.putExtra("year", clickYear);
                            intent.putExtra("month", clickMouth);
                            startActivity(intent);
                        }else if(ob instanceof AddItemPoint){
                            AddItemPoint d = (AddItemPoint) data.get(position);
                            //String s = d.getDetail();
                            Intent intent = new Intent(MainActivity.this, ClickAddItemPoint.class);
                            //intent.putExtra("extra_data", s);
                            intent.putExtra("week", d.getWeek());
                            intent.putExtra("day", d.getDay());
                            intent.putExtra("year", clickYear);
                            intent.putExtra("month", clickMouth);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    private String getWeekOfMe(String date){
        String weekStr=null;
        int tempdata=getDayofweek(date);
        switch (tempdata){
            case 1:weekStr = "MON";break;
            case 2:weekStr = "TUE";break;
            case 3:weekStr = "WEN";break;
            case 4:weekStr = "THU";break;
            case 5:weekStr = "FRI";break;
            case 6:weekStr = "SAT";break;
            case 7:weekStr = "SUN";break;
        }
        return weekStr;
    }
    public static int getDayofweek(String date){
        Calendar cal = Calendar.getInstance();
//   cal.setTime(new Date(System.currentTimeMillis()));
        if (date.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        }else {
            cal.setTime(new Date(getDateByStr2(date).getTime()));
        }
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    public static Date getDateByStr2(String dd)
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sd.parse(dd);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }
        return date;
    }
*/

    private void initDays()
    {
        DayItem day1 = new DayItem("SAT","1","去超市来着...");
        data.add(day1);
        DayItem day2 = new DayItem("SUN","2","学妹来啦~~~。") ;
        data.add(day2);
        DayItem day3 = new DayItem("MON","3","路两边的树吸走了汽车尾气");
        data.add(day3);
        DayItem day4 = new DayItem("TUE","4","现在吃到火锅好开心");
        data.add(day4);
        AddItemPoint point = new AddItemPoint(R.drawable.add_dot_btn,"WED","4");
        data.add(point);
        DayItem day6 = new DayItem("THR","4","今天要去ACM基地值班");
        data.add(day4);
        for(int i=0;i<22;i++){
            AddItemPoint point2 = new AddItemPoint(R.drawable.add_dot_btn,"FRI","5");
            data.add(point2);
        }
        AddItemPoint point1 = new AddItemPoint(R.drawable.add_dot_btn,"THR","5");
        data.add(point);
        DayItem day5 = new DayItem("FRI","7","你，黑咖啡，芝士年糕，羽毛球，成功");
        data.add(day5);
        DayItem day7 = new DayItem("SAT","8","DGA 7 最爱");
        data.add(day7);
    }
}
