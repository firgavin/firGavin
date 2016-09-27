package cn.firgavin.firgavin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClickLongSquare extends AppCompatActivity {

    private StaticStorage publicStorage = new StaticStorage();

    //private List<DayItem> daylist = new ArrayList<DayItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_long_square);

        //daylist = publicStorage.AllMonth
        /*for(int i=0;i<publicStorage.AllMonth.size();i++){
            DayItem newItem;
            newItem = (DayItem) publicStorage.AllMonth.get(i);
            daylist.add(newItem);
        }*/
        LittleDayApater adapter;
        adapter = new LittleDayApater(ClickLongSquare.this,publicStorage.AllMonth);
        ListView listView = (ListView)findViewById(R.id.longSquareListView);
        listView.setAdapter(adapter);
    }
}
