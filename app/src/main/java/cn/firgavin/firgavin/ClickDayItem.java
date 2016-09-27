package cn.firgavin.firgavin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ClickDayItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_day_item);

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
        tv1.setText(w);

        String m = intent.getStringExtra("month");
        TextView tv2 = (TextView)findViewById(R.id.showMonth);
        tv2.setText(m);

        String da = intent.getStringExtra("day");
        TextView tv3 = (TextView)findViewById(R.id.showDay);
        tv3.setText(da);

        String y = intent.getStringExtra("year");
        TextView tv4 = (TextView)findViewById(R.id.showYear);
        tv4.setText(y);

        String d = intent.getStringExtra("extra_data");
        EditText tv = (EditText) findViewById(R.id.dayItemTextView);
        tv.setText(d);
    }
}
