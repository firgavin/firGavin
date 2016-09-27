package cn.firgavin.firgavin;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class LittleDayApater extends BaseAdapter {

    private int resourceId;
    private Context context;
    private LayoutInflater listContainer;
    //private List<Object> data =new ArrayList<>();
    private  ArrayList<Object> listItems;

    public LittleDayApater(Context context,ArrayList<Object> listItems){
        this.context=context;
        listContainer = LayoutInflater.from(context);

        this.listItems = listItems;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //Log.e("method", "getView");
        final int selectID = position;
        //自定义视图
        DayItem  listItemView = null;
        if (convertView == null) {
            //listItemView = (DayItem) listItems.get(position);
            listItemView = new DayItem();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.activity_little_day_item, null);
            //获取控件对象
            TextView daydetail = (TextView)convertView.findViewById(R.id.LittleContent);
            TextView dayweek = (TextView)convertView.findViewById(R.id.LittleWeek);
            TextView dayday = (TextView)convertView.findViewById(R.id.LittleDate);
            daydetail.setText(listItemView.getDetail());
            dayweek.setText(listItemView.getWeek());
            dayday.setText(listItemView.getDay());
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (DayItem) convertView.getTag();
        }

        return convertView;
    }
}
