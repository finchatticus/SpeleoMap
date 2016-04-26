package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import ua.kpi.speleo.R;

public class SkeletonActivity extends Activity {
    public GraphicView graphicView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic);

        graphicView = (GraphicView) findViewById(R.id.graphicView);
    }
}
