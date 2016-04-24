package ua.kpi.speleo.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import ua.kpi.speleo.R;

import java.util.ArrayList;
import java.util.HashMap;

import static ua.kpi.speleo.app.Constants.COLUMN1;
import static ua.kpi.speleo.app.Constants.COLUMN2;
import static ua.kpi.speleo.app.Constants.COLUMN3;
import static ua.kpi.speleo.app.Constants.COLUMN4;
import static ua.kpi.speleo.app.Constants.COLUMN5;

public class ListviewAdapter extends BaseAdapter {

    public ArrayList<HashMap> list;
    Activity activity;

    public ListviewAdapter(Activity activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        EditText editText1;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        EditText editText5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.editText1 = (EditText) convertView.findViewById(R.id.column1);
            holder.editText2 = (EditText) convertView.findViewById(R.id.column2);
            holder.editText3 = (EditText) convertView.findViewById(R.id.column3);
            holder.editText4 = (EditText) convertView.findViewById(R.id.column4);
            holder.editText5 = (EditText) convertView.findViewById(R.id.column5);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.editText1.setText((String) map.get(COLUMN1));
        holder.editText2.setText((String) map.get(COLUMN2));
        holder.editText3.setText((String) map.get(COLUMN3));
        holder.editText4.setText((String) map.get(COLUMN4));
        holder.editText5.setText((String) map.get(COLUMN5));

        return convertView;
    }

}

