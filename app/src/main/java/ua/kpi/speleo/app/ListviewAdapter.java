package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

import java.util.ArrayList;
import java.util.HashMap;

import static ua.kpi.speleo.app.Constants.FROM;
import static ua.kpi.speleo.app.Constants.TO;
import static ua.kpi.speleo.app.Constants.DISTANCE;
import static ua.kpi.speleo.app.Constants.AZIMUTH;
import static ua.kpi.speleo.app.Constants.INCLINATION;

public class ListviewAdapter extends BaseAdapter {
    private static final String LOG_TAG = ListviewAdapter.class.getSimpleName();

    private ArrayList<HashMap> list;
    private Activity activity;

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

    /*public void insert(int position, HashMap item) {
        list.set(position,item);
    }*/

    private class ViewHolder {
        TextView editText1;
        TextView editText2;
        TextView editText3;
        TextView editText4;
        TextView editText5;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        final ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.editText1 = (TextView) convertView.findViewById(R.id.column1);
            holder.editText1.setRawInputType(Configuration.KEYBOARD_12KEY);
            holder.editText2 = (TextView) convertView.findViewById(R.id.column2);
            holder.editText2.setRawInputType(Configuration.KEYBOARD_12KEY);
            holder.editText3 = (TextView) convertView.findViewById(R.id.column3);
            holder.editText3.setRawInputType(Configuration.KEYBOARD_12KEY);
            holder.editText4 = (TextView) convertView.findViewById(R.id.column4);
            holder.editText4.setRawInputType(Configuration.KEYBOARD_12KEY);
            holder.editText5 = (TextView) convertView.findViewById(R.id.column5);
            holder.editText5.setRawInputType(Configuration.KEYBOARD_12KEY);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.editText1.setText(map.get(FROM).toString());
        holder.editText2.setText(map.get(TO).toString());
        holder.editText3.setText(map.get(DISTANCE).toString());
        holder.editText4.setText(map.get(AZIMUTH).toString());
        holder.editText5.setText(map.get(INCLINATION).toString());

        //holder.editText1.addTextChangedListener();

       /* holder.editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    final EditText editText = (EditText) view;
                    String text = editText.getText().toString();
                    int id = (Integer) list.get(position).get(Constants.ID);
                    int from = Integer.valueOf(text);
                    if(text != list.get(position).get(DISTANCE)) {
                        Data data = new Data();
                        data.setId(id);
                        data.setFrom(from);
                        data.setTo((Integer) list.get(position).get(Constants.TO));
                        data.setDistance((Double) list.get(position).get(Constants.DISTANCE));
                        data.setAzimuth((Double) list.get(position).get(Constants.AZIMUTH));
                        data.setInclination((Double) list.get(position).get(Constants.INCLINATION));
                        Caves caves = new Caves((Integer) list.get(position).get(Constants.ID_CAVE));
                        data.setCaves(caves);

                        DataDAO dataDAO = new DataDAO(view.getContext());
                        dataDAO.update(data);
                    }
                }
            }
        });

        holder.editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    final EditText editText = (EditText) view;
                    String text = editText.getText().toString();
                    int id = (Integer) list.get(position).get(Constants.ID);
                    int to = Integer.valueOf(text);
                    if(text != list.get(position).get(DISTANCE)) {
                        Data data = new Data();
                        data.setId(id);
                        data.setFrom((Integer) list.get(position).get(Constants.FROM));
                        data.setTo(to);
                        data.setDistance((Double) list.get(position).get(Constants.DISTANCE));
                        data.setAzimuth((Double) list.get(position).get(Constants.AZIMUTH));
                        data.setInclination((Double) list.get(position).get(Constants.INCLINATION));
                        Caves caves = new Caves((Integer) list.get(position).get(Constants.ID_CAVE));
                        data.setCaves(caves);

                        DataDAO dataDAO = new DataDAO(view.getContext());
                        dataDAO.update(data);
                    }
                }
            }
        });

        holder.editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    final EditText editText = (EditText) view;
                    String text = editText.getText().toString();
                    int id = (Integer) list.get(position).get(Constants.ID);
                    double distance = Double.valueOf(text);
                    if(text != list.get(position).get(DISTANCE)) {
                        Data data = new Data();
                        data.setId(id);
                        data.setFrom((Integer) list.get(position).get(Constants.FROM));
                        data.setTo((Integer) list.get(position).get(Constants.FROM));
                        data.setDistance(distance);
                        data.setAzimuth((Double) list.get(position).get(Constants.AZIMUTH));
                        data.setInclination((Double) list.get(position).get(Constants.INCLINATION));
                        Caves caves = new Caves((Integer) list.get(position).get(Constants.ID_CAVE));
                        data.setCaves(caves);

                        DataDAO dataDAO = new DataDAO(view.getContext());
                        dataDAO.update(data);
                    }
                }
            }
        });

        holder.editText4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    final EditText editText = (EditText) view;
                    String text = editText.getText().toString();
                    int id = (Integer) list.get(position).get(Constants.ID);
                    double azimuth = Double.valueOf(text);
                    if(text != list.get(position).get(DISTANCE)) {
                        Data data = new Data();
                        data.setId(id);
                        data.setFrom((Integer) list.get(position).get(Constants.FROM));
                        data.setTo((Integer) list.get(position).get(Constants.FROM));
                        data.setDistance((Double) list.get(position).get(Constants.DISTANCE));
                        data.setAzimuth(azimuth);
                        data.setInclination((Double) list.get(position).get(Constants.INCLINATION));
                        Caves caves = new Caves((Integer) list.get(position).get(Constants.ID_CAVE));
                        data.setCaves(caves);

                        DataDAO dataDAO = new DataDAO(view.getContext());
                        dataDAO.update(data);
                    }
                }
            }
        });

        holder.editText5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    final EditText editText = (EditText) view;
                    String text = editText.getText().toString();
                    int id = (Integer) list.get(position).get(Constants.ID);
                    double inclination = Double.valueOf(text);
                    if(text != list.get(position).get(DISTANCE)) {
                        Data data = new Data();
                        data.setId(id);
                        data.setFrom((Integer) list.get(position).get(Constants.FROM));
                        data.setTo((Integer) list.get(position).get(Constants.FROM));
                        data.setDistance((Double) list.get(position).get(Constants.DISTANCE));
                        data.setAzimuth((Double) list.get(position).get(Constants.AZIMUTH));
                        data.setInclination(inclination);
                        Caves caves = new Caves((Integer) list.get(position).get(Constants.ID_CAVE));
                        data.setCaves(caves);

                        DataDAO dataDAO = new DataDAO(view.getContext());
                        dataDAO.update(data);
                    }
                }
            }
        });*/


        return convertView;
    }


}

