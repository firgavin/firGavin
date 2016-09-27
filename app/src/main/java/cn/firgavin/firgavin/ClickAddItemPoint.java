package cn.firgavin.firgavin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClickAddItemPoint extends AppCompatActivity {

    private StaticStorage publicStorage = new StaticStorage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_add_item_point);

        Intent intent = getIntent();

        String w = intent.getStringExtra("week");
        if(w == "MON")
            w = "MONDAY";
        else if(w == "TUE")
            w = "TUESDAY";
        else if(w == "WED")
            w = "WEDNESDAY";
        else if(w == "THR")
            w = "THRUTHDAY";
        else if(w == "FRI")
            w = "FRIDAY";
        else if(w == "SAT")
            w = "SATURDAY";
        else if(w == "SUN")
            w = "SUNDAY";
        TextView tv1 = (TextView)findViewById(R.id.showWeek);
        final String ww= w;
        tv1.setText(w);

        final String m = intent.getStringExtra("month");
        TextView tv2 = (TextView)findViewById(R.id.showMonth);
        tv2.setText(m);

        final String da = intent.getStringExtra("day");
        TextView tv3 = (TextView)findViewById(R.id.showDay);
        tv3.setText(da);

        final String y = intent.getStringExtra("year");
        TextView tv4 = (TextView)findViewById(R.id.showYear);
        tv4.setText(y);

        //String d = intent.getStringExtra("extra_data");
        //TextView tv = (TextView) findViewById(R.id.dayItemTextView);
        //tv.setText(d);

        final int position = intent.getIntExtra("positionOfPoint",1);

        Button done = (Button) findViewById(R.id.clickDoneToAdd);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText clickdone = (EditText) findViewById(R.id.clickPointToAddContent);
                String inputContent = clickdone.getText().toString();
                //saveAsObject(y,m,da,ww,inputContent);
                DayItem newDay = new DayItem(y,m,da,ww,inputContent);
                publicStorage.AllMonth.set(position,newDay);
                finish();
            }
        });
    }

    /*public void saveAsObject(String y, String m, String da, String ww, String inputContent)
            {
                DayItem newDayItem = new DayItem(y,m,da,ww,inputContent);
                saveObject("DayItemObject.dat",newDayItem);
            }

        private void saveObject(String name,Object sod){
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try{
                fos = this.openFileOutput(name, Context.MODE_PRIVATE);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(sod);
                System.out.println("存储对象成功");
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
    }

    private Object getObject(String name){
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
}
