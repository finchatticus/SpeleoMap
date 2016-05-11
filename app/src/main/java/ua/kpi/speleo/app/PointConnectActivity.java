package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;

public class PointConnectActivity extends Activity {
    private static final String LOG_TAG = PointConnectActivity.class.getSimpleName();

    private EditText editTextPoint1;
    private EditText editTextPoint2;
    private Button buttonPointConnect;
    private Button buttonPointDisconnect;

    private Caves caves;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_connect);
        Log.d(LOG_TAG, "getParcelableExtra");
        caves = (Caves) getIntent().getParcelableExtra("Caves");

        sharedPreferences = getSharedPreferences(caves.getId() + "_connected_points", Context.MODE_PRIVATE);


        editTextPoint1 = (EditText) findViewById(R.id.editTextPoint1);
        editTextPoint2 = (EditText) findViewById(R.id.editTextPoint2);
        buttonPointConnect = (Button) findViewById(R.id.buttonPointConnect);
        buttonPointDisconnect = (Button) findViewById(R.id.buttonDisconnectPoint);

        buttonPointConnect.setOnClickListener(buttonPointConnectOnclickListener);
        buttonPointDisconnect.setOnClickListener(buttonPointDisconnectOnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private View.OnClickListener buttonPointConnectOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String point1 = editTextPoint1.getText().toString();
            String point2 = editTextPoint2.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(point1 + "_" + point2, true);
            editor.commit();
            Toast.makeText(getApplicationContext(),"Point " + point1 + "connected to point " + point2,Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener buttonPointDisconnectOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String point1 = editTextPoint1.getText().toString();
            String point2 = editTextPoint2.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(point1 + "_" + point2, false);
            editor.commit();

            Toast.makeText(getApplicationContext(),"Point " + point1 + " point " + point2 + " disconnected  ",Toast.LENGTH_LONG).show();
        }
    };
}
