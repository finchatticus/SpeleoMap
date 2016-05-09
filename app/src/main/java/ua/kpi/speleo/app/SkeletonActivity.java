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
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;

public class SkeletonActivity extends Activity {
    public GraphicView graphicView;
    private Button buttonBack;
    public static Caves caves;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic);

        caves = (Caves) getIntent().getParcelableExtra("Caves");

        graphicView = (GraphicView) findViewById(R.id.graphicView);
        buttonBack = (Button) findViewById(R.id.buttonBack);
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
