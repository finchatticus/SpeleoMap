package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.DataDAO;
import ua.kpi.speleo.app.graphics.DrawCave;
import ua.kpi.speleo.app.graphics.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class SkeletonActivity extends Activity {
    public GraphicView graphicView;
    private Button buttonBack;
    private TextView textViewLength;
    private TextView textViewArea;
    public static Caves caves;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic);

        caves = (Caves) getIntent().getParcelableExtra("Caves");

        graphicView = (GraphicView) findViewById(R.id.graphicView);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(onClickButtonBackListener);
        textViewLength = (TextView) findViewById(R.id.textViewLength);

        DataDAO dataDAO = new DataDAO(this);

        ArrayList<HashMap> list = new ArrayList<HashMap>();
        list = dataDAO.getDataListHashmap(caves);

        double length = 0;

        for (int i = 0; i < list.size(); i++) {
            length += (Double) list.get(i).get(Constants.DISTANCE);
        }

        textViewLength.setText(String.valueOf(length));
        textViewArea = (TextView) findViewById(R.id.textViewArea);
        textViewArea.setText("321");
    }

    private View.OnClickListener onClickButtonBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext() ,DataActivity.class);
            intent.putExtra("Caves", caves);
            startActivity(intent);
        }
    };
}
